/*
 * Decompiled with CFR 0_123.
 */
package ixos.xshttp;

public class Proxy {
    private String _addr;
    private int _port;
    private String _user;
    private String _passw;
    private boolean _useAuthen = false;

    public Proxy(String string, int n) {
        this._addr = string;
        this._port = n;
        this._useAuthen = false;
    }

    public Proxy(String string, int n, String string2, String string3) {
        this._addr = string;
        this._port = n;
        this._user = string2;
        this._passw = string3;
        this._useAuthen = this._user != null && this._user.length() >= 1;
    }

    public void setAddr(String string) {
        this._addr = string;
    }

    public String getAddr() {
        return this._addr;
    }

    public void setPort(int n) {
        this._port = n;
    }

    public int getPort() {
        return this._port;
    }

    public void setUser(String string) {
        this._user = string;
        this._useAuthen = this._user != null && this._user.length() >= 1;
    }

    public String getUser() {
        return this._user;
    }

    public void setPassw(String string) {
        this._passw = string;
    }

    public String getPassw() {
        return this._passw;
    }

    public boolean useAuthen() {
        return this._useAuthen;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this._addr + ":" + this._port);
        if (this._useAuthen) {
            stringBuffer.append(" u = " + this._user + "; p = " + this._passw);
        }
        return new String(stringBuffer);
    }
}

