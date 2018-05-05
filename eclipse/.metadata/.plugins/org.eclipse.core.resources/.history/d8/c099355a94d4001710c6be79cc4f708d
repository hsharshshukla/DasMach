/*
 * Decompiled with CFR 0_123.
 */
package ixos.xsadmx;

import ixos.xsadmx.AdmxResult;
import ixos.xsrpc.Xdr;
import ixos.xsrpc.XdrData;
import ixos.xsrpc.XdrProc;

class AdmxResultXdrProc
extends XdrProc {
    AdmxResultXdrProc() {
    }

    @Override
    public boolean xdrproc(Xdr xdr, XdrData xdrData) {
        AdmxResult admxResult = (AdmxResult)xdrData.objValue;
        if (admxResult == null) {
            admxResult = new AdmxResult();
        }
        XdrData xdrData2 = new XdrData();
        xdrData2.intValue = admxResult.error;
        if (!xdr.xdr_int(xdrData2)) {
            return false;
        }
        admxResult.error = xdrData2.intValue;
        xdrData2.strValue = admxResult.buf;
        if (!xdr.xdr_wrapstring(xdrData2)) {
            return false;
        }
        admxResult.buf = xdrData2.strValue;
        xdrData.objValue = admxResult;
        return true;
    }
}

