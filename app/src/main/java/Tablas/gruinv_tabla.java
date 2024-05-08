package Tablas;

/**
 * Created by HENRY on 26/02/2015.
 */
public class gruinv_tabla {
    public String codigo;
    public String descr;
    public Double por_gastos;
    public   gruinv_tabla(){
         codigo=null;
         descr=null;
         por_gastos=0.0;
    }

    public gruinv_tabla(String descr, String codigo) {
        this.codigo=codigo;
        this.descr= descr;
    }
}
