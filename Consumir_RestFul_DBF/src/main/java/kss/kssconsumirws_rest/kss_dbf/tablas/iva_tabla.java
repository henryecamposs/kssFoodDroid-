package kss.kssconsumirws_rest.kss_dbf.tablas;

/**
 * Created by HENRY on 26/02/2015.
 */
public class iva_tabla {
    public int tipo; public Double valor; public String nombre; public String nomencla;

    public iva_tabla() {
         tipo=0;  valor=0.0;  nombre=null;  nomencla=null;
    }

    public iva_tabla(String nombre, int tipo, double valor, String nomencla) {
        this.nombre=nombre;
        this.tipo=tipo;
        this.valor=valor;
        this.nomencla=nomencla;
    }
}
