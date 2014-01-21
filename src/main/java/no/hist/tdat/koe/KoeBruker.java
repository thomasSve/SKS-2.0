package no.hist.tdat.koe;

import no.hist.tdat.database.verktoy.KoeBrukerKoordinerer;

/**
 * Created by Henriette on 09/01/14.
 */
public class KoeBruker extends KoeBrukerKoordinerer {
    private int koe_id;
    private String mail;
    private String bordnr;

    private String ovingsnr;
    private int koe_plass;
    private String beskrivelse;

    public KoeBruker(int koe_id, String mail, String ovingsnr, int koe_plass, String beskrivelse){
        this.koe_id = koe_id;
        this.mail = mail;
        this.ovingsnr = ovingsnr;
        this.koe_plass = koe_plass;
        this.beskrivelse = beskrivelse;
    }

    public KoeBruker(){

    }

    public int getKoe_id() {
        return koe_id;
    }

    public void setKoe_id(int koe_id) {
        this.koe_id = koe_id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getOvingsnr() {
        return ovingsnr;
    }

    public void setOvingsnr(String ovingsnr) {
        this.ovingsnr = ovingsnr;
    }

    public int getKoe_plass() {
        return koe_plass;
    }

    public void setKoe_plass(int koe_plass) {
        this.koe_plass = koe_plass;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }
}