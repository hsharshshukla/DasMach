/*
 * Decompiled with CFR 0_123.
 */
package ixos.xshttp;

import ixos.xshttp.HTTPResponse;
import ixos.xshttp.NVPair;

public interface Connection {
    public static final int HTTP_HOST_UNKNOWN = 544;
    public static final int HTTP_TOMCAT_UNAVAILABLE = 545;
    public static final int HTTP_APACHE_UNAVAILABLE = 546;

    public HTTPResponse post(String var1, String var2, int var3, String var4, NVPair[] var5, NVPair[] var6) throws Exception;

    public HTTPResponse get(String var1, String var2, int var3, String var4, NVPair[] var5, NVPair[] var6) throws Exception;

    public void setTimeout(int var1);
}

