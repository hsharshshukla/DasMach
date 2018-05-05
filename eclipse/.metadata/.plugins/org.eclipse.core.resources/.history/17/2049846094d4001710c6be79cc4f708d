/*
 * Decompiled with CFR 0_123.
 */
package ixos.xshttp;

import ixos.xshttp.Connection;
import ixos.xshttp.HTTPResponse;
import ixos.xshttp.NVPair;
import ixos.xshttp.ProtocolNotSuppException;
import ixos.xshttp.Proxy;
import ixos.xshttp.PureHttpConnection;
import ixos.xshttp.SslHttpConnection;
import ixos.xshttp.XsHttp;

public class HttpClient {
    public static final int GET_METHOD = 0;
    public static final int POST_METHOD = 1;
    public static final int HTTP_PROTOCOL = 0;
    public static final int HTTPS_PROTOCOL = 1;
    private String _protocol;
    private String _hostname;
    private int _port;
    private Connection _connection = null;
    private static Proxy _proxy;
    private int _timeout;

    public HttpClient(int n, String string, int n2) throws ProtocolNotSuppException {
        this._protocol = this.getProtocol(n);
        this._hostname = string;
        this._port = n2;
        XsHttp.log("Connection: " + this._protocol + "://" + this._hostname + ":" + this._port);
    }

    public HttpClient(int n, String string, int n2, Proxy proxy) throws ProtocolNotSuppException {
        HttpClient.useProxy(proxy);
        this._protocol = this.getProtocol(n);
        this._hostname = string;
        this._port = n2;
        XsHttp.log("Connection: " + this._protocol + "://" + this._hostname + ":" + this._port);
        XsHttp.log("Go through PROXY: " + proxy);
    }

    public String getProtocolString() {
        return this._protocol;
    }

    public int getPort() {
        return this._port;
    }

    public String getHost() {
        return this._hostname;
    }

    String getProtocol(int n) throws ProtocolNotSuppException {
        String string;
        switch (n) {
            case 0: {
                string = "http";
                this._connection = new PureHttpConnection();
                break;
            }
            case 1: {
                string = "https";
                this._connection = new SslHttpConnection();
                break;
            }
            default: {
                throw new ProtocolNotSuppException("Only HTTP and HTTPS protocols are available");
            }
        }
        return string;
    }

    public static void useProxy(Proxy proxy) {
        _proxy = proxy;
        if (_proxy != null) {
            System.setProperty("helpProxySet", "true");
            System.setProperty("http.proxyHost", proxy.getAddr());
            System.setProperty("http.proxyPort", Integer.toString(proxy.getPort()));
            System.setProperty("https.proxyHost", proxy.getAddr());
            System.setProperty("https.proxyPort", Integer.toString(proxy.getPort()));
            System.setProperty("http.proxyUser", proxy.getUser());
            System.setProperty("http.proxyPass", proxy.getPassw());
        } else {
            System.setProperty("helpProxySet", "false");
            System.setProperty("http.proxyHost", "");
            System.setProperty("http.proxyPort", "");
            System.setProperty("https.proxyHost", "");
            System.setProperty("https.proxyPort", "");
        }
    }

    public void cl_destroy() {
        XsHttp.log("HttpClient: destroying connection");
        this._connection = null;
    }

    public void setTimeout(int n) {
        if (n > 1000) {
            this._timeout = n;
            this._connection.setTimeout(this._timeout);
        }
    }

    public HTTPResponse cl_call(int n, String string, NVPair[] arrnVPair, int n2, boolean bl) throws Exception {
        switch (n) {
            case 0: {
                return this.cl_get(string, arrnVPair, n2, bl);
            }
            case 1: {
                return this.cl_post(string, arrnVPair, n2, bl);
            }
        }
        throw new Exception("InternalError - unknown proc (GET | POST)");
    }

    synchronized HTTPResponse cl_get(String string, NVPair[] arrnVPair, int n, boolean bl) throws Exception {
        if (this._connection == null) {
            throw new Exception("Connect first!");
        }
        this.setTimeout(n);
        XsHttp.log("Get(" + string + this.printArgs(arrnVPair) + ")");
        HTTPResponse hTTPResponse = bl ? this._connection.get(this._protocol, this._hostname, this._port, string, arrnVPair, new NVPair[]{new NVPair("Connection", "Close")}) : this._connection.get(this._protocol, this._hostname, this._port, string, arrnVPair, null);
        XsHttp.dump(hTTPResponse);
        return hTTPResponse;
    }

    synchronized HTTPResponse cl_post(String string, NVPair[] arrnVPair, int n, boolean bl) throws Exception {
        if (this._connection == null) {
            throw new Exception("Connect first!");
        }
        this.setTimeout(n);
        XsHttp.log("Post(" + string + this.printArgs(arrnVPair) + ")");
        HTTPResponse hTTPResponse = bl ? this._connection.post(this._protocol, this._hostname, this._port, string, arrnVPair, new NVPair[]{new NVPair("Connection", "Close")}) : this._connection.post(this._protocol, this._hostname, this._port, string, arrnVPair, null);
        XsHttp.dump(hTTPResponse);
        return hTTPResponse;
    }

    public HTTPResponse cl_call(int n, String string, int n2, boolean bl) throws Exception {
        return this.cl_call(n, string, new NVPair[0], n2, bl);
    }

    private String printArgs(NVPair[] arrnVPair) {
        if (arrnVPair == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrnVPair.length; ++i) {
            stringBuffer.append(", " + arrnVPair[i].getName() + " = " + arrnVPair[i].getValue());
        }
        return new String(stringBuffer);
    }
}

