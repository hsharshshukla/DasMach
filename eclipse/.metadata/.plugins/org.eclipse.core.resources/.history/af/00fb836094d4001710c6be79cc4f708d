/*
 * Decompiled with CFR 0_123.
 */
package ixos.xshttp;

import ixos.xshttp.CommunicationUtil;
import ixos.xshttp.Connection;
import ixos.xshttp.HTTPResponse;
import ixos.xshttp.NVPair;
import ixos.xshttp.XsHttp;
import ixos.xsutil.Codecs;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;

class PureHttpConnection
implements Connection {
    private final boolean DEBUG = true;

    PureHttpConnection() {
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
        Object object;
        ArrayList arrayList2;
        URL uRL = new URL(string, string2, n, string3);
        System.err.println("URL = " + uRL);
        HttpURLConnection httpURLConnection = (HttpURLConnection)uRL.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        if (System.getProperty("helpProxySet").equalsIgnoreCase("true")) {
            object = System.getProperty("http.proxyUser");
            arrayList = System.getProperty("http.proxyPass");
            if (!object.equals("") && !arrayList.equals("")) {
                arrayList2 = (String)object + ":" + (String)((Object)arrayList);
                String string5 = "Basic " + Codecs.base64Encode((String)((Object)arrayList2));
                httpURLConnection.setRequestProperty("Proxy-Authorization", string5);
            }
        }
        object = httpURLConnection.getOutputStream();
        object.write(string4.getBytes());
        XsHttp.log("s: " + new String(string4.getBytes()));
        System.err.println("Written to HTTP's body: " + string4);
        object.flush();
        arrayList = new ArrayList();
        arrayList2 = new ArrayList();
        int n2 = CommunicationUtil.parseHeaders(httpURLConnection, arrayList, arrayList2);
        int n3 = 400;
        String string6 = "Network or server error";
        try {
            n3 = httpURLConnection.getResponseCode();
            string6 = CommunicationUtil.getData(httpURLConnection, n2);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        if (n3 == -1) {
            httpURLConnection = (HttpURLConnection)uRL.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            object = httpURLConnection.getOutputStream();
            object.write(string4.getBytes());
            XsHttp.log("s: " + new String(string4.getBytes()));
            System.err.println("Written to HTTP's body: " + string4);
            object.flush();
            arrayList = new ArrayList();
            arrayList2 = new ArrayList();
            n2 = CommunicationUtil.parseHeaders(httpURLConnection, arrayList, arrayList2);
            n3 = 400;
            string6 = "Network or server error";
            try {
                n3 = httpURLConnection.getResponseCode();
                string6 = CommunicationUtil.getData(httpURLConnection, n2);
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        if (!arrayList2.isEmpty()) {
            n3 = 400;
        }
        System.err.println("result = " + string6);
        return new HTTPResponse(n3, arrayList, arrayList2, string6);
    }

    @Override
    public HTTPResponse get(String string, String string2, int n, String string3, NVPair[] arrnVPair, NVPair[] arrnVPair2) throws Exception {
        String string4;
        Object object;
        int n2;
        Object object2;
        block8 : {
            Object object3;
            StringBuffer stringBuffer = new StringBuffer(string3);
            if (arrnVPair != null) {
                stringBuffer.append('?');
                for (int i = arrnVPair.length - 1; i > -1; --i) {
                    object3 = arrnVPair[i];
                    System.err.println(object3);
                    stringBuffer.append(object3.getName()).append('=').append(object3.getValue()).append('&');
                }
                stringBuffer.setLength(stringBuffer.length() - 1);
            }
            URL uRL = new URL(string, string2, n, new String(stringBuffer));
            object3 = (HttpURLConnection)uRL.openConnection();
            CommunicationUtil.setHeaders((URLConnection)object3, arrnVPair2);
            if (System.getProperty("helpProxySet").equalsIgnoreCase("true")) {
                object = System.getProperty("http.proxyUser");
                object2 = System.getProperty("http.proxyPass");
                if (!object.equals("") && !object2.equals("")) {
                    String string5 = (String)object + ":" + (String)object2;
                    String string6 = "Basic " + Codecs.base64Encode(string5);
                    object3.setRequestProperty("Proxy-Authorization", string6);
                }
            }
            object = new ArrayList();
            object2 = new ArrayList();
            int n3 = CommunicationUtil.parseHeaders((URLConnection)object3, (ArrayList)object, (ArrayList)object2);
            n2 = 200;
            string4 = "";
            try {
                n2 = object3.getResponseCode();
                string4 = CommunicationUtil.getData((URLConnection)object3, n3);
            }
            catch (Exception exception) {
                exception.printStackTrace();
                if (!(exception instanceof IOException)) break block8;
                if (exception.getCause() instanceof UnknownHostException) {
                    n2 = 544;
                }
                if (!(exception.getCause() instanceof ConnectException)) break block8;
                n2 = 546;
            }
        }
        if (!object2.isEmpty()) {
            n2 = 400;
        }
        return new HTTPResponse(n2, (ArrayList)object, (ArrayList)object2, string4);
    }

    @Override
    public void setTimeout(int n) {
    }
}

