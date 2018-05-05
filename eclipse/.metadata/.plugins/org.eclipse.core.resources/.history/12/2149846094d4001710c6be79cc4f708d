/*
 * Decompiled with CFR 0_123.
 */
package ixos.xshttp;

import ixos.xshttp.HTTPResponse;
import ixos.xsutil.XsDebug;

public class XsHttp {
    private static XsDebug m_dbg = new XsDebug();
    private static final boolean TRACE = false;

    public static void enableLog(boolean bl) {
        m_dbg._enableLog(bl);
    }

    public static void log(Object object) {
        m_dbg._log(object);
    }

    public static void enableTrace(boolean bl) {
        m_dbg._enableTrace(bl);
    }

    static void trace(Object object) {
    }

    static void dump(HTTPResponse hTTPResponse) {
        try {
            XsHttp.log(hTTPResponse.toString() + '\n' + new String(hTTPResponse.getData()) + "   -----***-----");
        }
        catch (Exception exception) {
            // empty catch block
        }
    }
}

