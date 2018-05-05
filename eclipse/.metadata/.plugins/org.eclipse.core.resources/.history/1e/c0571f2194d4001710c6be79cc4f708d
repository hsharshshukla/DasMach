/*
 * Decompiled with CFR 0_123.
 */
package ixos.xsadmx;

import ixos.xsadmx.Admx;
import ixos.xsadmx.AdmxParser;
import ixos.xsadmx.AdmxParserException;
import java.util.Enumeration;
import java.util.Vector;

public class AdmxListParser
extends AdmxParser {
    private Vector _lines;

    public AdmxListParser() {
    }

    public AdmxListParser(String string) throws AdmxParserException {
        this.parse(string);
    }

    @Override
    public void parse(String string) throws AdmxParserException {
        Vector vector = new Vector();
        Admx.splitList(vector, string);
        this._lines = new Vector();
        Vector<String> vector2 = new Vector<String>();
        Enumeration enumeration = vector.elements();
        while (enumeration.hasMoreElements()) {
            String string2 = (String)enumeration.nextElement();
            if ("\n".equals(string2)) {
                this._lines.addElement(vector2);
                vector2 = new Vector();
                continue;
            }
            vector2.addElement("^".equals(string2) ? "" : string2);
        }
        if (vector2.size() > 0) {
            this._lines.addElement(vector2);
        }
    }

    @Override
    public int getLineCount() {
        return this._lines.size();
    }

    @Override
    public int getWordCount(int n) {
        return ((Vector)this._lines.elementAt(n)).size();
    }

    @Override
    public String getWord(int n, int n2) {
        return (String)((Vector)this._lines.elementAt(n)).elementAt(n2);
    }
}

