/*
 * Decompiled with CFR 0_123.
 */
package ixos.xsutil;

import java.io.OutputStream;
import java.io.PrintStream;

class HercOutputStream
extends OutputStream {
    private static boolean xsWinHercLoaded = false;

    HercOutputStream() {
    }

    @Override
    public void write(int n) {
        if (xsWinHercLoaded) {
            try {
                HercOutputStream.writeWinHerc(n);
            }
            catch (Throwable throwable) {
                System.err.println("*** HercOutputStream cannot call native method writeWinHerc");
                throwable.printStackTrace();
            }
        }
    }

    private static native boolean loadWinHerc();

    private static native void writeWinHerc(int var0);

    static {
        System.loadLibrary("HercOutputStream");
        if (HercOutputStream.loadWinHerc()) {
            xsWinHercLoaded = true;
        } else {
            System.err.println("*** HercOutputStream failed to load WINHERC.DLL");
        }
    }
}

