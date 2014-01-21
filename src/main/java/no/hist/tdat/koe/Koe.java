package no.hist.tdat.koe;

/**
 * Created by Henriette on 09/01/14.
 */
public class Koe {
    private int koe_id;
    private int aapen; //1 hvis på, 0 hvis av

    public Koe(int koe_id, int aapen){
        this.koe_id = koe_id;
        this.aapen = aapen;
    }

    public Koe(){

    }


    public int getKoe_id() {
        return koe_id;
    }

    public void setKoe_id(int koe_id) {
        this.koe_id = koe_id;
    }

    public int getAapen() {
        return aapen;
    }

    public void setAapen(int aapen) {
        this.aapen = aapen;
    }

    public void sry(){
        System.out.println("hello, world");
    }
}

