/*
 * Decompiled with CFR 0_123.
 */
package ixos.xsrpc;

import ixos.xsrpc.BlockingCallback;
import ixos.xsrpc.RpcException;
import ixos.xsrpc.XdrData;
import ixos.xsrpc.XdrProc;

public abstract class Client {
    protected BlockingCallback _blockingCallback = null;
    protected int _blockingCallbackInterval = 5000;

    public abstract void cl_call(int var1, XdrProc var2, XdrData var3, XdrProc var4, XdrData var5, int var6) throws RpcException;

    public abstract void cl_destroy();

    public abstract void setTimeout(int var1);

    public BlockingCallback getBlockingCallback() {
        return this._blockingCallback;
    }

    public void setBlockingCallback(BlockingCallback blockingCallback) {
        this._blockingCallback = blockingCallback;
    }

    public int getBlockingCallbackInterval() {
        return this._blockingCallbackInterval;
    }

    public void setBlockingCallbackInterval(int n) {
        this._blockingCallbackInterval = n;
    }
}

