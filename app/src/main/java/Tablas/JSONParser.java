package Tablas;

import android.annotation.SuppressLint;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class JSONParser {

    public JSONParser() {
        super();
    }

    //******************IVA
    public iva_tabla parserIVA(JSONObject object) {
        iva_tabla result = new iva_tabla();

        try {
            JSONObject jsonObj = object.getJSONArray("Value").getJSONObject(0);
            result.nombre = (jsonObj.getString("nombre"));
            result.tipo = (jsonObj.getInt("tipo"));
            result.valor = (jsonObj.getDouble("valor"));
            result.nomencla = (jsonObj.getString("nomencla"));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser => parseUserDetails", e.getMessage());
        }
        return result;
    }

    public ArrayList<iva_tabla> parseIVA_array(JSONObject object) {
        ArrayList<iva_tabla> arrayList = new ArrayList<iva_tabla>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new iva_tabla(
                        jsonObj.getString("nombre"),
                        jsonObj.getInt("tipo"),
                        jsonObj.getDouble("valor"),
                        jsonObj.getString("nomencla")
                ));
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser => parseDepartment", e.getMessage());
        }
        return arrayList;

    }

//********************Detalle mesas

    @SuppressLint("LongLogTag")
    public mesaDetalle_tabla parserMesaDetalles(JSONObject object) {
        mesaDetalle_tabla result = new mesaDetalle_tabla();

        try {
            JSONObject jsonObj = object.getJSONArray("Value").getJSONObject(0);
            result.codigo = (jsonObj.getString("descr"));
            result.descr = (jsonObj.getString("descr"));
            result.canti = (jsonObj.getDouble("canti"));
            result.monto = (jsonObj.getDouble("monto"));
            result.iva = (jsonObj.getDouble("iva"));
            result.total = (jsonObj.getDouble("total"));
            result.fecha = ((Date) jsonObj.get("fecha"));
            result.nro = (jsonObj.getInt("nro"));
            result.grupo = (jsonObj.getString("grupo"));
            result.montot = (jsonObj.getDouble("montot"));
            result.mesa = (jsonObj.getString("mesa"));
            result.horaa = (jsonObj.getString("horaa"));
            result.kp = (jsonObj.getString("kp"));
            result.extras = (jsonObj.getString("extras"));
            result.emple = (jsonObj.getString("emple"));
            result.nomemp = (jsonObj.getString("nomemp"));
            result.tipoitem = (jsonObj.getString("tipoitem"));
            result.nroitem = (jsonObj.getInt("nroitem"));
            result.cantt = (jsonObj.getString("cantt"));
            result.montod = (jsonObj.getString("montod"));
            result.hora = (jsonObj.getString("hora"));
            result.telefono = (jsonObj.getString("telefono"));
            result.nrodev = (jsonObj.getInt("nrodev"));
            result.cliente = (jsonObj.getString("cliente"));
            result.tiva = (jsonObj.getInt("tiva"));
            result.codcli = (jsonObj.getString("codcli"));
            result.enviado = (jsonObj.getInt("enviado"));
            result.pedidokp = (jsonObj.getInt("pedidokp"));
            result.descrkp = (jsonObj.getString("descrkp"));
            result.rif = (jsonObj.getString("rif"));
            result.codigocom = (jsonObj.getString("codigocom"));
            result.iddev = (jsonObj.getInt("iddev"));
            result.cantielim = (jsonObj.getDouble("cantielim"));
            result.cantielin = (jsonObj.getDouble("cantielin"));
            result.montom = (jsonObj.getDouble("montom"));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser => parseUserDetails", e.getMessage());
        }
        return result;
    }

    public ArrayList<mesaDetalle_tabla> parseMesaDetalles_array(JSONObject object) {
        ArrayList<mesaDetalle_tabla> arrayList = new ArrayList<mesaDetalle_tabla>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new mesaDetalle_tabla(
                        jsonObj.getString("descr"),
                        jsonObj.getString("codigo"),
                        jsonObj.getDouble("canti"),
                        jsonObj.getDouble("monto"),
                        jsonObj.getDouble("iva"),
                        jsonObj.getDouble("total"),
                        (Date) jsonObj.get("fecha"),
                        jsonObj.getInt("nro"),
                        jsonObj.getString("grupo"),
                        jsonObj.getDouble("montot"),
                        jsonObj.getString("mesa"),
                        jsonObj.getString("horaa"),
                        jsonObj.getString("kp"),
                        jsonObj.getString("extras"),
                        jsonObj.getString("emple"),
                        jsonObj.getString("nomemp"),
                        jsonObj.getString("tipoitem"),
                        jsonObj.getInt("nroitem"),
                        jsonObj.getString("cantt"),
                        jsonObj.getString("montod"),
                        jsonObj.getString("hora"),
                        jsonObj.getString("telefono"),
                        jsonObj.getInt("nrodev"),
                        jsonObj.getString("cliente"),
                        jsonObj.getInt("tiva"),
                        jsonObj.getString("codcli"),
                        jsonObj.getInt("enviado"),
                        jsonObj.getInt("pedidokp"),
                        jsonObj.getString("descrkp"),
                        jsonObj.getString("rif"),
                        jsonObj.getString("codigocom"),
                        jsonObj.getInt("iddev"),
                        jsonObj.getDouble("cantielim"),
                        jsonObj.getDouble("cantielin"),
                        jsonObj.getDouble("montom"),
                        jsonObj.getString("codigoe")
                ));
            }
        } catch (JSONException e) {
            Log.d("JSONParser=>mesaDetalle_tabla", e.getMessage());
        }
        return arrayList;

    }

