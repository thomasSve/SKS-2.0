package no.hist.tdat.javabeans;

import javax.validation.constraints.NotNull;

/**
 * Created by Thomas on 14.01.14.
 */
public class PassordBeans {
    @NotNull
    private String gammeltPassord;
    @NotNull
    private String nyttPassord;
    @NotNull
    private String bekreftPassord;

    public PassordBeans(){

    }
    public PassordBeans(String GP, String NP, String BP){
        this.gammeltPassord = GP;
        this.nyttPassord = NP;
        this.bekreftPassord = BP;
    }

    public String getGammeltPassord() {
        return gammeltPassord;
    }

    public void setGammeltPassord(String gammeltPassord) {
        this.gammeltPassord = gammeltPassord;
    }

    public String getNyttPassord() {
        return nyttPassord;
    }

    public void setNyttPassord(String nyttPassord) {
        this.nyttPassord = nyttPassord;
    }

    public String getBekreftPassord() {
        return bekreftPassord;
    }

    public void setBekreftPassord(String bekreftPassord) {
        this.bekreftPassord = bekreftPassord;
    }
    /**
     * Tar inn tre variabler, det gamle, nye og bekrefta det nye.
     *
     * @param gPassord passord, nytt PW & bekreft nytt PW
     * @return Boolean, passordet endret eller ikkje
     * @author vimCnett
     */
    public boolean endrePassord(String gPassord, String nPassord, String bPassord){
       /*if(nPassord.equals(bPassord)){

            if(){

            }
        }*/
        return false;
    }
}
