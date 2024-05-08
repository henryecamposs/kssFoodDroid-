package Tablas;
/* JSON API for android appliation */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import kss.kssfooddroid.R;
import kss.kssfooddroid.util.clsUtil_Net;

public class RestAPI {
    private Context context;
    private int nReintentos;
    public RestAPI(Context context) {        this.context=context;    }
    public static final String urlString = "http://192.168.1.110/WebAPI_JSON_Rest/handler1.ashx";

    private static String convertStreamToUTF8String(InputStream stream) throws IOException {
        String result = "";
        StringBuilder sb = new StringBuilder();
        try {
            InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
            char[] buffer = new char[4096];
            int readedChars = 0;
            while (readedChars != -1) {
                readedChars = reader.read(buffer);
                if (readedChars > 0)
                    sb.append(buffer, 0, readedChars);
            }
            result = sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    private String load(String contents) throws IOException {
        boolean Continuar=true;
        //TODO:(tablet conectada) quitar comentarios
       /* for (int i =0;i<2;i++){
            if (clsUtil_Net.esWSActivo(context,urlString) == true) {
                //Verificar que URL correcta
                if (clsUtil_Net.esWSActivo(this.context,urlString)){
                    Continuar=true;
                    break;
                }else{Continuar=false;}
            } else if(nReintentos<2){
                Continuar=false;
            }
        }*/
        if (Continuar) {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(60000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            OutputStreamWriter w = new OutputStreamWriter(conn.getOutputStream());
            w.write(contents);
            w.flush();
            InputStream istream = conn.getInputStream();
            String result = convertStreamToUTF8String(istream);
            return result;
       }
        return null;
    }
    private Object mapObject(Object o) {
        Object finalValue = null;
        if (o.getClass() == String.class) {
            finalValue = o;
        } else if (Number.class.isInstance(o)) {
            finalValue = String.valueOf(o);
        } else if (Date.class.isInstance(o)) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss", new Locale("en", "USA"));
            finalValue = sdf.format((Date) o);
        } else if (Collection.class.isInstance(o)) {
            Collection<?> col = (Collection<?>) o;
            JSONArray jarray = new JSONArray();
            for (Object item : col) {
                jarray.put(mapObject(item));
            }
            finalValue = jarray;
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            Method[] methods = o.getClass().getMethods();
            for (Method method : methods) {
                if (method.getDeclaringClass() == o.getClass()
                        && method.getModifiers() == Modifier.PUBLIC
                        && method.getName().startsWith("get")) {
                    String key = method.getName().substring(3);
                    try {
                        Object obj = method.invoke(o, null);
                        Object value = mapObject(obj);
                        map.put(key, value);
                        finalValue = new JSONObject(map);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return finalValue;
    }

    public JSONObject CreateNewAccount(int ID, String firstName, String lastName, String userName, String password) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "CreateNewAccount");
        p.put("ID", mapObject(ID));
        p.put("firstName", mapObject(firstName));
        p.put("lastName", mapObject(lastName));
        p.put("userName", mapObject(userName));
        p.put("password", mapObject(password));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject GetUserDetails(String userName) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "GetUserDetails");
        p.put("userName", mapObject(userName));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UserAuthentication(String userName, String passsword) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "UserAuthentication");
        p.put("userName", mapObject(userName));
        p.put("passsword", mapObject(passsword));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject GetDepartmentDetails() throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "GetDepartmentDetails");
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getAmbientes() throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getAmbientes");
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getAmbientePorCodigo(String porCodigo) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getAmbientePorCodigo");
        p.put("porCodigo", mapObject(porCodigo));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getAmbientePorDescr(String porDescr) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getAmbientePorDescr");
        p.put("porDescr", mapObject(porDescr));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getGruposMenu() throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getGruposMenu");
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getGruposMenuPorCodigo(String porCodigo) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getGruposMenuPorCodigo");
        p.put("porCodigo", mapObject(porCodigo));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getCajas() throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getCajas");
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getCajasPorCodigo(String porCodigo) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getCajasPorCodigo");
        p.put("porCodigo", mapObject(porCodigo));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getIVA() throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getIVA");
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getIVAPortipo(int tipo) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getIVAPortipo");
        p.put("tipo", mapObject(tipo));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getMesas(String nomTablaAmbiente) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getMesas");
        p.put("nomTablaAmbiente", mapObject(nomTablaAmbiente));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getMesaPorMesa(String nomTablaAmbiente, String porMesa) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getMesaPorMesa");
        p.put("nomTablaAmbiente", mapObject(nomTablaAmbiente));
        p.put("porMesa", mapObject(porMesa));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject setMesaAbierta(String nomTablaAmbiente, String porMesa, double monto, String hora, String abierta, String horaulti, String emple, String nomem, double iva, String codcli, String direccion, String nomcli, String rif) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "setMesaAbierta");
        p.put("nomTablaAmbiente", mapObject(nomTablaAmbiente));
        p.put("porMesa", mapObject(porMesa));
        p.put("monto", mapObject(monto));
        p.put("hora", mapObject(hora));
        p.put("abierta", mapObject(abierta));
        p.put("horaulti", mapObject(horaulti));
        p.put("emple", mapObject(emple));
        p.put("nomem", mapObject(nomem));
        p.put("iva", mapObject(iva));
        p.put("codcli", mapObject(codcli));
        p.put("direccion", mapObject(direccion));
        p.put("nomcli", mapObject(nomcli));
        p.put("rif", mapObject(rif));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getClientes() throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getClientes");
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getclientesPorRIF(String porRIF) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getclientesPorRIF");
        p.put("porRIF", mapObject(porRIF));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getclientesPorNombre(String porNombre) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getclientesPorNombre");
        p.put("porNombre", mapObject(porNombre));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getclientesPorCodigo(String porCodigo) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getclientesPorCodigo");
        p.put("porCodigo", mapObject(porCodigo));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject newClientes(String telefono, String direccion, String nombre, String rif, String codigo, String ciudad, String urb, Date fecha) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "newClientes");
        p.put("telefono", mapObject(telefono));
        p.put("direccion", mapObject(direccion));
        p.put("nombre", mapObject(nombre));
        p.put("rif", mapObject(rif));
        p.put("codigo", mapObject(codigo));
        p.put("ciudad", mapObject(ciudad));
        p.put("urb", mapObject(urb));
        p.put("fecha", mapObject(fecha));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getItemsMesa(String nomTablaMesa) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getItemsMesa");
        p.put("nomTablaMesa", mapObject(nomTablaMesa));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getItemsMesaPorNroItem(String nomTablaMesa, int porNroItem) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getItemsMesaPorNroItem");
        p.put("nomTablaMesa", mapObject(nomTablaMesa));
        p.put("porNroItem", mapObject(porNroItem));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject newItemMesaPorNroItem(String nomTablaMesa, String codigo, String mesa, String empleClave, int idDEV, String codcli) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "newItemMesaPorNroItem");
        p.put("nomTablaMesa", mapObject(nomTablaMesa));
        p.put("codigo", mapObject(codigo));
        p.put("mesa", mapObject(mesa));
        p.put("empleClave", mapObject(empleClave));
        p.put("idDEV", mapObject(idDEV));
        p.put("codcli", mapObject(codcli));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }
    public JSONObject getItemMenuporCodNivel(String porCodNivel,String porGrupo) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","Service1");
        o.put("method", "getItemMenuporCodNivel");
        p.put("porCodNivel",mapObject(porCodNivel));
        p.put("porGrupo",mapObject(porGrupo));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getArchivosImagenes() throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","Service1");
        o.put("method", "getArchivosImagenes");
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject nroItems(String nomTablaMesa) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "nroItems");
        p.put("nomTablaMesa", mapObject(nomTablaMesa));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject elimItemMesaPorNroItem(String nomTablaMesa, int porNroItem, double cantidad) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "elimItemMesaPorNroItem");
        p.put("nomTablaMesa", mapObject(nomTablaMesa));
        p.put("porNroItem", mapObject(porNroItem));
        p.put("cantidad", mapObject(cantidad));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject reducirItemMesaPorNroItem(String nomTablaMesa, int porNroItem, double originalCantidad, double nuevaCantidad) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "reducirItemMesaPorNroItem");
        p.put("nomTablaMesa", mapObject(nomTablaMesa));
        p.put("porNroItem", mapObject(porNroItem));
        p.put("originalCantidad", mapObject(originalCantidad));
        p.put("nuevaCantidad", mapObject(nuevaCantidad));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getItemsMenu() throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getItemsMenu");
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getItemMenuPorCodigo(String porCodigo) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getItemMenuPorCodigo");
        p.put("porCodigo", mapObject(porCodigo));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getItemMenuPorDescr(String porDescr) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getItemMenuPorDescr");
        p.put("porDescr", mapObject(porDescr));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getItemMenuPorGrupo(String porGrupo) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getItemMenuPorGrupo");
        p.put("porGrupo", mapObject(porGrupo));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getEmpleados() throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getEmpleados");
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getEmpleadoPorClave(String porClave) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getEmpleadoPorClave");
        p.put("porClave", mapObject(porClave));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject esClaveCorrecta(String clave) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "esClaveCorrecta");
        p.put("clave", mapObject(clave));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getCorrelativoDevol() throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getCorrelativoDevol");
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject setCorrelativoDevol() throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "setCorrelativoDevol");
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getCorrelativoPedido() throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getCorrelativoPedido");
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject setCorrelativoPedido() throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "setCorrelativoPedido");
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getModificadores() throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getModificadores");
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getModificadoresPorCategoria(String porCategoria) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getModificadoresPorCategoria");
        p.put("porCategoria", mapObject(porCategoria));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getModificadorPorCodigo(String porCodigo) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getModificadorPorCodigo");
        p.put("porCodigo", mapObject(porCodigo));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getCategoriasMod(String porCodigo) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getCategoriasMod");
        p.put("porCodigo", mapObject(porCodigo));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject getCategoriaModPorCodigo(String porCodigo) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "Service1");
        o.put("method", "getCategoriaModPorCodigo");
        p.put("porCodigo", mapObject(porCodigo));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

}