//********************Empleados

    public emple_tabla parserEmpleado(JSONObject object) {
        emple_tabla result = new emple_tabla();

        try {
            JSONObject jsonObj = object.getJSONArray("Value").getJSONObject(0);
            result.descr = (jsonObj.getString("descr"));
            result.codigo = (jsonObj.getString("codigo"));
            result.cargo = (jsonObj.getString("cargo"));
            result.clavec = (jsonObj.getString("clavec"));
            result.nivel = (jsonObj.getInt("nivel"));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser => parseUserDetails", e.getMessage());
        }
        return result;
    }

    public ArrayList<emple_tabla> parseEmpleado_array(JSONObject object) {
        ArrayList<emple_tabla> arrayList = new ArrayList<emple_tabla>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new emple_tabla(
                        jsonObj.getString("descr"),
                        jsonObj.getString("codigo"),
                        jsonObj.getString("clavec")
                ));
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser => parseDepartment", e.getMessage());
        }
        return arrayList;

    }

    //************************Detalle de Ambiente

    public ambienteDetalle_tabla parserAmbienteDetalle(JSONObject object) {
        ambienteDetalle_tabla result = new ambienteDetalle_tabla();

        try {
            JSONObject jsonObj = object.getJSONArray("Value").getJSONObject(0);
            result.monto = (jsonObj.getDouble("monto"));
            result.hora = (jsonObj.getString("hora"));
            result.abierta = (jsonObj.getString("abierta"));
            result.horaulti = (jsonObj.getString("horaulti"));
            result.emple = (jsonObj.getString("emple"));
            result.nomem = (jsonObj.getString("nomem"));
            result.iva = (jsonObj.getDouble("iva"));
            result.codcli = (jsonObj.getString("codcli"));
            result.direccion = (jsonObj.getString("direccion"));
            result.nomcli = (jsonObj.getString("nomcli"));
            result.rif = (jsonObj.getString("rif"));
            result.mesa = (jsonObj.getString("mesa"));
            result.impreso = (jsonObj.getInt("impreso"));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser => parseUserDetails", e.getMessage());
        }
        return result;
    }

    public ArrayList<ambienteDetalle_tabla> parserAmbienteDetalle_array(JSONObject object) {
        ArrayList<ambienteDetalle_tabla> arrayList = new ArrayList<ambienteDetalle_tabla>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new ambienteDetalle_tabla(
                        jsonObj.getDouble("monto"),
                        jsonObj.getString("hora"),
                        jsonObj.getString("abierta"),
                        jsonObj.getString("horaulti"),
                        jsonObj.getString("emple"),
                        jsonObj.getString("nomem"),
                        jsonObj.getDouble("iva"),
                        jsonObj.getString("codcli"),
                        jsonObj.getString("direccion"),
                        jsonObj.getString("nomcli"),
                        jsonObj.getString("rif"),
                        jsonObj.getString("mesa"),
                        jsonObj.getInt("impreso")
                ));
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser => parseDepartment", e.getMessage());
        }
        return arrayList;

    }
    //***********************KP (Impresoras remotas

    public kp_tabla parserKP(JSONObject object) {
        kp_tabla result = new kp_tabla();

        try {
            JSONObject jsonObj = object.getJSONArray("Value").getJSONObject(0);
            result.nombre = (jsonObj.getString("nombre"));
            result.codigo = (jsonObj.getString("codigo"));
            result.impresora = (jsonObj.getString("impresora"));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser => parseUserDetails", e.getMessage());
        }
        return result;
    }

    public ArrayList<kp_tabla> parseKP_array(JSONObject object) {
        ArrayList<kp_tabla> arrayList = new ArrayList<kp_tabla>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new kp_tabla(
                        jsonObj.getString("nombre"),
                        jsonObj.getString("codigo"),
                        jsonObj.getString("impresora")

                ));
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser => parseDepartment", e.getMessage());
        }
        return arrayList;

    }
    //***********************grupo inventario

    public grupos_tabla parserGrupos(JSONObject object) {
        grupos_tabla result = new grupos_tabla();

        try {
            JSONObject jsonObj = object.getJSONArray("Value").getJSONObject(0);
            result.codigo = (jsonObj.getString("codigo"));
            result.nombre = (jsonObj.getString("nombre"));
            result.imagen = (jsonObj.getString("imagen"));
            result.tipo = (jsonObj.getString("tipo"));
            result.IVA = (jsonObj.getDouble("IVA"));
            result.oculto = (jsonObj.getBoolean("oculto"));
            result.inactivo = (jsonObj.getInt("inactivo"));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser => parseUserDetails", e.getMessage());
        }
        return result;
    }

    public ArrayList<grupos_tabla> parseGrupos_array(JSONObject object) {
        ArrayList<grupos_tabla> arrayList = new ArrayList<grupos_tabla>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new grupos_tabla(
                                jsonObj.getString("codigo"),
                                jsonObj.getString("nombre"),
                                jsonObj.getString("imagen"),
                                jsonObj.getString("tipo"),
                                jsonObj.getDouble("iva"),
                                jsonObj.getBoolean("oculto"),
                                jsonObj.getInt("inactivo"))
                );
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser => parseDepartment", e.getMessage());
        }
        return arrayList;

    }

    //***********************Cajas

    public cajas_tabla parserCaja(JSONObject object) {
        cajas_tabla result = new cajas_tabla();

        try {
            JSONObject jsonObj = object.getJSONArray("Value").getJSONObject(0);
            result.nombre = (jsonObj.getString("nombre"));
            result.codigo = (jsonObj.getString("codigo"));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser => parseUserDetails", e.getMessage());
        }
        return result;
    }

    public ArrayList<cajas_tabla> parseCaja_array(JSONObject object) {
        ArrayList<cajas_tabla> arrayList = new ArrayList<cajas_tabla>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new cajas_tabla(
                        jsonObj.getString("nombre"),
                        jsonObj.getString("codigo")
                ));
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser => parseDepartment", e.getMessage());
        }
        return arrayList;

    }

