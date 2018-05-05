/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  ixos.base.Log
 *  ixos.base.PwdCrypt
 */
package ixos.xsadmx;

import ixos.base.Log;
import ixos.base.PwdCrypt;
import ixos.xsadmx.Admx;
import ixos.xsadmx.AdmxResult2;
import ixos.xsadmx.AdmxResultXdrProc2;
import ixos.xsadmx.AdmxRpcException;
import ixos.xsrpc.BlockingCallback;
import ixos.xsrpc.RpcException;
import ixos.xsrpc.TcpClient;
import ixos.xsrpc.XdrData;
import ixos.xsrpc.XdrProc;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class AdmxRpc2 {
    public static final int DEFAULT_ADMS_PORT = 4034;
    private static boolean USE_NEW = true;
    static int rpcTimeout = 300000;
    private TcpClient cl;
    private int _errCode = 0;
    private byte[] _bt = null;

    public AdmxRpc2(String string) throws AdmxRpcException {
        this(string, 4034);
    }

    public AdmxRpc2(String string, int n) throws AdmxRpcException {
        InetAddress inetAddress;
        Log log = new Log("xsadmx/AdmxRpc2", "init");
        log.msg(10, "host \"localhost\", port " + n);
        try {
            if (string.equalsIgnoreCase("localhost")) {
                log.msg(10, "Using value of InetAddress.getLocalHost() to connect to \"localhost\" ");
                inetAddress = InetAddress.getLocalHost();
            } else {
                inetAddress = InetAddress.getByName(string);
            }
        }
        catch (UnknownHostException unknownHostException) {
            throw new AdmxRpcException("Cannot get address for host \"hostname\": " + unknownHostException.getMessage());
        }
        log.msg(10, "Internet address of \"" + string + "\": " + inetAddress);
        try {
            this.cl = new TcpClient(inetAddress, n, 1175523601, 1, 8192, 8192);
        }
        catch (Exception exception) {
            throw new AdmxRpcException("Cannot build a connection: " + exception.getMessage());
        }
    }

    public synchronized void disconnect() {
        Log log = new Log("xsadmx/AdmxRpc2", "disconnect");
        this.cl.cl_destroy();
        USE_NEW = true;
    }

    public byte[] getByteArray() {
        Log log = new Log("xsadmx/AdmxRpc2", "getByteAray");
        return this._bt;
    }

    public synchronized String command(String string) throws AdmxRpcException {
        Log log = new Log("xsadmx/AdmxRpc2", "command");
        this._bt = null;
        AdmxResult2 admxResult2 = new AdmxResult2();
        try {
            if (USE_NEW) {
                log.msg(10, "rpc call 2: " + string);
                this.cl.setTimeout(rpcTimeout);
                this.cl.cl_call(2, XdrProc.xdr_wrapstring, new XdrData(string), new AdmxResultXdrProc2(), new XdrData(admxResult2), -1);
            } else {
                log.msg(10, "rpc call 1: " + string);
                this.cl.setTimeout(rpcTimeout);
                this.cl.cl_call(1, XdrProc.xdr_wrapstring, new XdrData(string), new AdmxResultXdrProc2(), new XdrData(admxResult2), -1);
            }
        }
        catch (RpcException rpcException) {
            this._errCode = -1;
            throw new AdmxRpcException(rpcException.getErrorCode() == 5 ? "Timeout expired, no response from server" : "Network communications failed. Please, try to reconnect to the server.");
        }
        catch (Exception exception) {
            this._errCode = -1;
            throw new AdmxRpcException("Connection lost. Please, try to reconnect to the server.");
        }
        this._errCode = admxResult2.error;
        if (admxResult2.error != 0) {
            throw new AdmxRpcException(admxResult2.buf);
        }
        this._bt = admxResult2.bt;
        return admxResult2.buf;
    }

    public void logon(String string, String string2) throws AdmxRpcException {
        Log log = new Log("xsadmx/AdmxRpc2", "logon");
        try {
            USE_NEW = true;
            this.command("user_logon " + string + " {" + PwdCrypt.pwencrypt((String)string2) + "}");
        }
        catch (AdmxRpcException admxRpcException) {
            if (admxRpcException.getMessage().equals("Network communications failed. Please, try to reconnect to the server2.")) {
                USE_NEW = false;
                this.command("user_logon " + string + " {" + PwdCrypt.pwencrypt((String)string2) + "}");
            }
            this._errCode = -1;
            throw admxRpcException;
        }
    }

    public int getErrCode() {
        return this._errCode;
    }

    public void setBlockingCallback(BlockingCallback blockingCallback) {
        this.cl.setBlockingCallback(blockingCallback);
    }

    public static int getTimeout() {
        return rpcTimeout;
    }

    public static void setTimeout(int n) {
        rpcTimeout = n;
    }

    public String call(String string) throws AdmxRpcException {
        return this.command(string);
    }

    public String call(String string, String string2) throws AdmxRpcException {
        return this.call(string, new String[]{string2});
    }

    public String call(String string, String string2, String string3) throws AdmxRpcException {
        return this.call(string, new String[]{string2, string3});
    }

    public String call(String string, String[] arrstring) throws AdmxRpcException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(string);
        for (int i = 0; i < arrstring.length; ++i) {
            Admx.appendElement(stringBuffer, arrstring[i]);
        }
        return this.command(stringBuffer.toString());
    }
}

