package kss.kssfooddroid;

/**
 * Created by KSS on 25/06/2015.
 */
public class ItemMenu {

    public final String codigo;
    public final String descr;
    public final Double precio;
    public final String grupo;
    public final Double precio2;
    public final int tiva;
    public final String imagen;
    public final String kp;
    public final int nivel;
    public final String codnivel;
    public final int pidepre;
    public final int pidecanti;
    public final int retorno;
    public final String descrkp;
    public final Double precio1;
    //Opciones de Grupo
    public final String nombre;
    public final String tipo;
    public final Boolean oculto;
    public final int inactivo;

    public ItemMenu() {
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
        oculto = false;
        inactivo = 0;
    }


    public ItemMenu(String descr, Double precio, String grupo, Double precio2, String imagen,
                    String kp, int nivel, String codnivel, int pidecanti, int pidepre, int tiva,
                    String codigo, int retorno, String descrkp, Double precio1,
                    String nombre, String tipo, boolean oculto, int inactivo) {
        this.descr = descr;
        this.precio = precio;
        this.grupo = grupo;
        this.precio2 = precio2;
        this.imagen = imagen;
        this.kp = kp;
        this.nivel = nivel;
        this.codnivel = codnivel;
        this.pidecanti = pidecanti;
        this.pidepre = pidepre;
        this.tiva = tiva;
        this.codigo = codigo;
        this.retorno = retorno;
        this.descrkp = descrkp;
        this.precio1 = precio1;
        this.nombre = nombre;
        this.tipo = tipo;
        this.oculto = oculto;
        this.inactivo = inactivo;
    }

    /**
     * Devuelve el tipo de ItemdeMenu (GRUPO,SUBGRUPO,ITEM)
     * @return
     */
    public enuNivelMenu getEnuTipoItem() {
        if (grupo == null && descr==null) {
            return enuNivelMenu.GRUPO;
        }
        if (nivel == 1) {
            return enuNivelMenu.SUBGRUPO;
        }else if(nivel == 0){
            return enuNivelMenu.ITEM_GRUPO;
        }else if(nivel == 2){
            return enuNivelMenu.ITEM_SUBGRUPO;
        }
        return enuNivelMenu.GRUPO;
    }



    /**
     * Verifica si pertenece a un subgrupo
     *
     * @return Devuelve verdadero o falso
     */
    public boolean getEsSubGrupo() {
        if (nivel == 1) {
            return true;
        } else {
            return false;
        }
    }


}

