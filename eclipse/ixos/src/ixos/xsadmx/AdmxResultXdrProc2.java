/*
 * Decompiled with CFR 0_123.
 */
package ixos.xsadmx;

import ixos.xsadmx.AdmxResult2;
import ixos.xsrpc.Xdr;
import ixos.xsrpc.XdrData;
import ixos.xsrpc.XdrProc;

class AdmxResultXdrProc2
extends XdrProc {
    AdmxResultXdrProc2() {
    }

    @Override
    public boolean xdrproc(Xdr xdr, XdrData xdrData) {
        AdmxResult2 admxResult2 = (AdmxResult2)xdrData.objValue;
        if (admxResult2 == null) {
            admxResult2 = new AdmxResult2();
        }
        XdrData xdrData2 = new XdrData();
        xdrData2.intValue = admxResult2.error;
        if (!xdr.xdr_int(xdrData2)) {
            return false;
        }
        admxResult2.error = xdrData2.intValue;
        xdrData2.strValue = admxResult2.buf;
        if (!xdr.xdr_wrapstring(xdrData2)) {
            return false;
        }
        admxResult2.buf = xdrData2.strValue;
        admxResult2.bt = xdrData2.byteValue;
        xdrData.objValue = admxResult2;
        return true;
    }
}

