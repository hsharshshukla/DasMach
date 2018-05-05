/*
 * Decompiled with CFR 0_123.
 */
package ixos.xsadmx;

import ixos.xsadmx.AdmxParserException;
import java.io.PrintStream;
import java.util.Vector;

public class Admx {
    public static final int TCL_DONT_USE_BRACES = 1;
    public static final int USE_BRACES = 2;
    public static final int BRACES_UNMATCHED = 4;
    static boolean _debug = false;

    public static void splitList(Vector vector, String string) throws AdmxParserException {
        FindElemResult findElemResult;
        if (_debug) {
            System.err.println("Split list called: " + string);
        }
        int n = string.length();
        int n2 = 0;
        while (n2 < n && (findElemResult = Admx.findElement(string, n2, n)) != null) {
            vector.addElement(findElemResult.elem);
            n2 = findElemResult.elemEnd;
        }
    }

    public static String appendElement(String string, String string2) {
        StringBuffer stringBuffer = new StringBuffer(string);
        Admx.appendElement(stringBuffer, string2);
        return stringBuffer.toString();
    }

    public static void appendElement(StringBuffer stringBuffer, String string) {
        if (stringBuffer.length() > 0) {
            stringBuffer.append(' ');
        }
        int n = Admx.scanElement(string);
        stringBuffer.append(Admx.convertElement(string, n));
    }

    static BackSlashResult backslash(char[] arrc, int n) {
        int n2 = arrc.length;
        if (++n == n2--) {
            return new BackSlashResult('\\', n);
        }
        char c = arrc[n];
        switch (c) {
            case 'a': {
                return new BackSlashResult('\u0007', n + 1);
            }
            case 'b': {
                return new BackSlashResult('\b', n + 1);
            }
            case 'f': {
                return new BackSlashResult('\f', n + 1);
            }
            case 'n': {
                return new BackSlashResult('\n', n + 1);
            }
            case 'r': {
                return new BackSlashResult('\r', n + 1);
            }
            case 't': {
                return new BackSlashResult('\t', n + 1);
            }
            case 'v': {
                return new BackSlashResult('\u000b', n + 1);
            }
            case '\\': {
                return new BackSlashResult('\\', n + 1);
            }
            case '\n': 
            case '\r': {
                if (c == '\r' && n + 1 < n2 && arrc[n + 1] == '\n') {
                    ++n;
                }
                do {
                    c = arrc[++n];
                } while (n < n2 && (c == ' ' || c == '\t'));
                return new BackSlashResult(' ', n);
            }
            case '\u0000': {
                return new BackSlashResult('\\', n + 1);
            }
        }
        if (c >= '0' && c <= '9') {
            int n3 = c - 48;
            if (++n < n2 && (c = arrc[n]) >= '0' && c <= '9') {
                n3 = n3 * 8 + (c - 48);
                if (++n != n2 && (c = arrc[n]) >= '0' && c <= '9') {
                    n3 = n3 * 8 + (c - 48);
                    ++n;
                }
            }
            return new BackSlashResult((char)(n3 & 255), n);
        }
        return new BackSlashResult(c, n + 1);
    }

    private static final FindElemResult findElement(String string, int n, int n2) throws AdmxParserException {
        char c;
        int n3 = 0;
        boolean bl = false;
        if (_debug) {
            System.err.println("findElement (" + string);
        }
        if (_debug) {
            System.err.println(", " + n + ", " + n2 + ")");
        }
        while (n < n2) {
            c = string.charAt(n);
            if (_debug) {
                System.err.println("Testing char: " + c);
            }
            if (c == '\n') {
                if (_debug) {
                    System.err.println("Found NEWLINE!");
                }
                return new FindElemResult(n + 1, "\n");
            }
            if (!Character.isWhitespace(c)) {
                if (!_debug) break;
                System.err.println("Break it out");
                break;
            }
            ++n;
        }
        if (n >= n2) {
            return null;
        }
        c = string.charAt(n);
        if (c == '{') {
            n3 = 1;
            ++n;
        } else if (c == '\"') {
            bl = true;
            ++n;
        }
        StringBuffer stringBuffer = new StringBuffer();
        block8 : do {
            if (n >= n2) {
                if (n3 != 0) {
                    throw new AdmxParserException("unmatched open brace in list");
                }
                if (bl) {
                    throw new AdmxParserException("unmatched open quote in list");
                }
                return new FindElemResult(n, stringBuffer.toString());
            }
            c = string.charAt(n);
            switch (c) {
                case '{': {
                    if (n3 != 0) {
                        ++n3;
                    }
                    stringBuffer.append(c);
                    ++n;
                    continue block8;
                }
                case '}': {
                    if (n3 == 1) {
                        int n4;
                        if (n == n2 - 1 || Character.isWhitespace(string.charAt(n + 1))) {
                            return new FindElemResult(n + 1, stringBuffer.toString());
                        }
                        for (n4 = n + 1; n4 < n2 && !Character.isWhitespace(string.charAt(n4)); ++n4) {
                        }
                        throw new AdmxParserException("list element in braces followed by \"" + string.substring(n + 1, n4) + "\" instead of space");
                    }
                    if (n3 != 0) {
                        --n3;
                    }
                    stringBuffer.append(c);
                    ++n;
                    continue block8;
                }
                case '\\': {
                    if (_debug) {
                        System.err.println("WARNING \\ FINDS");
                    }
                    if (n3 > 0) {
                        stringBuffer.append(c);
                        if (++n >= n2) continue block8;
                        c = string.charAt(n);
                        stringBuffer.append(c);
                        ++n;
                        continue block8;
                    }
                    BackSlashResult backSlashResult = Admx.backslash(string.toCharArray(), n);
                    stringBuffer.append(backSlashResult.c);
                    n = backSlashResult.nextIndex;
                    continue block8;
                }
                case '\t': 
                case '\n': 
                case '\f': 
                case '\r': 
                case ' ': {
                    if (n3 == 0 && !bl) {
                        return new FindElemResult(n, stringBuffer.toString());
                    }
                    stringBuffer.append(c);
                    ++n;
                    continue block8;
                }
                case '\"': {
                    if (bl) {
                        int n5;
                        if (n == n2 - 1 || Character.isWhitespace(string.charAt(n + 1))) {
                            return new FindElemResult(n + 1, stringBuffer.toString());
                        }
                        for (n5 = n + 1; n5 < n2 && !Character.isWhitespace(string.charAt(n5)); ++n5) {
                        }
                        throw new AdmxParserException("list element in quotes followed by \"" + string.substring(n + 1, n5) + "\" instead of space");
                    }
                    stringBuffer.append(c);
                    ++n;
                    continue block8;
                }
            }
            stringBuffer.append(c);
            ++n;
        } while (true);
    }

