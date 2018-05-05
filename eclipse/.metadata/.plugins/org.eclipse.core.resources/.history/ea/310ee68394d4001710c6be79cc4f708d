/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  ixos.base.Log
 */
package ixos.xsrpc;

import ixos.base.Log;
import ixos.xsrpc.CallMsg;
import ixos.xsrpc.NoneAuth;
import ixos.xsrpc.ReplyMsg;
import ixos.xsrpc.Request;
import ixos.xsrpc.XdrData;
import ixos.xsrpc.XdrProc;
import ixos.xsrpc.accepted_reply;
import ixos.xsrpc.opaque_auth;
import ixos.xsrpc.rejected_reply;
import java.net.InetAddress;

public abstract class Service {
    protected int sc_prog;
    protected int sc_vers;
    private static String[] allowed_hosts = null;
    opaque_auth xp_verf = new NoneAuth();

    protected abstract boolean xp_recv(CallMsg var1);

    protected abstract boolean xp_getargs(XdrProc var1, XdrData var2);

    protected abstract boolean xp_reply(ReplyMsg var1);

    protected abstract void xp_destroy();

    protected abstract void dispatch(Request var1);

    public abstract void runService(boolean var1);

    public abstract void stopService();

    protected Service(int n, int n2) {
        this.sc_prog = n;
        this.sc_vers = n2;
    }

    protected final boolean svc_sendreply(XdrProc xdrProc, XdrData xdrData) {
        ReplyMsg replyMsg = new ReplyMsg();
        replyMsg.rm_direction = 1;
        replyMsg.rp_stat = 0;
        replyMsg.rp_acpt.ar_verf = this.xp_verf;
        replyMsg.rp_acpt.ar_stat = 0;
        replyMsg.rp_acpt.ar_results.where = xdrData;
        replyMsg.rp_acpt.ar_results.proc = xdrProc;
        return this.xp_reply(replyMsg);
    }

    protected final void svcerr_noproc() {
        ReplyMsg replyMsg = new ReplyMsg();
        replyMsg.rm_direction = 1;
        replyMsg.rp_stat = 0;
        replyMsg.rp_acpt.ar_verf = this.xp_verf;
        replyMsg.rp_acpt.ar_stat = 3;
        this.xp_reply(replyMsg);
    }

    protected final void svcerr_decode() {
        ReplyMsg replyMsg = new ReplyMsg();
        replyMsg.rm_direction = 1;
        replyMsg.rp_stat = 0;
        replyMsg.rp_acpt.ar_verf = this.xp_verf;
        replyMsg.rp_acpt.ar_stat = 4;
        this.xp_reply(replyMsg);
    }

    protected final void svcerr_systemerr() {
        ReplyMsg replyMsg = new ReplyMsg();
        replyMsg.rm_direction = 1;
        replyMsg.rp_stat = 0;
        replyMsg.rp_acpt.ar_verf = this.xp_verf;
        replyMsg.rp_acpt.ar_stat = 5;
        this.xp_reply(replyMsg);
    }

    protected final void svcerr_auth(int n) {
        ReplyMsg replyMsg = new ReplyMsg();
        replyMsg.rm_direction = 1;
        replyMsg.rp_stat = 1;
        replyMsg.rp_rjct.rj_stat = 1;
        replyMsg.rp_rjct.rj_why = n;
        this.xp_reply(replyMsg);
    }

    protected final void svcerr_weakauth() {
        this.svcerr_auth(5);
    }

    protected final void svcerr_noprog() {
        ReplyMsg replyMsg = new ReplyMsg();
        replyMsg.rm_direction = 1;
        replyMsg.rp_stat = 0;
        replyMsg.rp_acpt.ar_verf = this.xp_verf;
        replyMsg.rp_acpt.ar_stat = 1;
        this.xp_reply(replyMsg);
    }

    protected final void svcerr_progvers(int n, int n2) {
        ReplyMsg replyMsg = new ReplyMsg();
        replyMsg.rm_direction = 1;
        replyMsg.rp_stat = 0;
        replyMsg.rp_acpt.ar_verf = this.xp_verf;
        replyMsg.rp_acpt.ar_stat = 2;
        replyMsg.rp_acpt.ar_vers.low = n;
        replyMsg.rp_acpt.ar_vers.high = n2;
        this.xp_reply(replyMsg);
    }

    protected void set_allowedHosts(String[] arrstring) {
        allowed_hosts = arrstring;
    }

    protected boolean allowedHost(InetAddress inetAddress) {
        Log log = new Log("Service", "allowedHost");
        if (allowed_hosts == null) {
            log.msg(13, "allowed_hosts = null, no restricted access to rpc-services");
            return true;
        }
        String string = inetAddress.getHostAddress();
        String string2 = inetAddress.getHostName();
        log.msg(13, "remote hostaddress: " + string);
        log.msg(13, "remote hostname:    " + string2);
        String string3 = "";
        int n = string2.indexOf(46);
        if (n != -1) {
            string3 = string2.substring(0, n);
            log.msg(13, "remote host:        " + string3);
        }
        for (int i = 0; i < allowed_hosts.length; ++i) {
            try {
                String string4 = allowed_hosts[i];
                log.msg(13, "allowed host:       " + string4);
                if (!string4.equalsIgnoreCase(string2) && !string4.equals(string) && !string4.equalsIgnoreCase(string3)) continue;
                return true;
            }
            catch (NullPointerException nullPointerException) {
                log.msg(13, "Can't evaluate hostname or ipaddress from remote host");
                continue;
            }
            catch (Exception exception) {
                log.msg(13, exception.toString());
            }
        }
        return false;
    }
}

