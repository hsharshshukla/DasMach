/*
 * Decompiled with CFR 0_123.
 */
package ixos.xsutil;

import ixos.xsutil.XsDebug;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.StringTokenizer;

public class XsUtil {
    private static XsDebug m_dbg = new XsDebug();
    private static final boolean TRACE = false;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static String getVersionFromVersionTxt(String string) {
        StringTokenizer stringTokenizer;
        BufferedReader bufferedReader = null;
        String string2 = null;
        bufferedReader = new BufferedReader(new FileReader(string));
        String string3 = bufferedReader.readLine();
        if (string3 != null && (stringTokenizer = new StringTokenizer(string3, " ")).countTokens() >= 3) {
            stringTokenizer.nextToken();
            stringTokenizer.nextToken();
            string2 = stringTokenizer.nextToken();
        }
        if (bufferedReader == null) return string2;
        try {
            bufferedReader.close();
            return string2;
        }
        catch (IOException iOException) {
            return string2;
        }
        catch (Exception exception) {
            if (bufferedReader == null) return string2;
            try {
                bufferedReader.close();
                return string2;
            }
            catch (IOException iOException) {
                return string2;
            }
            catch (Throwable throwable) {
                if (bufferedReader == null) throw throwable;
                try {
                    bufferedReader.close();
                    throw throwable;
                }
                catch (IOException iOException) {
                    // empty catch block
                }
                throw throwable;
            }
        }
    }

    public static String replace(String string, String string2, String string3) {
        if (string == null || string2 == null || string3 == null) {
            throw new NullPointerException();
        }
        StringBuffer stringBuffer = new StringBuffer(string.length());
        int n = 0;
        int n2 = string.indexOf(string2, n);
        while (n2 >= 0) {
            stringBuffer.append(string.substring(n, n2));
            stringBuffer.append(string3);
            n = n2 + string2.length();
            n2 = string.indexOf(string2, n);
        }
        stringBuffer.append(string.substring(n));
        return stringBuffer.toString();
    }

    public static void enableLog(boolean bl) {
        m_dbg._enableLog(bl);
    }

    static void log(Object object) {
        m_dbg._log(object);
    }

    public static void enableTrace(boolean bl) {
        m_dbg._enableTrace(bl);
    }

    static void trace(Object object) {
    }
}

