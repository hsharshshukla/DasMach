/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  ixos.base.PwdCrypt
 */
package ixos.xsadmx;

import ixos.base.PwdCrypt;
import ixos.xsadmx.Admx;
import ixos.xsadmx.AdmxResult;
import ixos.xsadmx.AdmxResultXdrProc;
import ixos.xsadmx.AdmxRpcException;
import ixos.xsrpc.BlockingCallback;
import ixos.xsrpc.RpcException;
import ixos.xsrpc.TcpClient;
import ixos.xsrpc.XdrData;
import ixos.xsrpc.XdrProc;
import java.net.InetAddress;

public class AdmxRpc {
    public static final int DEFAULT_ADMS_PORT = 4034;
    private static boolean USE_NEW = true;
    static int rpcTimeout = 120000;
    private TcpClient cl;

    public AdmxRpc(String string) throws AdmxRpcException {
        this(string, 4034);
    }

    public AdmxRpc(String string, int n) throws AdmxRpcException {
        try {
            this.cl = new TcpClient(InetAddress.getByName(string), n, 1175523601, 1, 8192, 8192);
        }
        catch (Exception exception) {
            throw new AdmxRpcException("Cannot build a connection.");
        }
    }

    public synchronized void disconnect() {
        this.cl.cl_destroy();
        USE_NEW = true;
    }

    public synchronized String command(String string) throws AdmxRpcException {
        AdmxResult admxResult = new AdmxResult();
        try {
            if (USE_NEW) {
                this.cl.cl_call(2, XdrProc.xdr_wrapstring, new XdrData(string), new AdmxResultXdrProc(), new XdrData(admxResult), rpcTimeout);
            } else {
                this.cl.cl_call(1, XdrProc.xdr_wrapstring, new XdrData(string), new AdmxResultXdrProc(), new XdrData(admxResult), rpcTimeout);
            }
        }
        catch (RpcException rpcException) {
            throw new AdmxRpcException(rpcException.getErrorCode() == 5 ? "Timeout expired, no response from server" : "Network communications failed. Please, try to reconnect to the server.");
        }
        catch (Exception exception) {
            throw new AdmxRpcException("Connection lost. Please, try to reconnect to the server.");
        }
        if (admxResult.error != 0) {
            throw new AdmxRpcException(admxResult.buf);
        }
        return admxResult.buf;
    }

    public void logon(String string, String string2) throws AdmxRpcException {
        try {
            USE_NEW = true;
            this.command("user_logon " + string + " {" + PwdCrypt.pwencrypt((String)string2) + "}");
        }
        catch (AdmxRpcException admxRpcException) {
            if (admxRpcException.getMessage().equals("Network communications failed. Please, try to reconnect to the server.")) {
                USE_NEW = false;
                this.command("user_logon " + string + " {" + PwdCrypt.pwencrypt((String)string2) + "}");
            }
            throw admxRpcException;
        }
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

