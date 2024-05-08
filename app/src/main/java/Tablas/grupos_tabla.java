package Tablas;

/**
 * Created by KSS on 22/06/2015.
 */
public class grupos_tabla {
    public String codigo, nombre ,tipo ,imagen;
    public Double IVA;
    public Boolean oculto; public int inactivo;

    public   grupos_tabla(){
        codigo=null;
        nombre=null;
        imagen=null;
        tipo=null;
        IVA=0.0;
        oculto=false;
        inactivo=0;
    }

    public grupos_tabla(String codigo, String nombre, String imagen, String tipo,
                        Double IVA, Boolean oculto, int inactivo) {
        this.codigo=codigo;
        this.nombre=nombre;
        this.imagen=imagen;
        this.tipo=tipo;
        this.IVA=IVA;
        this.oculto=oculto;
        this.inactivo=inactivo;
    }
}
