package Tablas;

import java.util.Date;

/**
 * Created by HENRY on 26/02/2015.
 */
public class cajas_tabla {
    public String codigo; public String nombre; public String activo; public Date fecha; public String hora; public Double nro; public String emplea; public String nomempa;
    public  cajas_tabla(){
         codigo=null; 
         nombre=null; 
         activo=null; 
         fecha=null;
         hora=null; 
         nro=null;
         emplea=null; 
         nomempa=null;
    }

    public cajas_tabla(String nombre, String codigo) {
        this.codigo=codigo;
        this.nombre=nombre;
    }
}
