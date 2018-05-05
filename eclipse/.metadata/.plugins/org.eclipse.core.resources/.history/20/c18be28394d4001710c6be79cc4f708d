/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  ixos.base.Log
 */
package ixos.xsrpc;

import ixos.base.Log;
import ixos.xsutil.XsDebug;

public class XsRpc {
    private static XsDebug m_dbg = new XsDebug();
    private static final boolean TRACE = false;
    private static char[] hexdigit = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    static final int ntohl(byte[] arrby) {
        return XsRpc.ntohl(arrby, 0);
    }

    static final int byte2int(byte n) {
        int n2 = n;
        if (n2 < 0) {
            n2 += 256;
        }
        return n2;
    }

    static final byte int2byte(int n) {
        if (n > 128) {
            n -= 256;
        }
        return (byte)n;
    }

    static final int ntohl(byte[] arrby, int n) {
        return XsRpc.byte2int(arrby[n]) << 24 | XsRpc.byte2int(arrby[n + 1]) << 16 | XsRpc.byte2int(arrby[n + 2]) << 8 | XsRpc.byte2int(arrby[n + 3]);
    }

    static final void htonl(byte[] arrby, int n, int n2) {
        arrby[n] = XsRpc.int2byte(n2 >> 24 & 255);
        arrby[n + 1] = XsRpc.int2byte(n2 >> 16 & 255);
        arrby[n + 2] = XsRpc.int2byte(n2 >> 8 & 255);
        arrby[n + 3] = XsRpc.int2byte(n2 & 255);
    }

    static void bcopy(byte[] arrby, int n, byte[] arrby2, int n2, int n3) {
        for (int i = 0; i < n3; ++i) {
            arrby2[n2 + i] = arrby[n + i];
        }
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

    public static void dump(byte[] arrby, int n, int n2) {
        Log log = new Log("XsRpc", "dump");
        if (Log.logging((int)Log.level2int((String)"RPC"))) {
            for (int i = 0; i < (n2 + 15) / 16; ++i) {
                int n3;
                int n4;
                StringBuffer stringBuffer = new StringBuffer();
                int n5 = i * 16;
                stringBuffer.append(hexdigit[(n5 & 61440) >> 12]);
                stringBuffer.append(hexdigit[(n5 & 3840) >> 8]);
                stringBuffer.append(hexdigit[(n5 & 240) >> 4]);
                stringBuffer.append(hexdigit[n5 & 15]);
                stringBuffer.append(":  ");
                for (n3 = 0; n3 < 16; ++n3) {
                    if (i * 16 + n3 < n2) {
                        n4 = arrby[n + i * 16 + n3];
                        if (n4 < 0) {
                            n4 += 256;
                        }
                        stringBuffer.append(hexdigit[n4 / 16]).append(hexdigit[n4 % 16]);
                    } else {
                        stringBuffer.append("  ");
                    }
                    stringBuffer.append(" ");
                    if (n3 != 7) continue;
                    stringBuffer.append(" ");
                }
                stringBuffer.append("  ");
                for (n3 = 0; n3 < 16; ++n3) {
                    if (i * 16 + n3 >= n2) continue;
                    n4 = arrby[n + i * 16 + n3];
                    if (n4 < 0) {
                        n4 = (byte)(n4 + 256);
                    }
                    if (n4 >= 32) {
                        stringBuffer.append((char)n4);
                    } else {
                        stringBuffer.append('.');
                    }
                    if (n3 != 7) continue;
                    stringBuffer.append(" ");
                }
                log.msg(13, "\n" + stringBuffer.toString());
            }
        }
    }
}

