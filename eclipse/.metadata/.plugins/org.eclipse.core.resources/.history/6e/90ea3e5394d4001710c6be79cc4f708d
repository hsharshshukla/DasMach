/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  ixos.base.Log
 */
package ixos.xsadmx;

import ixos.base.Log;
import ixos.xsadmx.AdmsRpcException;
import ixos.xsadmx.AdmxRpc2;
import ixos.xsadmx.AdmxRpcException;
import java.util.StringTokenizer;

public class AdmsRpcClient {
    private final int _maxRetries = 5;
    private final int _waitBeforeRetry = 2000;
    protected long _timeStamp = System.currentTimeMillis();
    private AdmxRpc2 _xsadmx = null;
    private String _host = "";
    private int _port = 4034;
    private String _user = "";
    private String _pwcrypt = "";
    private String _ipaddr = null;

    public String getHost() {
        return this._host;
    }

    public int getPort() {
        return this._port;
    }

    public String getUser() {
        return this._user;
    }

    public String getPasswd() {
        return this._pwcrypt;
    }

    public String getRemoteAddr() {
        return this._ipaddr;
    }

    public void setRemoteAddr(String string) {
        this._ipaddr = string;
    }

    public byte[] getByteArray() {
        if (this._xsadmx != null) {
            return this._xsadmx.getByteArray();
        }
        return null;
    }

    public AdmsRpcClient(String string, int n, String string2, String string3, String string4) throws AdmsRpcException {
        Log log = new Log("AdmsRpcClient", "AdmsRpcClient_hpup");
        this.connect(string, n);
        this.setRemoteAddr(string4);
        try {
            this.auth(string2, string3);
        }
        catch (AdmsRpcException admsRpcException) {
            this.disconnect();
            throw admsRpcException;
        }
        this.touch();
    }

    public AdmsRpcClient(String string, int n) throws AdmsRpcException {
        Log log = new Log("AdmsRpcClient", "AdmsRpcClient_hp");
        this.connect(string, n);
        this.touch();
    }

    public AdmsRpcClient(String string) throws AdmsRpcException {
        Log log = new Log("AdmsRpcClient", "AdmsRpcClient_h");
        this.connect(string, 4034);
        this.touch();
    }

    private void connect(String string, int n) throws AdmsRpcException {
        Log log = new Log("AdmsRpcClient", "connect");
        log.msg(10, "connecting to host " + string + " on port " + n);
        if (this._xsadmx != null) {
            return;
        }
        try {
            this._xsadmx = new AdmxRpc2(string, n);
            this._host = string;
            this._port = n;
            log.msg(10, "connection established");
            return;
        }
        catch (AdmxRpcException admxRpcException) {
            log.msg(2, admxRpcException.getMessage());
            this._xsadmx = null;
            throw new AdmsRpcException(-1, admxRpcException.getMessage());
        }
    }

    public void disconnect() {
        Log log = new Log("AdmsRpcClient", "disconnect");
        if (this._xsadmx != null) {
            log.msg(10, "connection closed");
            this._xsadmx.disconnect();
            this._xsadmx = null;
        }
    }

    public String command(String string) throws AdmsRpcException {
        Log log = new Log("AdmsRpcClient", "command");
        log.msg(10, "sending command '" + string + " '");
        String string2 = "Sorry, no error text";
        int n = 0;
        this.touch();
        do {
            try {
                if (this._xsadmx == null) {
                    log.msg(10, "reconnect to adms");
                    this.connect(this._host, this._port);
                    if (!this._user.equals("")) {
                        this.auth(this._user, this._pwcrypt);
                    }
                }
                StringTokenizer stringTokenizer = new StringTokenizer(string);
                while (stringTokenizer.hasMoreTokens()) {
                    String string3 = stringTokenizer.nextToken();
                    if (!string3.equals("user_logon")) continue;
                    if (stringTokenizer.countTokens() == 2) {
                        String string4 = stringTokenizer.nextToken();
                        String string5 = stringTokenizer.nextToken();
                        return "command 'user_logon' disabled";
                    }
                    return "Invalid number of arguments";
                }
            }
            catch (AdmsRpcException admsRpcException) {
                log.msg(10, "an exception occurs");
                if (this._xsadmx != null) {
                    this._xsadmx.disconnect();
                }
                this._xsadmx = null;
                ++n;
                try {
                    Thread.sleep(2000);
                }
                catch (InterruptedException interruptedException) {
                    // empty catch block
                }
                if (n >= 5) {
                    log.msg(2, "too much retries");
                    throw new AdmsRpcException(-1, "cmd '" + string + "' failed after " + n + " retries\nbecause " + admsRpcException.getMessage());
                }
                log.msg(10, "make another retry no " + n);
                continue;
            }
            break;
        } while (true);
        try {
            log.msg(10, "sending command to adms");
            string2 = this._xsadmx.command(string);
            return string2;
        }
        catch (AdmxRpcException admxRpcException) {
            if (this._xsadmx.getErrCode() > 0) {
                log.msg(10, "adms sends an error");
                throw new AdmsRpcException(this._xsadmx.getErrCode(), admxRpcException.getMessage());
            }
            log.msg(10, "connection error: " + admxRpcException.getMessage());
            throw new AdmsRpcException(-1, admxRpcException.getMessage());
        }
    }

    public String auth(String string, String string2) throws AdmsRpcException {
        Log log = new Log("AdmsRpcClient", "auth");
        log.msg(10, "authenticate user " + string + " and passwd " + string2);
        String string3 = "Sorry, no error text";
        this.touch();
        if (this._xsadmx == null) {
            this.connect(this._host, this._port);
        }
        try {
            log.msg(10, "authenticate the user");
            String string4 = "user_logon " + string + " " + string2;
            if (this._ipaddr != null) {
                string4 = string4 + " -ixclntaddr " + this._ipaddr;
            }
            string3 = this._xsadmx.command(string4);
        }
        catch (AdmxRpcException admxRpcException) {
            this._user = "";
            this._pwcrypt = "";
            if (this._xsadmx.getErrCode() > 0) {
                throw new AdmsRpcException(this._xsadmx.getErrCode(), admxRpcException.getMessage());
            }
            if (this._xsadmx != null) {
                this._xsadmx.disconnect();
            }
            this._xsadmx = null;
        }
        this._user = string;
        this._pwcrypt = string2;
        return string3;
    }

    public boolean isConnected() {
        if (this._xsadmx != null) {
            return true;
        }
        return false;
    }

    public boolean test() {
        Log log = new Log("AdmsRpcClient", "test");
        if (this._xsadmx == null) {
            return false;
        }
        try {
            String string = this._xsadmx.command("");
            log.msg(10, "rpc connection ok");
            return true;
        }
        catch (AdmxRpcException admxRpcException) {
            this._xsadmx.disconnect();
            this._xsadmx = null;
            log.msg(10, "no rpc connection");
            return false;
        }
    }

    public void touch() {
        Log log = new Log("AdmsRpcClient", "touch");
        this._timeStamp = System.currentTimeMillis();
        log.msg(10, "new time stamp: " + this._timeStamp);
    }

    public boolean isTimedOut(long l) {
        long l2 = System.currentTimeMillis();
        if (l2 - this._timeStamp > l) {
            return true;
        }
        return false;
    }

    public long lastAccess() {
        return this._timeStamp;
    }
}

