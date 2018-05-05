/*
 * Decompiled with CFR 0_123.
 */
package ixos.xsadmx;

import ixos.xsadmx.AdmxException;
import ixos.xsadmx.AdmxParser;
import ixos.xsadmx.AdmxRpc;
import ixos.xsadmx.AdmxRpcException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.util.Date;

public class Console {
    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void main(String[] arrstring) {
        AdmxRpc admxRpc;
        Object object;
        String string;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        PrintStream printStream = System.out;
        if (arrstring.length != 1) {
            printStream.println("usage: java ixos.xsadmx.Console hostname");
            return;
        }
        try {
            admxRpc = new AdmxRpc(arrstring[0]);
        }
        catch (AdmxRpcException admxRpcException) {
            printStream.println("Error: " + admxRpcException);
            return;
        }
        printStream.println("Connected.");
        printStream.println("");
        try {
            object = new AdmxParser(admxRpc.command("cf_get_server_info"));
            printStream.println("Admin Server: ");
            printStream.println("   version: " + object.getWord(0, 0) + " " + object.getWord(0, 1));
            printStream.println("   started at: " + new Date(1000 * (long)Integer.parseInt(object.getWord(0, 2))));
            printStream.println("   comments: " + object.getWord(0, 3));
            printStream.println("   xsadmx protocol ID: " + object.getWord(0, 4));
            printStream.println("");
        }
        catch (AdmxException admxException) {
            printStream.println("Error: " + admxException.getMessage());
        }
        do {
            try {
                printStream.print("username: ");
                printStream.flush();
                object = bufferedReader.readLine().trim();
                if (!"".equals(object)) {
                    printStream.print("password: ");
                    printStream.flush();
                    string = bufferedReader.readLine();
                    admxRpc.logon((String)object, string);
                }
            }
            catch (AdmxRpcException admxRpcException) {
                printStream.println("Error: " + admxRpcException.getMessage());
                continue;
            }
            break;
        } while (true);
        catch (IOException iOException) {
            iOException.printStackTrace();
            return;
        }
        printStream.println("Type \"exit\" to quit");
        do {
            printStream.print("> ");
            printStream.flush();
            try {
                object = bufferedReader.readLine().trim();
                if ("exit".equals(object)) {
                    admxRpc.disconnect();
                    return;
                }
                string = admxRpc.command((String)object);
                printStream.println(string);
                continue;
            }
            catch (AdmxRpcException admxRpcException) {
                printStream.println("Error: " + admxRpcException.getMessage());
                continue;
            }
            break;
        } while (true);
        catch (IOException iOException) {
            iOException.printStackTrace();
            return;
        }
    }
}

