/*
 * Decompiled with CFR 0_123.
 */
package ixos.xshttp;

import ixos.xshttp.HTTPResponse;

public class Result {
    public int error = -1;
    public String buf;
    static final boolean _debug = false;

    public Result(HTTPResponse hTTPResponse, String string) throws Exception {
        this(hTTPResponse, string, false);
    }

    public Result(HTTPResponse hTTPResponse, String string, boolean bl) throws Exception {
        try {
            switch (hTTPResponse.getStatusCode()) {
                case 200: {
                    this.buf = new String(hTTPResponse.getData());
                    this.error = 0;
                    break;
                }
                default: {
                    if (bl) {
                        this.buf = new String(hTTPResponse.getData());
                        if (this.buf == null || this.buf.length() == 0) {
                            this.buf = new String(hTTPResponse.getHeader(string));
                        }
                    } else {
                        this.buf = new String(hTTPResponse.getHeader(string));
                    }
                    this.error = hTTPResponse.getStatusCode();
                }
            }
            this.buf = this.buf.replace('\r', ' ');
            while (this.buf.endsWith("\n")) {
                this.buf = this.buf.substring(0, this.buf.length() - 1);
            }
            if (this.error > 0) {
                this.buf = this.buf.substring(0, 1).toUpperCase() + this.buf.substring(1);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
            this.error = 1;
            this.buf = exception.getMessage();
            throw new Exception(exception.getMessage());
        }
    }
}

