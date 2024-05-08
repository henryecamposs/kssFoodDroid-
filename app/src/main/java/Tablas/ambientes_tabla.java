package Tablas;

/**
 * Created by HENRY on 26/02/2015.
 */
public class ambientes_tabla {
    public String codigo; public String descr;

    public  ambientes_tabla(){
        codigo=null;
        descr=null;

    }

    public ambientes_tabla(String descr, String codigo) {
        this.codigo=codigo;
        this.descr=descr;
    }
}
