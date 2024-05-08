package Tablas;

/**
 * Created by HENRY on 26/02/2015.
 */
public class kp_tabla {
    public String nombre; public String codigo; public String impresora; public String inactiva; public String tprinter;
    public  kp_tabla(){
         nombre=null;
        codigo=null;
        impresora=null;
        inactiva=null;
        tprinter=null;
    }

    public kp_tabla(String nombre, String codigo, String impresora) {
        this.nombre=nombre;
        this.codigo=codigo;
        this.impresora=impresora;
    }
}
