package Tablas;

/**
 * Created by HENRY on 26/02/2015.
 */
public class modifi_tabla {
    public String codigo;
    public String descr;
    public Double precio;
    public int tiva;
    public String kp;
    public String nombre;
    public String categoria;
    public String dcategoria;
    public String modi;
    public int nivel;
    public Double precio1;
    public Double precio2;
    public Double factor;
    public String descrkp;

    public modifi_tabla(){
        codigo=null;
        descr=null;
        precio=0.0;
        tiva=0;
        kp=null;
        nombre=null;
        categoria=null;
        dcategoria=null;
        modi=null;
        nivel=0;
        precio1=0.0;
        precio2=0.0;
        factor=0.0;
        descrkp=null;
    }

    public modifi_tabla(String descr, String codigo, double precio, int tiva, String kp, String nombre, String modi, int nivel) {
        this.descr = descr;
        this.codigo = codigo;
        this.precio= precio;
        this.tiva =tiva;
        this.kp = kp;
        this.nombre = nombre;
        this. modi= modi;
        this.nivel =nivel;
    }
}
