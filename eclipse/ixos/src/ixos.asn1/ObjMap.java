/*
 * Decompiled with CFR 0_123.
 */
package ixos.asn1;

import java.io.PrintStream;

public class ObjMap {
    protected int num;
    protected ObjMap sibling;
    protected ObjMap desc;
    public String name;
    public Object info;
    private static ObjMap root = null;
    private static String[] table = new String[]{"1 2 840 10040 1", "ANSI X9.57", "1 2 840 10040 1 1", "x9f1-cert-mgmt", "1 2 840 10040 2", "ANSI X9.57-holdinstruction", "1 2 840 10040 2 1", "none", "1 2 840 10040 2 2", "callissuer", "1 2 840 10040 2 3", "reject", "1 2 840 10040 2 4", "pickupToken", "1 2 840 10040 3", "ANSI X9.57-attribute", "1 2 840 10040 3 1", "countersignature", "1 2 840 10040 3 2", "attribute-cert", "1 2 840 10040 4", "ANSI X9.57-algorithm", "1 2 840 10040 4 1", "dsa", "1 2 840 10040 4 2", "dsa-match", "1 2 840 10040 4 3", "dsaWithSHA1", "1 2 840 10045 4", "ANSI X9.62", "1 2 840 10045 4 1", "ecdsaWithSHA1", "1 2 840 113549", "rsadsi", "1 2 840 113549 1", "pkcs", "1 2 840 113549 1 1", "PKCS1", "1 2 840 113549 1 1 1", "rsaEncryption", "1 2 840 113549 1 1 2", "md2WithRSAEncryption", "1 2 840 113549 1 1 3", "md4WithRSAEncryption", "1 2 840 113549 1 1 4", "md5WithRSAEncryption", "1 2 840 113549 1 1 5", "sha1WithRSAEncryption", "1 2 840 113549 1 3 1", "DHKeyAgreement", "1 2 840 113549 1 3", "PKCS3", "1 2 840 113549 1 3 1", "dhKeyAgreement", "1 2 840 113549 1 5 1", "pbeWithMD2AndDES-CBC", "1 2 840 113549 1 5 3", "pbeWithMD5AndDES-CBC", "1 2 840 113549 1 7", "PKCS7", "1 2 840 113549 1 7 1", "Data", "1 2 840 113549 1 7 2", "signedData", "1 2 840 113549 1 7 3", "envelopData", "1 2 840 113549 1 7 4", "signedEnvelopData", "1 2 840 113549 1 7 5", "digestedData", "1 2 840 113549 1 7 6", "encData", "1 2 840 113549 1 9", "PKCS9", "1 2 840 113549 1 9 1", "emailAddr", "1 2 840 113549 1 9 2", "unstructName", "1 2 840 113549 1 9 3", "contentType", "1 2 840 113549 1 9 4", "messDigest", "1 2 840 113549 1 9 5", "signTime", "1 2 840 113549 1 9 6", "counterSig", "1 2 840 113549 1 9 7", "chalPasswd", "1 2 840 113549 1 9 8", "unstructAddr", "1 2 840 113549 1 9 9", "extCertAttribs", "1 2 840 113549 2 2", "MD2", "1 2 840 113549 2 4", "MD4", "1 2 840 113549 2 5", "MD5", "1 2 840 113549 3 2", "rc2CBC", "1 2 840 113549 3 4", "rc4", "1 2 840 113549 3 7", "DES-EDE3-CBC", "1 3 14 3 2", "Algorithm", "1 3 14 3 2 1", "", "1 3 14 3 2 2", "MD4-RSA", "1 3 14 3 2 3", "MD5-RSA", "1 3 14 3 2 4", "MD4-RSA", "1 3 14 3 2 5", "", "1 3 14 3 2 6", "DES-ECB", "1 3 14 3 2 7", "DES-CBC", "1 3 14 3 2 8", "DES-OFB", "1 3 14 3 2 9", "DES-CFB", "1 3 14 3 2 10", "", "1 3 14 3 2 11", "", "1 3 14 3 2 12", "DSA-old", "1 3 14 3 2 13", "DSA-SHA", "1 3 14 3 2 14", "", "1 3 14 3 2 15", "shaWithRSAEncryption", "1 3 14 3 2 16", "DHwithCommonModulus", "1 3 14 3 2 17", "DES-EDE", "1 3 14 3 2 18", "SHA", "1 3 14 3 2 19", "", "1 3 14 3 2 26", "SHA-1", "1 3 14 3 2 27", "DSA-SHA1-old", "1 3 14 3 2 29", "sha1WithRSAEncryption", "1 3 14 7 2 3 1", "MD2-RSA", "1 3 36 3 1 1 4 1 1", "DES-CBC-ISO1", "1 3 36 3 1 1 4 1 2", "DES-CBC-ISO0", "1 3 36 3 1 3 2", "DES3-CBC", "1 3 36 3 1 3 4 1 1", "DES-CBC3-ISO1", "1 3 36 3 1 3 4 1 2", "DES-CBC3-ISO0", "1 3 36 3 1 5", "DES-CBC-PAD", "1 3 36 3 1 11", "DES-CBC3", "1 3 36 3 1 13", "DES_CBC3-PAD", "1 3 36 3 1 20", "DSA-SK", "1 3 36 3 1 22", "MD2-RSA-TIMEDATE", "1 3 36 3 1 24", "MD4-RSA-TIMEDATE", "1 3 36 3 1 25", "MD5-RSA-TIMEDATE", "1 3 36 3 1 26", "DES-CBC-NOPAD", "1 3 36 3 1 30", "IDEA", "1 3 36 3 2 1", "RIPEMD-160", "1 3 36 3 3 1 2", "ripemd160WithRSAEncryption", "1 3 36 8", "Sigi", "2 5", "X500", "2 5 4", "X509", "2 5 4 3", "CN=", "2 5 4 5", "SN=", "2 5 4 6", "C=", "2 5 4 7", "L=", "2 5 4 8", "ST=", "2 5 4 10", "O=", "2 5 4 11", "OU=", "2 5 4 15", "BC=", "2 5 4 17", "PC=", "2 5 8 1 1", "RSA", "2 5 8 2 1", "SQMODN", "2 5 8 3 1", "SQMODN_RSA", "2 5 29", "X509v3", "2 5 29 14", "", "2 5 29 15", "keyUsage", "2 5 29 19", "basicConstraints", "2 5 29 32", "certificatePolicies", "2 5 29 35", "authorityKeyIdentifier", "2 23 42 9 11 4", "Certicom", "2 23 42 9 11 4 1", "ECDSA"};

