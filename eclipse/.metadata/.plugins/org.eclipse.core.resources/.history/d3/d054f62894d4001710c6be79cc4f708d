/*
 * Decompiled with CFR 0_123.
 */
package ixos.xsadmx;

import ixos.xsadmx.AdmxParserException;
import java.util.StringTokenizer;
import java.util.Vector;

public class AdmxParser {
    private Vector m_lines;
    public static final char REC_SEP = '\n';
    public static final char FIELD_SEP = ' ';
    public static final String NO_VAL = "^";
    public static final char FIELD_LPAR = '{';
    public static final char FIELD_RPAR = '}';

    public AdmxParser() {
    }

    public AdmxParser(String string) throws AdmxParserException {
        this.parse(string);
    }

    public void parse(String string) throws AdmxParserException {
        this.m_lines = new Vector(10, 10);
        StringTokenizer stringTokenizer = new StringTokenizer(string, "\n");
        while (stringTokenizer.hasMoreTokens()) {
            String string2 = stringTokenizer.nextToken().trim();
            if ("".equals(string2)) continue;
            this.m_lines.addElement(new Line(string2));
        }
    }

    public int getLineCount() {
        return this.m_lines.size();
    }

    public int getWordCount(int n) {
        return ((Line)this.m_lines.elementAt(n)).getWordCount();
    }

    public String getWord(int n, int n2) {
        return ((Line)this.m_lines.elementAt(n)).getWord(n2);
    }

    class Line {
        private Vector m_words;

        Line(String string) throws AdmxParserException {
            this.m_words = new Vector();
            int n = string.length();
            int n2 = 0;
            while (n2 < n) {
                while (n2 < n && string.charAt(n2) == ' ') {
                    ++n2;
                }
                if (n2 >= n) break;
                StringBuffer stringBuffer = new StringBuffer();
                char c = string.charAt(n2);
                if (c == '{') {
                    ++n2;
                    int n3 = 0;
                    while (n2 < n && (c = string.charAt(n2)) != '}' || n3 > 0) {
                        stringBuffer.append(c);
                        ++n2;
                        if (c == '{') {
                            ++n3;
                            continue;
                        }
                        if (c != '}') continue;
                        --n3;
                    }
                    if (n2 > n) {
                        throw new AdmxParserException();
                    }
                    this.m_words.addElement(stringBuffer.toString());
                    ++n2;
                    continue;
                }
                while (n2 < n && (c = string.charAt(n2)) != ' ') {
                    stringBuffer.append(c);
                    ++n2;
                }
                String string2 = stringBuffer.toString();
                this.m_words.addElement(string2.equals("^") ? "" : string2);
            }
        }

        int getWordCount() {
            return this.m_words.size();
        }

        String getWord(int n) {
            return (String)this.m_words.elementAt(n);
        }
    }

}

