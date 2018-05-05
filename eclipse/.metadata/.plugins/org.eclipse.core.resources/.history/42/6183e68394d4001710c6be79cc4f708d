/*
 * Decompiled with CFR 0_123.
 */
package ixos.xsrpc;

public class RpcException
extends Exception {
    public static final int RPC_SUCCESS = 0;
    public static final int RPC_CANTENCODEARGS = 1;
    public static final int RPC_CANTDECODERES = 2;
    public static final int RPC_CANTSEND = 3;
    public static final int RPC_CANTRECV = 4;
    public static final int RPC_TIMEDOUT = 5;
    public static final int RPC_VERSMISMATCH = 6;
    public static final int RPC_AUTHERROR = 7;
    public static final int RPC_PROGUNAVAIL = 8;
    public static final int RPC_PROGVERSMISMATCH = 9;
    public static final int RPC_PROCUNAVAIL = 10;
    public static final int RPC_CANTDECODEARGS = 11;
    public static final int RPC_SYSTEMERROR = 12;
    public static final int RPC_UNKNOWNHOST = 13;
    public static final int RPC_PMAPFAILURE = 14;
    public static final int RPC_PROGNOTREGISTERED = 15;
    public static final int RPC_FAILED = 16;
    private int m_errorCode;

    public RpcException(int n) {
        this.m_errorCode = n;
    }

    public final int getErrorCode() {
        return this.m_errorCode;
    }
}

