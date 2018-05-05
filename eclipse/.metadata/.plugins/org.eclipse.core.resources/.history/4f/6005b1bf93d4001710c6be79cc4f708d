/*
 * Decompiled with CFR 0_123.
 */
package ixos.xshttp;

import ixos.xshttp.NVPair;
import ixos.xshttp.XsHttp;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLConnection;
import java.util.ArrayList;

public class CommunicationUtil {
    public static final String SESSION_ID = "Adms-SessionId";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String ADMS_ERROR = "Adms-ErrTxt";
    public static final String ADMS_ERROR_CODE = "Adms-ErrNo";
    public static final String ASCONF_ERROR = "ASConf-ErrTxt";
    public static final String ASCONF_ERROR_CODE = "ASConf-ErrNo";
    public static final String SPAWN_ERROR = "Spawn-ErrTxt";
    private static final String[] OK_HEADERS = new String[]{"Adms-SessionId", "Content-Length"};
    private static final String[] ERROR_HEADERS = new String[]{"Adms-ErrTxt", "Adms-ErrNo", "ASConf-ErrTxt", "ASConf-ErrNo", "Spawn-ErrTxt"};

    protected static int parseHeaders(URLConnection uRLConnection, ArrayList arrayList, ArrayList arrayList2) {
        int n;
        arrayList.clear();
        arrayList2.clear();
        int n2 = -1;
        String string = null;
        String string2 = null;
        for (n = 0; n < ERROR_HEADERS.length; ++n) {
            string = ERROR_HEADERS[n];
            string2 = uRLConnection.getHeaderField(ERROR_HEADERS[n]);
            if (string2 == null || string.endsWith("ErrNo") && string2.equals("0") || string.endsWith("ErrTxt") && string2.equals("Success")) continue;
            arrayList2.add(new NVPair(string, string2));
        }
        for (n = 0; n < OK_HEADERS.length; ++n) {
            string = OK_HEADERS[n];
            string2 = uRLConnection.getHeaderField(OK_HEADERS[n]);
            if (string2 == null) continue;
            arrayList.add(new NVPair(string, string2));
        }
        if (string2 != null) {
            n2 = new Integer(string2);
        }
        XsHttp.log("h:" + arrayList + arrayList2);
        XsHttp.log("c:" + n2);
        return n2;
    }

    protected static void setHeaders(URLConnection uRLConnection, NVPair[] arrnVPair) {
        if (arrnVPair != null) {
            for (int i = arrnVPair.length - 1; i > -1; --i) {
                NVPair nVPair = arrnVPair[i];
                uRLConnection.setRequestProperty(nVPair.getName(), nVPair.getValue());
            }
        }
    }

    protected static String getData(URLConnection uRLConnection, int n) throws Exception {
        String string;
        XsHttp.log("http.proxyHost: " + System.getProperty("http.proxyHost"));
        XsHttp.log("http.proxyPort: " + System.getProperty("http.proxyPort"));
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(uRLConnection.getInputStream()));
        while ((string = bufferedReader.readLine()) != null) {
            stringBuffer.append(string).append('\n');
        }
        bufferedReader.close();
        XsHttp.log("r: " + new String(stringBuffer));
        String string2 = new String(stringBuffer);
        while (string2.endsWith("\n") || string2.endsWith("\r")) {
            string2 = string2.substring(0, string2.length() - 1);
        }
        return string2;
    }
}

