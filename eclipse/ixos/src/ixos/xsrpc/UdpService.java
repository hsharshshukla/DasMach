/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  ixos.base.Log
 */
package ixos.xsrpc;

import ixos.base.Log;
import ixos.xsrpc.CallMsg;
import ixos.xsrpc.ReplyMsg;
import ixos.xsrpc.Request;
import ixos.xsrpc.Service;
import ixos.xsrpc.Xdr;
import ixos.xsrpc.XdrData;
import ixos.xsrpc.XdrMem;
import ixos.xsrpc.XdrProc;
import ixos.xsrpc.XsRpc;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public abstract class UdpService
extends Service
implements Cloneable {
    private final DatagramSocket su_sock;
    private byte[] su_buf;
    private int su_xid;
    private Xdr su_xdrs;
    private InetAddress su_raddr;
    private int su_rport;
    private boolean su_stopping;

    public UdpService(int n, int n2, int n3, int n4, int n5) throws SocketException {
        super(n2, n3);
        this.su_sock = n == -1 ? new DatagramSocket() : new DatagramSocket(n);
        int n6 = (Math.max(n4, n5) + 3) / 4 * 4;
        this.su_buf = new byte[n6];
        this.su_xdrs = new XdrMem(this.su_buf, this.su_buf.length, 1);
    }

    public Object clone() throws CloneNotSupportedException {
        UdpService udpService = (UdpService)Object.super.clone();
        this.su_buf = (byte[])this.su_buf.clone();
        this.su_xdrs = new XdrMem(this.su_buf, this.su_buf.length, 1);
        return udpService;
    }

    public final InetAddress getLocalAddress() {
        return this.su_sock.getLocalAddress();
    }

    public final int getLocalPort() {
        return this.su_sock.getLocalPort();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void runService(boolean bl) {
        Log log = new Log("UdpService", "runService");
        CallMsg callMsg = new CallMsg();
        Request request = new Request();
        do {
            UdpService udpService = this;
            synchronized (udpService) {
                if (this.su_stopping) {
                    break;
                }
            }
            if (!this.xp_recv(callMsg)) continue;
            request.rq_prog = callMsg.rm_prog;
            request.rq_vers = callMsg.rm_vers;
            request.rq_proc = callMsg.rm_proc;
            if (this.sc_prog != request.rq_prog) {
                this.svcerr_noprog();
                continue;
            }
            if (this.sc_vers != request.rq_vers) {
                this.svcerr_progvers(this.sc_vers, this.sc_vers);
                continue;
            }
            if (bl) {
                try {
                    udpService = (UdpService)this.clone();
                    udpService.createServiceThread(request).start();
                }
                catch (CloneNotSupportedException cloneNotSupportedException) {
                    cloneNotSupportedException.printStackTrace();
                    log.msg(13, cloneNotSupportedException.toString());
                }
                continue;
            }
            this.dispatch(request);
        } while (true);
    }

    @Override
    public synchronized void stopService() {
        this.su_stopping = true;
        this.xp_destroy();
    }

    private Thread createServiceThread(Request request) {
        return new ServiceThread(request);
    }

    @Override
    protected boolean xp_recv(CallMsg callMsg) {
        Log log = new Log("UdpService", "xp_recv");
        DatagramPacket datagramPacket = new DatagramPacket(this.su_buf, this.su_buf.length);
        try {
            this.su_sock.receive(datagramPacket);
            this.su_raddr = datagramPacket.getAddress();
            this.su_rport = datagramPacket.getPort();
            log.msg(13, "*** rhost = " + this.su_raddr + " rport = " + this.su_rport);
            if (!this.allowedHost(this.su_raddr)) {
                log.msg(13, "*** connection from " + this.su_raddr + " is not allowed !");
                return false;
            }
            XsRpc.dump(datagramPacket.getData(), 0, datagramPacket.getLength());
        }
        catch (IOException iOException) {
            if (!this.su_stopping) {
                iOException.printStackTrace();
            }
            log.msg(13, iOException.toString());
            return false;
        }
        if (datagramPacket.getLength() < 16) {
            return false;
        }
        this.su_xdrs.x_op = 1;
        this.su_xdrs.x_setpos(0);
        if (!callMsg.xdr_callmsg(this.su_xdrs)) {
            return false;
        }
        this.su_xid = callMsg.rm_xid;
        return true;
    }

    @Override
    protected boolean xp_getargs(XdrProc xdrProc, XdrData xdrData) {
        return xdrProc.xdrproc(this.su_xdrs, xdrData);
    }

    @Override
    protected boolean xp_reply(ReplyMsg replyMsg) {
        Log log = new Log("UdpService", "xp_reply");
        this.su_xdrs.x_op = 0;
        this.su_xdrs.x_setpos(0);
        replyMsg.rm_xid = this.su_xid;
        if (replyMsg.xdr_replymsg(this.su_xdrs)) {
            int n = this.su_xdrs.x_getpos();
            DatagramPacket datagramPacket = new DatagramPacket(this.su_buf, n, this.su_raddr, this.su_rport);
            XsRpc.log("*** UdpService.xp_reply:  rhost = " + this.su_raddr + " rport = " + this.su_rport);
            XsRpc.dump(this.su_buf, 0, n);
            try {
                this.su_sock.send(datagramPacket);
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
                log.msg(13, iOException.toString());
                return false;
            }
        }
        return true;
    }

    @Override
    protected void xp_destroy() {
        if (this.su_sock != null) {
            this.su_sock.close();
        }
    }

    class ServiceThread
    extends Thread {
        private Request request;

        ServiceThread(Request request) {
            this.request = request;
        }

        @Override
        public void run() {
            UdpService.this.dispatch(this.request);
        }
    }

}