    public static int scanElement(String string) {
        char c;
        boolean bl = _debug;
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = string.length();
        if (n4 == 0) {
            string = String.valueOf('\u0000');
        }
        if (bl) {
            System.out.println("scanElement string is \"" + string + "\"");
        }
        if ((c = string.charAt(n3)) == '{' || c == '\"' || c == '\u0000') {
            n2 |= 2;
        }
        while (n3 < n4) {
            if (bl) {
                System.out.println("getting char at index " + n3);
                System.out.println("char is '" + string.charAt(n3) + "'");
            }
            c = string.charAt(n3);
            switch (c) {
                case '{': {
                    ++n;
                    break;
                }
                case '}': {
                    if (--n >= 0) break;
                    n2 |= 5;
                    break;
                }
                case '\t': 
                case '\n': 
                case '\u000b': 
                case '\f': 
                case '\r': 
                case ' ': 
                case '$': 
                case ';': 
                case '[': {
                    n2 |= 2;
                    break;
                }
                case '\\': {
                    if (n3 >= n4 - 1 || string.charAt(n3 + 1) == '\n') {
                        n2 = 5;
                        break;
                    }
                    BackSlashResult backSlashResult = Admx.backslash(string.toCharArray(), n3);
                    n3 = backSlashResult.nextIndex - 1;
                    n2 |= 2;
                }
            }
            ++n3;
        }
        if (n != 0) {
            n2 = 5;
        }
        return n2;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static String convertElement(String var0, int var1_1) {
        var2_2 = 0;
        var4_3 = var0.length();
        if (var0 == null) return "{}";
        if (var0.length() == 0) return "{}";
        if (var0.charAt(0) == '\u0000') {
            return "{}";
        }
        var5_4 = new StringBuffer();
        if ((var1_1 & 2) != 0 && (var1_1 & 1) == 0) {
            var5_4.append('{');
            var2_2 = 0;
            do {
                if (var2_2 >= var4_3) {
                    var5_4.append('}');
                    return var5_4.toString();
                }
                var5_4.append(var0.charAt(var2_2));
                ++var2_2;
            } while (true);
        }
        var3_5 = var0.charAt(0);
        if (var3_5 == '{') {
            var5_4.append('\\');
            var5_4.append('{');
            ++var2_2;
            var1_1 |= 4;
        }
        while (var2_2 < var4_3) {
            var3_5 = var0.charAt(var2_2);
            switch (var3_5) {
                case ' ': 
                case '\"': 
                case '$': 
                case ';': 
                case '[': 
                case '\\': 
                case ']': {
                    var5_4.append('\\');
                    break;
                }
                case '{': 
                case '}': {
                    if ((var1_1 & 4) == 0) break;
                    var5_4.append('\\');
                    break;
                }
                case '\f': {
                    var5_4.append('\\');
                    var5_4.append('f');
                    ** break;
                }
                case '\n': {
                    var5_4.append('\\');
                    var5_4.append('n');
                    ** break;
                }
                case '\r': {
                    var5_4.append('\\');
                    var5_4.append('r');
                    ** break;
                }
                case '\t': {
                    var5_4.append('\\');
                    var5_4.append('t');
                    ** break;
                }
                case '\u000b': {
                    var5_4.append('\\');
                    var5_4.append('v');
                    ** break;
                }
            }
            var5_4.append(var3_5);
lbl55: // 6 sources:
            ++var2_2;
        }
        return var5_4.toString();
    }

    private static class FindElemResult {
        int elemEnd;
        String elem;

        FindElemResult(int n, String string) {
            this.elemEnd = n;
            this.elem = string;
        }
    }

    static class BackSlashResult {
        char c;
        int nextIndex;
        boolean isWordSep;

        BackSlashResult(char c, int n) {
            this.c = c;
            this.nextIndex = n;
            this.isWordSep = false;
        }

        BackSlashResult(char c, int n, boolean bl) {
            this.c = c;
            this.nextIndex = n;
            this.isWordSep = bl;
        }
    }

}

