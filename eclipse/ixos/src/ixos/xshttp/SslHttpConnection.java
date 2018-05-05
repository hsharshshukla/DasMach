/*
 * Decompiled with CFR 0_123.
 */
package ixos.xshttp;

import ixos.xshttp.CommunicationUtil;
import ixos.xshttp.Connection;
import ixos.xshttp.HTTPResponse;
import ixos.xshttp.NVPair;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

class SslHttpConnection
implements Connection {
    private final boolean DEBUG = true;

    SslHttpConnection() {
    }

    @Override
    public HTTPResponse post(String string, String string2, int n, String string3, NVPair[] arrnVPair, NVPair[] arrnVPair2) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        if (arrnVPair != null && arrnVPair.length > 0) {
            for (int i = 0; i < arrnVPair.length; ++i) {
                NVPair nVPair = arrnVPair[i];
                stringBuffer.append(nVPair.getName()).append('=').append(URLEncoder.encode(nVPair.getValue(), "UTF-8")).append('&');
            }
            stringBuffer.setLength(stringBuffer.length() - 1);
        }
        String string4 = new String(stringBuffer);
        System.err.println("cmds: " + string4);
        System.err.println("comm: " + string3);
        return this.post(string, string2, n, string3, string4, arrnVPair2);
    }

    public HTTPResponse post(String string, String string2, int n, String string3, String string4, NVPair[] arrnVPair) throws Exception {
        ArrayList arrayList;
        int n2;
        ArrayList arrayList2;
        String string5 = null;
        string = "https";
        URL uRL = new URL(string, string2, n, string3);
        try {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection)uRL.openConnection();
            System.err.println("URL = " + httpsURLConnection.getURL().toString());
            X509TrustManager x509TrustManager = new X509TrustManager(){

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkServerTrusted(X509Certificate[] arrx509Certificate, String string) throws CertificateException {
                }

                @Override
                public void checkClientTrusted(X509Certificate[] arrx509Certificate, String string) throws CertificateException {
                }
            };
            SSLContext sSLContext = SSLContext.getInstance("SSL");
            sSLContext.init(null, new X509TrustManager[]{x509TrustManager}, null);
            SSLSocketFactory sSLSocketFactory = sSLContext.getSocketFactory();
            httpsURLConnection.setSSLSocketFactory(sSLSocketFactory);
            httpsURLConnection.setHostnameVerifier(new HostnameVerifier(){

                @Override
                public boolean verify(String string, SSLSession sSLSession) {
                    return true;
                }
            });
            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.connect();
            OutputStream outputStream = httpsURLConnection.getOutputStream();
            outputStream.write(string4.getBytes());
            System.err.println("Written to HTTPS's body: " + string4);
            outputStream.flush();
            arrayList = new ArrayList();
            arrayList2 = new ArrayList();
            int n3 = CommunicationUtil.parseHeaders(httpsURLConnection, arrayList, arrayList2);
            n2 = 400;
            string5 = "Network or server error";
            try {
                n2 = httpsURLConnection.getResponseCode();
                string5 = CommunicationUtil.getData(httpsURLConnection, n3);
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
            if (n2 == -1) {
                httpsURLConnection = (HttpsURLConnection)uRL.openConnection();
                httpsURLConnection.setSSLSocketFactory(sSLSocketFactory);
                httpsURLConnection.setHostnameVerifier(new HostnameVerifier(){

                    @Override
                    public boolean verify(String string, SSLSession sSLSession) {
                        return true;
                    }
                });
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.connect();
                outputStream = httpsURLConnection.getOutputStream();
                outputStream.write(string4.getBytes());
                System.err.println("Written to HTTPS's body: " + string4);
                outputStream.flush();
                arrayList = new ArrayList();
                arrayList2 = new ArrayList();
                n3 = CommunicationUtil.parseHeaders(httpsURLConnection, arrayList, arrayList2);
                n2 = 400;
                string5 = "Network or server error";
                try {
                    n2 = httpsURLConnection.getResponseCode();
                    string5 = CommunicationUtil.getData(httpsURLConnection, n3);
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            if (!arrayList2.isEmpty()) {
                n2 = 400;
            }
            System.err.println("result = " + string5);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
        return new HTTPResponse(n2, arrayList, arrayList2, string5);
    }

    @Override
    public HTTPResponse get(String string, String string2, int n, String string3, NVPair[] arrnVPair, NVPair[] arrnVPair2) throws Exception {
        Object object;
        int n2;
        ArrayList arrayList;
        ArrayList arrayList2;
        StringBuffer stringBuffer = new StringBuffer(string3);
        String string4 = "";
        if (arrnVPair != null) {
            stringBuffer.append('?');
            for (int i = arrnVPair.length - 1; i > -1; --i) {
                object = arrnVPair[i];
                System.err.println(object);
                stringBuffer.append(object.getName()).append('=').append(object.getValue()).append('&');
            }
            stringBuffer.setLength(stringBuffer.length() - 1);
        }
        string = "https";
        URL uRL = new URL(string, string2, n, new String(stringBuffer));
        try {
            block11 : {
                object = (HttpsURLConnection)uRL.openConnection();
                X509TrustManager x509TrustManager = new X509TrustManager(){

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] arrx509Certificate, String string) throws CertificateException {
                    }

                    @Override
                    public void checkClientTrusted(X509Certificate[] arrx509Certificate, String string) throws CertificateException {
                    }
                };
                SSLContext sSLContext = SSLContext.getInstance("SSL");
                sSLContext.init(null, new X509TrustManager[]{x509TrustManager}, null);
                SSLSocketFactory sSLSocketFactory = sSLContext.getSocketFactory();
                object.setSSLSocketFactory(sSLSocketFactory);
                object.setHostnameVerifier(new HostnameVerifier(){

                    @Override
                    public boolean verify(String string, SSLSession sSLSession) {
                        return true;
                    }
                });
                CommunicationUtil.setHeaders((URLConnection)object, arrnVPair2);
                n2 = 500;
                arrayList = new ArrayList();
                arrayList2 = new ArrayList();
                int n3 = -1;
                string4 = "";
                try {
                    n2 = object.getResponseCode();
                    n3 = CommunicationUtil.parseHeaders((URLConnection)object, arrayList, arrayList2);
                    string4 = CommunicationUtil.getData((URLConnection)object, n3);
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                    if (!(exception instanceof RuntimeException)) break block11;
                    if (exception.getMessage() != null && exception.getMessage().startsWith("Error while silently connecting")) {
                        String string5 = exception.getMessage();
                        if (string5.indexOf("Connection refused") > 0) {
                            n2 = 546;
                        } else if (string5.indexOf("The host name") > 0) {
                            n2 = 544;
                        }
                    }
                    throw exception;
                }
            }
            if (!arrayList2.isEmpty()) {
                n2 = 400;
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
        return new HTTPResponse(n2, arrayList, arrayList2, string4);
    }

    @Override
    public void setTimeout(int n) {
    }

}

