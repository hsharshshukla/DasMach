/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  ixos.base.Log
 */
package ixos.xsrpc;

import ixos.base.Log;
import ixos.xsrpc.CallMsg;
import ixos.xsrpc.RecordStream;
import ixos.xsrpc.ReplyMsg;
import ixos.xsrpc.Request;
import ixos.xsrpc.Service;
import ixos.xsrpc.Xdr;
import ixos.xsrpc.XdrData;
import ixos.xsrpc.XdrProc;
import ixos.xsrpc.XdrRec;
import ixos.xsrpc.XsRpc;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class TcpService
extends Service
implements RecordStream,
Runnable,
Cloneable {
    private ServerSocket st_sock;
    private boolean st_stopping;
    private Socket st_clientSock;
    private XdrRec st_xdrs;
    private int st_xid;
    private int st_sendsz;
    private int st_recvsz;

    public TcpService(int n, int n2, int n3, int n4, int n5) throws IOException {
        super(n2, n3);
        Log log = new Log("TcpService", "TcpService");
        this.st_sendsz = n4;
        this.st_recvsz = n5;
        this.st_sock = new ServerSocket(n);
        log.msg(13, "*** TcpService: parent service: " + this);
    }

    public Object clone() throws CloneNotSupportedException {
        TcpService tcpService = (TcpService)Object.super.clone();
        tcpService.st_xdrs = new XdrRec(this.st_sendsz, this.st_recvsz, tcpService);
        tcpService.st_sock = null;
        return tcpService;
    }

    private static void closeSocketSilently(Socket socket) {
        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void runService(boolean bl) {
        Log log = new Log("TcpService", "runService");
        do {
            Object object = this;
            synchronized (object) {
                if (this.st_stopping) {
                    log.msg(13, "*** TcpService.runService: broken!");
                    break;
                }
            }
            try {
                log.msg(13, "*** TcpService: waiting for a connection");
                this.st_clientSock = this.st_sock.accept();
                object = this.st_clientSock.getInetAddress();
                log.msg(13, "*** connection from " + object);
                if (!this.allowedHost((InetAddress)object)) {
                    log.msg(13, "*** connection from " + object + " is not allowed !");
                    TcpService.closeSocketSilently(this.st_clientSock);
                    continue;
                }
                TcpService tcpService = (TcpService)this.clone();
                log.msg(13, "*** TcpService: clone is: " + tcpService);
                Runnable runnable = this;
                synchronized (runnable) {
                    log.msg(13, "*** TcpService: dispatch service stored");
                }
                this.st_clientSock = null;
                runnable = new Thread(tcpService);
                runnable.setDaemon(true);
                runnable.start();
            }
            catch (CloneNotSupportedException cloneNotSupportedException) {
                cloneNotSupportedException.printStackTrace();
                log.msg(13, cloneNotSupportedException.toString());
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
                log.msg(13, iOException.toString());
            }
        } while (true);
    }

    @Override
    public synchronized void stopService() {
        Log log = new Log("TcpService", "stopService");
        this.st_stopping = true;
        this.destroyAll();
        log.msg(13, "*** TcpService.stopService: stop set, for: " + this);
    }

    @Override
    public void run() {
        CallMsg callMsg = new CallMsg();
        Request request = new Request();
        while (!this.st_stopping) {
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
            this.dispatch(request);
        }
    }

    @Override
    protected void xp_destroy() {
        Log log = new Log("TcpService", "xp_destroy");
        if (this.st_clientSock != null) {
            log.msg(13, "*** TcpService.xp_destroy: client socket closed, " + this);
            try {
                this.st_clientSock.close();
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
                log.msg(13, iOException.toString());
            }
            this.st_clientSock = null;
        }
        if (this.st_sock != null) {
            log.msg(13, "*** TcpService.xp_destroy: server socket closed, " + this);
            try {
                this.st_sock.close();
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
                log.msg(13, iOException.toString());
            }
            this.st_sock = null;
        }
    }

    private synchronized void destroyAll() {
        Log log = new Log("TcpService", "destroyAll");
        log.msg(13, "*** TcpService: destroyAll called, for: " + this);
        if (this.st_sock != null || this.st_clientSock != null) {
            this.xp_destroy();
        }
    }

    @Override
    protected boolean xp_recv(CallMsg callMsg) {
        this.st_xdrs.x_op = 1;
        if (!this.st_xdrs.xdrrec_skiprecord()) {
            return false;
        }
        if (callMsg.xdr_callmsg(this.st_xdrs)) {
            this.st_xid = callMsg.rm_xid;
            return true;
        }
        return false;
    }

    @Override
    protected boolean xp_getargs(XdrProc xdrProc, XdrData xdrData) {
        return xdrProc.xdrproc(this.st_xdrs, xdrData);
    }

    @Override
    protected boolean xp_reply(ReplyMsg replyMsg) {
        this.st_xdrs.x_op = 0;
        replyMsg.rm_xid = this.st_xid;
        boolean bl = replyMsg.xdr_replymsg(this.st_xdrs);
        this.st_xdrs.xdrrec_endofrecord(true);
        return bl;
    }

    @Override
    public int readit(byte[] arrby, int n, int n2) {
        Log log = new Log("TcpService", "readit");
        log.msg(13, "*** TcpService.readit: len = " + n2);
        try {
            InputStream inputStream = this.st_clientSock.getInputStream();
            n2 = inputStream.read(arrby, n, n2);
            if (n2 < 0) {
                this.stopService();
            } else {
                XsRpc.dump(arrby, n, n2);
            }
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            log.msg(13, iOException.toString());
            this.stopService();
            return -1;
        }
        return n2;
    }

    @Override
    public int writeit(byte[] arrby, int n, int n2) {
        Log log = new Log("TcpService", "writeit");
        log.msg(13, "*** TcpService.writeit: len = " + n2);
        XsRpc.dump(arrby, n, n2);
        try {
            OutputStream outputStream = this.st_clientSock.getOutputStream();
            outputStream.write(arrby, n, n2);
            outputStream.flush();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            log.msg(13, iOException.toString());
            this.stopService();
            return -1;
        }
        return n2;
    }
}

