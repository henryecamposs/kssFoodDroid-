package kss.kssfooddroid;

import java.util.Date;

import static kss.kssfooddroid.util.clsUtil_Calculos.calcularValorFinalConPorcentaje;
import static kss.kssfooddroid.util.clsUtil_Calculos.convertirTasa;

/**
 * Created by KSS on 24/06/2015.
 */
public class ItemPedido {

    public final String codigo;
    public final String descr;
    public double cantidad;
    public final double preciobase;
    public final int tiva;   //Tipo Iva
    public final String Imagen;
    public final String kp;  //Codigo Impresora
    public final int IDItem;
    public final Date fecha;
    public final Double taxServicio;
    public final String codigoEmpleado;
    public final String extras;
    public final String codigoExtra;
    /**
     * Indica el tipo d eenviado (2 Procesado con exito)
     */
    public final int enviado;
    /**
     * Indica si es modificador (2 es Extra)
     */
    public final String tipoItem;
    public final String pedidoKp;   // TODO:Numero Pedido Impresora extraido desde correlativo de kp
    public final String HoraActual;

    public ItemPedido() {
        codigoExtra = null;
        extras=null;
        codigo = null;
        descr = null;
        cantidad = 0;
        tiva = 0;
        Imagen = null;
        kp = null;
        IDItem = 0;
        fecha = null;
        preciobase = 0;
        taxServicio = 0.0;
        codigoEmpleado = null;
        enviado = 0;
        tipoItem = null;
        pedidoKp = null;
        HoraActual = null;
    }

    public ItemPedido(String codigo,
                      String descr,
                      double cantidad,
                      double preciobase,
                      int tiva,
                      String Imagen,
                      String kp,
                      int IDItem,
                      Date fecha,
                      Double taxServicio,
                      String codigoEmpleado,
                      String extras,
                      String codigoextra,
                      int enviado,
                      String tipoItem,
                      String pedidoKp,
                      String horaActual) {
        this.extras = extras;
        this.codigoExtra = codigoextra;
        this.enviado = enviado;
        this.codigo = codigo;
        this.descr = descr;
        this.cantidad = cantidad;
        this.tiva = tiva;
        this.Imagen = Imagen;
        this.kp = kp;
        this.IDItem = IDItem;
        this.fecha = fecha;
        this.preciobase = preciobase;
        this.taxServicio = taxServicio;
        this.codigoEmpleado = codigoEmpleado;
        this.tipoItem = tipoItem;
        this.pedidoKp = pedidoKp;
        HoraActual = horaActual;
    }

    public boolean getEsEliminado() {
        if (cantidad == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Calcular el precio con o sin IVA incluido
     *
     * @return
     */
    public Double getPrecio() {
        if (tiva >= 1) {
            return  calcularValorFinalConPorcentaje(12.00, preciobase); //TODO: Colocar valor de Tasa real (desde la configuracion)
        } else {
            return  preciobase;
        }
    }

    /**
     * Calcular el monto total del Item.
     *
     * @return
     */
    public Double getMontoTotal() {
        return cantidad * getPrecio();
    }

    /**
     * Devuelve al Monto Total por el servicio
     *
     * @return
     */
    public Double getServicio() {
        // if (cfg.esCobraServicio){...}
        return getMontoTotal() * convertirTasa(10); // TODO:Colocar la tasa real del cobro por servicio si existe y si secobra
    }

    public Double getTasaIVA() {
        switch (tiva) {
            case 0:
                return 0.0;
            case 1:
                return 12.00; //TODO: Colocar tasa de configuracion
            case 2:
                return 8.00; //TODO: Colocar tasa de configuracion
            case 3:
                return 22.00; //TODO: Coolcar tasa de configuracion
        }
        return 0.0;
        }
}
