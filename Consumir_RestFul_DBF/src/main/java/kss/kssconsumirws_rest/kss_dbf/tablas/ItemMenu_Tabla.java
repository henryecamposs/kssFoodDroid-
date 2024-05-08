package kss.kssconsumirws_rest.kss_dbf.tablas;

/**
 * Created by HENRY on 23/02/2015.
 */
public class ItemMenu_Tabla {
    public String codigo;
    public String descr;
    public Double precio;
    public String  grupo;
    public Double  precio2;
    public int   tiva;
    public String  imagen;
    public String  kp;
    public int   nivel;
    public String  codnivel;
    public int   pidepre;
    public int   pidecanti;
    public int   retorno;
    public String  descrkp;
    public Double  precio1;
    public String  nombre;

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
        codigo =null;
    }


    public ItemMenu_Tabla(String descr, Double precio, String grupo, String imagen,
                          int nivel, int pidecanti, int pidepre, int tiva, String codigo) {
        this.descr= descr;
        this.precio= precio;
        this.grupo= grupo;
        this.imagen= imagen;
        this.nivel= nivel;
        this.pidecanti= pidecanti;
        this.pidepre= pidepre;
        this.tiva= tiva;
        this.codigo =codigo;
    }
}
