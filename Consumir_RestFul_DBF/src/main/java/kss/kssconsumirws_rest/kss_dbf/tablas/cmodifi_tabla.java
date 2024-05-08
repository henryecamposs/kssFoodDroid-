package kss.kssconsumirws_rest.kss_dbf.tablas;

/**
 * Created by HENRY on 26/02/2015.
 */
public class cmodifi_tabla {
    public String codigo; public String nombre;
    public  cmodifi_tabla(){
        codigo=null;
        nombre=null;
    }

    public cmodifi_tabla(String nombre, String codigo) {
        this.codigo=codigo;
        this.nombre=nombre;
    }
}
