/*
 * Decompiled with CFR 0_123.
 */
package ixos.xsutil;

import ixos.xsutil.DebugOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

public class XsDebug {
    public static PrintWriter logWriter;
    public static PrintWriter traceWriter;
    private static DebugOutputStream dbgOutput;
    private boolean logEnabled = false;
    private boolean traceEnabled = false;

    public void _enableLog(boolean bl) {
        this.logEnabled = bl;
    }

    public boolean isLogEnabled() {
        return this.logEnabled;
    }

    public void _enableTrace(boolean bl) {
        this.traceEnabled = bl;
    }

    public boolean isTraceEnabled() {
        return this.traceEnabled;
    }

    public static void enableHercules(boolean bl) {
        if (dbgOutput == null && bl) {
            dbgOutput = new DebugOutputStream(System.err);
            System.setErr(new PrintStream(dbgOutput));
            logWriter = new PrintWriter(dbgOutput, true);
            traceWriter = new PrintWriter(dbgOutput, true);
        }
        if (dbgOutput != null) {
            dbgOutput.enableHercules(bl);
        }
    }

    public void enableAll(boolean bl) {
        XsDebug.enableHercules(bl);
        this._enableLog(bl);
        this._enableTrace(bl);
    }

    public void _log(Object object) {
        if (this.logEnabled) {
            String string = object == null ? "<null>" : object.toString();
            logWriter.println(string);
            logWriter.flush();
        }
    }

    public void _trace(Object object) {
        if (this.traceEnabled) {
            String string = object == null ? "<null>" : object.toString();
            traceWriter.println(string);
            traceWriter.flush();
        }
    }

    static {
        dbgOutput = null;
        logWriter = new PrintWriter(System.err, true);
        traceWriter = new PrintWriter(System.err, true);
    }
}

