package kss.kssconsumirws_rest.kss_dbf.tablas;

import java.util.Date;

/**
 * Created by HENRY on 26/02/2015.
 */
public class emple_tabla {
    public String codigo;
    public String descr;
    public int nivel;
    public String clavec;
    public String cargo;

    public emple_tabla() {
        codigo=null;
        descr=null;
        nivel=0;
        clavec=null;
        cargo=null;
    }

    public emple_tabla(String descr, String codigo,String clavec) {
        this.descr=descr;
        this.codigo=codigo;
    }


}
