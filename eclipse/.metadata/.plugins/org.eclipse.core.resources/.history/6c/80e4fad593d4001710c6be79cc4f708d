/*
 * Decompiled with CFR 0_123.
 */
package ixos.xsadmx;

public class AdmsRpcException
extends Exception {
    public static final int ADMS_RPC_ERROR = -1;
    public static final int ADMS_OK = 0;
    public static final int ADMS_ERROR = 1;
    private int _errorCode;

    public AdmsRpcException(int n) {
        super("no error text");
        this._errorCode = n;
    }

    public AdmsRpcException(int n, String string) {
        super(string);
        this._errorCode = n;
    }

    public final int getErrorCode() {
        return this._errorCode;
    }
}

