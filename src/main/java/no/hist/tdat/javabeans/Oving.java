package no.hist.tdat.javabeans;

import java.sql.Date;

/**
 * Created by ted on 1/15/14.
 */
public class Oving {
    private int ovingnr;
    private boolean godkjent;
    private String godkjentAv;
    private Date godkjentTid;

    public int getOvingnr() {
        return ovingnr;
    }

    public void setOvingnr(int ovingnr) {
        this.ovingnr = ovingnr;
    }

    public boolean isGodkjent() {
        return godkjent;
    }

    public void setGodkjent(boolean godkjent) {
        this.godkjent = godkjent;
    }

    public String getGodkjentAv() {
        return godkjentAv;
    }

    public void setGodkjentAv(String godkjentAv) {
        this.godkjentAv = godkjentAv;
    }

    public Date getGodkjentTid() {
        return godkjentTid;
    }

    public void setGodkjentTid(Date godkjentTid) {
        this.godkjentTid = godkjentTid;
    }
}
