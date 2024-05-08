package kss.kssconsumirws_rest.kss_dbf.tablas;

import java.util.Date;

/**
 * Created by HENRY on 26/02/2015.
 */
public class mesaDetalle_tabla {
    public String codigo;
    public String descr;
    public Double canti;
    public Double monto;
    public Double iva;
    public Double total;
    public Date fecha;
    public int nro;
    public String grupo;
    public Double montot;
    public String mesa;
    public String horaa;
    public String kp;
    public String extras;
    public String emple;
    public String nomemp;
    public String tipoitem;
    public int nroitem;
    public String cantt;
    public String montod;
    public String hora;
    public String telefono;
    public int nrodev;
    public String cliente;
    public int tiva;
    public String codcli;
    public int enviado;
    public int pedidokp;
    public String descrkp;
    public String rif;
    public String codigocom;
    public int iddev;
    public Double cantielim;
    public Double cantielin;
    public Double montom;

    public mesaDetalle_tabla() {
        super();
        codigo=null;
        descr=null;
        canti=0.0;
        monto=0.0;
        iva=0.0;
        total=0.0;
        fecha=null;
        nro=0;
        grupo=null;
        montot=0.0;
        mesa=null;
        horaa=null;
        kp=null;
        extras=null;
        emple=null;
        nomemp=null;
        tipoitem=null;
        nroitem=0;
        cantt=null;
        montod=null;
        hora=null;
        telefono=null;
        nrodev=0;
        cliente=null;
        tiva=0;
        codcli=null;
        enviado=0;
        pedidokp=0;
        descrkp=null;
        rif=null;
        codigocom=null;
        iddev=0;
        cantielim=0.0;
        cantielin=0.0;
        montom=0.0;
    }

    public mesaDetalle_tabla(String descr, String codigo, double canti, double monto, double iva, double total, Date fecha, int nro, String grupo, double montot, String mesa, String horaa, String kp, String extras, String emple, String nomemp, int nroitem, String cantt, String montod, String hora, String telefono, int nrodev, String cliente, int tiva, String codcli, int enviado, int pedidokp, String descrkp, String rif, String codigocom, int iddev, double cantielim, double cantielin, double montom) {
        this.descr = descr;
        this.codigo = codigo;
        this.canti = canti;
        this.monto = monto;
        this.iva = iva;
        this.total = total;
        this.fecha = fecha;
        this.nro = nro;
        this.grupo = grupo;
        this.montot = montot;
        this.mesa = mesa;
        this.horaa = horaa;
        this.kp = kp;
        this.extras = extras;
        this.emple = emple;
        this.nomemp = nomemp;
        this.nroitem = nroitem;
        this.cantt = cantt;
        this.montod = montod;
        this.hora = hora;
        this.telefono = telefono;
        this.nrodev = nrodev;
        this.cliente = cliente;
        this.tiva = tiva;
        this.codcli = codcli;
        this.enviado = enviado;
        this.pedidokp = pedidokp;
        this.descrkp = descrkp;
        this.rif = rif;
        this.codigocom = codigocom;
        this.iddev = iddev;
        this.cantielim = cantielim;
        this.cantielin = cantielin;
        this.montom = montom;
    }
}