    private ObjMap(int n, String string, ObjMap objMap, ObjMap objMap2, Object object) {
        this.num = n;
        this.name = string;
        this.sibling = objMap;
        this.desc = objMap2;
        this.info = object;
    }

    public static void addNode(int[] arrn, String string, Object object) {
        if (root == null) {
            root = ObjMap.mkNode(arrn, 0, string, object);
            return;
        }
        ObjMap objMap = root;
        int n = arrn.length;
        for (int i = 0; i < n; ++i) {
            int n2 = arrn[i];
            while (objMap.num != n2 && objMap.desc != null) {
                objMap = objMap.desc;
            }
            if (objMap.num == n2) {
                if (i == n - 1) {
                    objMap.name = string;
                    if (object != null) {
                        objMap.info = object;
                    }
                    return;
                }
                if (objMap.sibling == null) {
                    objMap.sibling = ObjMap.mkNode(arrn, i + 1, string, object);
                    return;
                }
            } else {
                objMap.desc = ObjMap.mkNode(arrn, i, string, object);
                return;
            }
            objMap = objMap.sibling;
        }
    }

    private static ObjMap mkNode(int[] arrn, int n, String string, Object object) {
        String string2;
        ObjMap objMap;
        Object object2;
        if (n == arrn.length) {
            System.err.println("ObjMap internal error");
            return null;
        }
        if (n == arrn.length - 1) {
            string2 = string;
            object2 = object;
            objMap = null;
        } else {
            string2 = null;
            object2 = null;
            objMap = ObjMap.mkNode(arrn, n + 1, string, object);
        }
        return new ObjMap(arrn[n], string2, objMap, null, object2);
    }

    public static String getName(int[] arrn) {
        String string = "";
        ObjMap objMap = root;
        int n = arrn.length;
        for (int i = 0; i < n; ++i) {
            int n2 = arrn[i];
            while (objMap.num != n2 && objMap.desc != null) {
                objMap = objMap.desc;
            }
            if (objMap.num == n2) {
                if (objMap.name != null) {
                    string = string.length() == 0 ? objMap.name : string + "." + objMap.name;
                }
                if (i == n - 1) {
                    return string;
                }
                if (objMap.sibling == null) {
                    return null;
                }
            } else {
                return null;
            }
            objMap = objMap.sibling;
        }
        return null;
    }

    public static ObjMap getNode(int[] arrn) {
        ObjMap objMap = root;
        int n = arrn.length;
        for (int i = 0; i < n; ++i) {
            int n2 = arrn[i];
            while (objMap.num != n2 && objMap.desc != null) {
                objMap = objMap.desc;
            }
            if (objMap.num == n2) {
                if (i == n - 1) {
                    return objMap;
                }
                if (objMap.sibling == null) {
                    return null;
                }
            } else {
                return null;
            }
            objMap = objMap.sibling;
        }
        return null;
    }

    private static int[] s2arr(String string) {
        int[] arrn = new int[10];
        int n = 0;
        int n2 = 0;
        int n3 = -1;
        int n4 = string.length();
        while (n2 < n4) {
            char c;
            if ((c = string.charAt(n2++)) == ' ' && n3 != -1) {
                arrn[n++] = n3;
                n3 = -1;
                continue;
            }
            if (n3 == -1) {
                n3 = c - 48;
                continue;
            }
            n3 = n3 * 10 + c - 48;
        }
        if (n3 != -1) {
            arrn[n++] = n3;
        }
        int[] arrn2 = new int[n];
        System.arraycopy(arrn, 0, arrn2, 0, n);
        return arrn2;
    }

    public static void main(String[] arrstring) {
        for (int i = 0; i < table.length; i += 2) {
            int[] arrn = ObjMap.s2arr(table[i]);
            String string = ObjMap.getName(arrn);
            System.out.println(table[i] + ": expected:" + table[i + 1] + " got:" + string);
        }
    }

    static {
        for (int i = 0; i < table.length; i += 2) {
            int[] arrn = ObjMap.s2arr(table[i]);
            ObjMap.addNode(arrn, table[i + 1], null);
        }
    }
}

