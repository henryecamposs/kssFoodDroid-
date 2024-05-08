package Tablas;

/**
 * Created by HENRY on 26/02/2015.
 */
public class ambienteDetalle_tabla {
    public Double monto;
    public String hora;
    public String abierta;
    public String horaulti;
    public String emple;
    public String nomem;
    public Double iva;
    public String codcli;
    public String direccion;
    public String nomcli;
    public String rif;
    public String mesa;
    public int impreso;

    public ambienteDetalle_tabla() {
         monto=0.0;
         hora=null; 
         abierta=null; 
         horaulti=null; 
         emple=null; 
         nomem=null; 
         iva=0.0;
         codcli=null; 
         direccion=null; 
         nomcli=null;
         rif=null;
         mesa=null;
        impreso=0;
    }

    public ambienteDetalle_tabla(double monto, String hora, String abierta, String horaulti, String emple, String nomem, double iva, String codcli, String direccion, String nomcli, String rif, String mesa, int impreso) {
        this.monto= monto;
        this.hora = hora;
        this.abierta = abierta;
        this.horaulti = horaulti;
        this. emple= emple;
        this.nomem = nomem;
        this.iva= iva;
        this.codcli = codcli;
        this.direccion = direccion;
        this.nomcli = nomcli;
        this.rif = rif;
        this.mesa = mesa;
        this.impreso=impreso;
    }
}
