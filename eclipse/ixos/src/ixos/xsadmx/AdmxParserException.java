/*
 * Decompiled with CFR 0_123.
 */
package ixos.xsadmx;

import ixos.xsadmx.AdmxException;

public class AdmxParserException
extends AdmxException {
    public AdmxParserException() {
        this("Wrong result format");
    }

    public AdmxParserException(String string) {
        super(string);
    }
}