//************************Categoria Modificadores

    public cmodifi_tabla parserCatModificador(JSONObject object) {
        cmodifi_tabla result = new cmodifi_tabla();

        try {
            JSONObject jsonObj = object.getJSONArray("Value").getJSONObject(0);
            result.nombre = (jsonObj.getString("nombre"));
            result.codigo = (jsonObj.getString("codigo"));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser => parseUserDetails", e.getMessage());
        }
        return result;
    }

    public ArrayList<cmodifi_tabla> parseCatModificador_array(JSONObject object) {
        ArrayList<cmodifi_tabla> arrayList = new ArrayList<cmodifi_tabla>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new cmodifi_tabla(
                        jsonObj.getString("nombre"),
                        jsonObj.getString("codigo")
                ));
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser => parseDepartment", e.getMessage());
        }
        return arrayList;

    }

    //***********************Modificadores

    public modifi_tabla parserModificador(JSONObject object) {
        modifi_tabla result = new modifi_tabla();

        try {
            JSONObject jsonObj = object.getJSONArray("Value").getJSONObject(0);
            result.descr = (jsonObj.getString("descr"));
            result.codigo = (jsonObj.getString("codigo"));
            result.precio = (jsonObj.getDouble("precio"));
            result.tiva = (jsonObj.getInt("tiva"));
            result.kp = (jsonObj.getString("kp"));
            result.nombre = (jsonObj.getString("nombre"));
            result.categoria = (jsonObj.getString("categoria"));
            result.dcategoria = (jsonObj.getString("dcategoria"));
            result.modi = (jsonObj.getString("modi"));
            result.nivel = (jsonObj.getInt("nivel"));
            result.precio1 = (jsonObj.getDouble("precio1"));
            result.precio2 = (jsonObj.getDouble("precio2"));
            result.factor = (jsonObj.getDouble("factor"));
            result.descrkp = (jsonObj.getString("descrkp"));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser => parseUserDetails", e.getMessage());
        }
        return result;
    }

    public ArrayList<modifi_tabla> parseModificador_array(JSONObject object) {
        ArrayList<modifi_tabla> arrayList = new ArrayList<modifi_tabla>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new modifi_tabla(
                        jsonObj.getString("descr"),
                        jsonObj.getString("codigo"),
                        jsonObj.getDouble("precio"),
                        jsonObj.getInt("tiva"),
                        jsonObj.getString("kp"),
                        jsonObj.getString("nombre"),
                        jsonObj.getString("modi"),
                        jsonObj.getInt("nivel")
                ));
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser => parseDepartment", e.getMessage());
        }
        return arrayList;

    }
    //**************************ambientes

    public ambientes_tabla parserAmbiente(JSONObject object) {
        ambientes_tabla result = new ambientes_tabla();

        try {
            JSONObject jsonObj = object.getJSONArray("Value").getJSONObject(0);
            result.descr = (jsonObj.getString("descr"));
            result.codigo = (jsonObj.getString("codigo"));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser => parseUserDetails", e.getMessage());
        }
        return result;
    }

    public ArrayList<ambientes_tabla> parseAmbiente_array(JSONObject object) {
        ArrayList<ambientes_tabla> arrayList = new ArrayList<ambientes_tabla>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new ambientes_tabla(
                        jsonObj.getString("descr"),
                        jsonObj.getString("codigo")
                ));
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser => parseDepartment", e.getMessage());
        }
        return arrayList;

    }

    //***************************Items de Menu
    public ItemMenu_Tabla parserItemMenu(JSONObject object) {
        ItemMenu_Tabla ItemDet = new ItemMenu_Tabla();

        try {
            JSONObject jsonObj = object.getJSONArray("Value").getJSONObject(0);
            ItemDet.descr = (jsonObj.getString("descr"));
            ItemDet.precio = (jsonObj.getDouble("precio"));
            ItemDet.grupo = (jsonObj.getString("grupo"));
            ItemDet.precio2 = (jsonObj.getDouble("precio2"));
            ItemDet.tiva = (jsonObj.getInt("tiva"));
            ItemDet.imagen = (jsonObj.getString("imagen"));
            ItemDet.kp = (jsonObj.getString("kp"));
            ItemDet.nivel = (jsonObj.getInt("nivel"));
            ItemDet.codnivel = (jsonObj.getString("codnivel"));
            ItemDet.pidepre = (jsonObj.getInt("pidepre"));
            ItemDet.pidecanti = (jsonObj.getInt("pidecanti"));
            ItemDet.retorno = (jsonObj.getInt("retorno"));
            ItemDet.descrkp = (jsonObj.getString("descrkp"));
            ItemDet.precio1 = (jsonObj.getDouble("precio1"));
            ItemDet.nombre = (jsonObj.getString("nombre"));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser => parseUserDetails", e.getMessage());
        }
        return ItemDet;
    }

    public ArrayList<ItemMenu_Tabla> parseItemmenu_array(JSONObject object) {
        ArrayList<ItemMenu_Tabla> arrayList = new ArrayList<ItemMenu_Tabla>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new ItemMenu_Tabla(
                        jsonObj.getString("descr"),
                        jsonObj.getDouble("precio"),
                        jsonObj.getString("grupo"),
                        jsonObj.getString("imagen"),
                        jsonObj.getInt("nivel"),
                        jsonObj.getInt("pidecanti"),
                        jsonObj.getInt("pidepre"),
                        jsonObj.getInt("tiva"),
                        jsonObj.getString("codigo"),
                        jsonObj.getString("tipo"),
                        jsonObj.getString("inven"),
                        jsonObj.getDouble("precio2"),
                        jsonObj.getString("kp"),
                        jsonObj.getString("codnivel"),
                        jsonObj.getInt("retorno"),
                        jsonObj.getString("descrkp"),
                        jsonObj.getDouble("precio1"),
                        jsonObj.getString("nombre"),
                        jsonObj.getString("modi"),
                        jsonObj.getInt("peso")
                ));
            }
            return arrayList;
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser => parseDepartment", e.getMessage());
            return null;
        }

    }


    //***************************Cliente
    public cliente_tabla parseCliente(JSONObject object) {
        cliente_tabla cliente = new cliente_tabla();
        try {
            JSONObject jsonObj = object.getJSONArray("Value").getJSONObject(0);
            cliente.nombre = (jsonObj.getString("nombre"));
            cliente.rif = (jsonObj.getString("rif"));
            cliente.codigo = (jsonObj.getString("codigo"));
            cliente.ciudad = (jsonObj.getString("ciudad"));
            cliente.direccion = (jsonObj.getString("direccion"));
            cliente.fecha = (Date) (jsonObj.get("fecha"));
            cliente.telefono = (jsonObj.getString("telefono"));
            cliente.urb = (jsonObj.getString("urb"));
            cliente.direccion = (jsonObj.getString("direccion"));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser => parseUserDetails", e.getMessage());
        }
        return cliente;
    }

    public ArrayList<cliente_tabla> parseCliente_array(JSONObject object) {
        ArrayList<cliente_tabla> arrayList = new ArrayList<cliente_tabla>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new cliente_tabla(
                        jsonObj.getString("rif"),
                        jsonObj.getString("nombre"),
                        jsonObj.getString("codigo"),
                        jsonObj.getString("ciudad"),
                        jsonObj.getString("telefono"),
                        jsonObj.getString("direccion")
                ));
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser => parseDepartment", e.getMessage());
        }
        return arrayList;

    }


}