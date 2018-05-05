/*
 * Decompiled with CFR 0_123.
 */
package ixos.xsutil;

import java.io.UnsupportedEncodingException;

public class Codecs {
    private static byte[] Base64EncMap;
    private static byte[] Base64DecMap;

    public static final String base64Decode(String string) {
        if (string == null) {
            return null;
        }
        try {
            return new String(Codecs.base64Decode(string.getBytes("8859_1")), "8859_1");
        }
        catch (UnsupportedEncodingException unsupportedEncodingException) {
            throw new Error(unsupportedEncodingException.toString());
        }
    }

    public static final byte[] base64Decode(byte[] arrby) {
        int n;
        int n2;
        if (arrby == null) {
            return null;
        }
        int n3 = arrby.length;
        while (arrby[n3 - 1] == 61) {
            --n3;
        }
        byte[] arrby2 = new byte[n3 - arrby.length / 4];
        for (n2 = 0; n2 < arrby.length; ++n2) {
            arrby[n2] = Base64DecMap[arrby[n2]];
        }
        n2 = 0;
        for (n = 0; n < arrby2.length - 2; n += 3) {
            arrby2[n] = (byte)(arrby[n2] << 2 & 255 | arrby[n2 + 1] >>> 4 & 3);
            arrby2[n + 1] = (byte)(arrby[n2 + 1] << 4 & 255 | arrby[n2 + 2] >>> 2 & 15);
            arrby2[n + 2] = (byte)(arrby[n2 + 2] << 6 & 255 | arrby[n2 + 3] & 63);
            n2 += 4;
        }
        if (n < arrby2.length) {
            arrby2[n] = (byte)(arrby[n2] << 2 & 255 | arrby[n2 + 1] >>> 4 & 3);
        }
        if (++n < arrby2.length) {
            arrby2[n] = (byte)(arrby[n2 + 1] << 4 & 255 | arrby[n2 + 2] >>> 2 & 15);
        }
        return arrby2;
    }

    public static final String base64Encode(String string) {
        if (string == null) {
            return null;
        }
        try {
            return new String(Codecs.base64Encode(string.getBytes("8859_1")), "8859_1");
        }
        catch (UnsupportedEncodingException unsupportedEncodingException) {
            throw new Error(unsupportedEncodingException.toString());
        }
    }

    public static final byte[] base64Encode(byte[] arrby) {
        int n;
        if (arrby == null) {
            return null;
        }
        byte[] arrby2 = new byte[(arrby.length + 2) / 3 * 4];
        int n2 = 0;
        for (n = 0; n < arrby.length - 2; n += 3) {
            arrby2[n2++] = Base64EncMap[arrby[n] >>> 2 & 63];
            arrby2[n2++] = Base64EncMap[arrby[n + 1] >>> 4 & 15 | arrby[n] << 4 & 63];
            arrby2[n2++] = Base64EncMap[arrby[n + 2] >>> 6 & 3 | arrby[n + 1] << 2 & 63];
            arrby2[n2++] = Base64EncMap[arrby[n + 2] & 63];
        }
        if (n < arrby.length) {
            arrby2[n2++] = Base64EncMap[arrby[n] >>> 2 & 63];
            if (n < arrby.length - 1) {
                arrby2[n2++] = Base64EncMap[arrby[n + 1] >>> 4 & 15 | arrby[n] << 4 & 63];
                arrby2[n2++] = Base64EncMap[arrby[n + 1] << 2 & 63];
            } else {
                arrby2[n2++] = Base64EncMap[arrby[n] << 4 & 63];
            }
        }
        while (n2 < arrby2.length) {
            arrby2[n2] = 61;
            ++n2;
        }
        return arrby2;
    }

    static {
        byte[] arrby = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
        Base64EncMap = arrby;
        Base64DecMap = new byte[128];
        for (int i = 0; i < Base64EncMap.length; ++i) {
            Codecs.Base64DecMap[Codecs.Base64EncMap[i]] = (byte)i;
        }
    }
}

