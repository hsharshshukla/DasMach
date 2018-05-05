/*
 * Decompiled with CFR 0_123.
 */
package ixos.xshttp;

import ixos.xshttp.NVPair;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class HTTPResponse {
    private final boolean DEBUG = false;
    private int _statusCode;
    private ArrayList _headers;
    private String _data;

    public HTTPResponse(int n, ArrayList arrayList, String string) {
        this._statusCode = n;
        this._headers = arrayList;
        this._data = string;
    }

    HTTPResponse(int n, ArrayList arrayList, ArrayList arrayList2, String string) {
        this._statusCode = n;
        this._headers = arrayList;
        this._headers.addAll(arrayList2);
        this._data = string;
    }

    public int getStatusCode() {
        return this._statusCode;
    }

    public String getData() {
        return this._data;
    }

    public String getHeader(String string) {
        Iterator iterator = this.listHeaders();
        while (iterator.hasNext()) {
            NVPair nVPair = (NVPair)iterator.next();
            if (!nVPair.getName().equals(string)) continue;
            return nVPair.getValue();
        }
        return null;
    }

    Iterator listHeaders() {
        return this._headers.iterator();
    }

    public ArrayList getHeaders() {
        return this._headers;
    }
}

