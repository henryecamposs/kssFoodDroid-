package kss.kssconsumirws_rest.kss_dbf.tablas;

import java.util.Date;

/**
 * Created by HENRY on 23/02/2015.
 */
public class cliente_tabla {
    public String telefono; public String direccion; public String nombre; public String rif; public String codigo;
    public String ciudad; public String urb; public Date fecha;

    public cliente_tabla(String rif, String nombre, String codigo) {
        this.rif= rif;
        this.nombre=nombre;
        this.codigo=codigo;
    }
    public  cliente_tabla(){
        telefono=null;
        direccion=null;
        nombre=null;
        rif=null;
        ciudad=null;
        codigo=null;
        fecha=null;
        urb=null;
    }

}
