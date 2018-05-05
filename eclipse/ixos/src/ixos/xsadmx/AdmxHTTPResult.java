/*
 * Decompiled with CFR 0_123.
 */
package ixos.xsadmx;

import ixos.xsadmx.AdmxRpcException;
import ixos.xshttp.HTTPResponse;

class AdmxHTTPResult {
    int error = -1;
    String buf;
    String sid;
    static final boolean _debug = false;

    AdmxHTTPResult(HTTPResponse hTTPResponse) throws AdmxRpcException {
        try {
            switch (hTTPResponse.getStatusCode()) {
                case 200: {
                    this.buf = new String(hTTPResponse.getData());
                    this.sid = hTTPResponse.getHeader("Adms-SessionId");
                    this.error = 0;
                    break;
                }
                case 400: {
                    this.buf = hTTPResponse.getHeader("Adms-ErrTxt");
                    if (this.buf != null) {
                        this.error = new Integer(hTTPResponse.getHeader("Adms-ErrNo"));
                    } else {
                        this.buf = hTTPResponse.getHeader("ASConf-ErrTxt");
                        if (this.buf != null) {
                            if (this.buf.startsWith("Invalid Value(s):")) {
                                this.buf = new String(hTTPResponse.getData());
                            }
                            this.error = new Integer(hTTPResponse.getHeader("ASConf-ErrNo"));
                        } else {
                            this.buf = new String(hTTPResponse.getData());
                            this.error = 400;
                        }
                    }
                    if (this.buf.indexOf("Unknown session id") <= -1) break;
                    this.buf = "Network communication with server failed. Do you want to disconnect?";
                    break;
                }
                case 500: {
                    this.buf = new String(hTTPResponse.getData());
                    this.error = 500;
                    throw new AdmxRpcException("Network communication with server failed. Do you want to disconnect?");
                }
                case 544: {
                    this.buf = "An UnknownHostException occurred.";
                    this.error = 544;
                    break;
                }
                case 545: {
                    this.buf = "The servlet is not available.";
                    this.error = 545;
                    break;
                }
                case 546: {
                    this.buf = "The servlet is not available.";
                    this.error = 546;
                    break;
                }
                default: {
                    this.buf = new String(hTTPResponse.getData());
                    this.error = 500;
                }
            }
            while (this.buf.endsWith("\n") || this.buf.endsWith("\r")) {
                this.buf = this.buf.substring(0, this.buf.length() - 1);
            }
            if (this.error > 0 && this.buf.length() > 0) {
                this.buf = this.buf.substring(0, 1).toUpperCase() + this.buf.substring(1);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
            this.error = 1;
            this.buf = exception.getMessage();
            throw new AdmxRpcException(exception.getMessage());
        }
    }
}

