/*
 * Decompiled with CFR 0_123.
 */
package ixos.xsrpc;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

public class Version {
    private static final String version = "10.5.0";
    private static final String timeStamp = "2014/09/25 20:51:54 +0200 CEST";
    private static final String revision = "10.5.0.649";

    public static String getName() {
        return "ixos.xsrpc";
    }

    public static String getTimestamp() {
        return "2014/09/25 20:51:54 +0200 CEST";
    }

    public static String getVersion() {
        return "10.5.0 of 2014/09/25 20:51:54 +0200 CEST";
    }

    public static String getRevision() {
        return "10.5.0.649";
    }

    public static String getCopyright() {
        return "Copyright (c) 1997-2000 IXOS Software AG. All rights reserved.";
    }

    public static void main(String[] arrstring) {
        PrintWriter printWriter = new PrintWriter(System.out);
        printWriter.println(Version.getName() + " " + Version.getVersion());
        printWriter.println(Version.getCopyright());
        printWriter.flush();
    }
}

