/*
 * Decompiled with CFR 0_123.
 */
package ixos.xsutil;

import ixos.xsutil.HercOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

class DebugOutputStream
extends OutputStream {
    private static OutputStream herc;
    private boolean hercEnabled;
    private OutputStream stderr;

    DebugOutputStream(OutputStream outputStream) {
        this.stderr = outputStream;
    }

    @Override
    public void write(int n) throws IOException {
        if (herc != null && this.hercEnabled) {
            herc.write(n);
        }
        if (this.stderr != null) {
            this.stderr.write(n);
        }
    }

    void enableHercules(boolean bl) {
        if (!this.hercEnabled && bl && herc == null) {
            try {
                Class<HercOutputStream> class_ = HercOutputStream.class;
                herc = class_.newInstance();
            }
            catch (Throwable throwable) {
                System.err.println("*** cannot load HercOutputStream");
                System.err.println("*** " + throwable);
                throwable.printStackTrace();
            }
        }
        this.hercEnabled = bl;
    }
}

