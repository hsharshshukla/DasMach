/*
 * Decompiled with CFR 0_123.
 */
package ixos.xsrpc;

import ixos.xsrpc.XdrProc;
import ixos.xsrpc.opaque_auth;

class accepted_reply {
    opaque_auth ar_verf = new opaque_auth();
    int ar_stat;
    AR_versions ar_vers;
    AR_results ar_results;

    accepted_reply() {
        this.ar_vers = new AR_versions();
        this.ar_results = new AR_results();
    }

    class AR_results {
        Object where;
        XdrProc proc;

        AR_results() {
        }
    }

    class AR_versions {
        int low;
        int high;

        AR_versions() {
        }
    }

}

