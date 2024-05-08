package Tablas;

/**
 * Created by HENRY on 23/02/2015.
 */
public class ItemMenu_Tabla {
    public  String inven;
    public  String codigo;
    public  String descr;
    public  Double precio;
    public  String grupo;
    public  Double precio2;
    public  int tiva;
    public  String imagen;
    public  String kp;
    public  int nivel;
    public  String codnivel;
    public  int pidepre;
    public  int pidecanti;
    public  int retorno;
    public  String descrkp;
    public  Double precio1;
    public  String nombre;
    public  String tipo;
    public  String modi;
    public  int peso;

    public ItemMenu_Tabla() {
        super();
        descr = null;
        precio = 0.0;
        grupo = null;
        precio2 = 0.0;
        tiva = 0;
        imagen = null;
        kp = null;
        nivel = 0;
        codnivel = null;
        pidepre = 0;
        pidecanti = 0;
        retorno = 0;
        descrkp = null;
        precio1 = 0.0;
        nombre = null;
        codigo = null;
        tipo = null;
        inven = null;
        modi = null;
        peso = 0;
    }


    public ItemMenu_Tabla(String descr,
                          Double precio,
                          String grupo,
                          String imagen,
                          int nivel,
                          int pidecanti,
                          int pidepre,
                          int tiva,
                          String codigo,
                          String tipo,
                          String inven,
                          Double precio2,
                          String kp,
                          String codnivel,
                          int retorno,
                          String descrkp,
                          Double precio1,
                          String nombre,
                          String modi,
                          int peso
    ) {
        this.descr = descr;
        this.precio = precio;
        this.grupo = grupo;
        this.imagen = imagen;
        this.nivel = nivel;
        this.pidecanti = pidecanti;
        this.pidepre = pidepre;
        this.tiva = tiva;
        this.codigo = codigo;
        this.tipo = tipo;
        this.inven = inven;
        this.precio2 = precio2;
        this.kp = kp;
        this.codnivel = codnivel;
        this.retorno = retorno;
        this.descrkp = descrkp;
        this.precio1 = precio1;
        this.nombre = nombre;
        this.modi = modi;
        this.peso = peso;
    }
}
