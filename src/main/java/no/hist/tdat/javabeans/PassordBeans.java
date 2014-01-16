package no.hist.tdat.javabeans;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by Thomas on 14.01.14.
 */
public class PassordBeans {
    @NotNull
    @NotBlank
    private String gammeltPassord;
    @NotBlank
    @NotNull
    private String nyttPassord;
    @NotBlank
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

}
