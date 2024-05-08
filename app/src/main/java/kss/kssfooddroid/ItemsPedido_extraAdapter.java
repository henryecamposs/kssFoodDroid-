package kss.kssfooddroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import kss.kssfooddroid.util.ToastManager;

/**
 * Created by KSS on 13/07/2015.
 */
public class ItemsPedido_extraAdapter extends BaseAdapter {
    private final List<ItemPedido> extras;
    private final Context context;
    private final List<ItemPedido> items;


    public ItemsPedido_extraAdapter(Context context, List<ItemPedido> items, List<ItemPedido> extras) {
        this.context = context;
        this.items = items;
        this.extras= extras;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        List<ItemPedido> lstextras_Pedido = null;

        if (convertView == null) {
            // Create a new view into the list.
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.views_itemspedido_modificadores, parent, false);
        }
        // Set data into the view.
        TextView txtCantidad = (TextView) rowView.findViewById(R.id.tvCanti_PedidoExtra);
        TextView txtDescr = (TextView) rowView.findViewById(R.id.tvItemMenu_PedidoExtra);
        ListView lvExtras = (ListView) rowView.findViewById(R.id.lvModificadores_PedidoExtra);

        ItemPedido Item = this.items.get(position);
        txtCantidad.setText(String.format("%.2f", Item.cantidad));
        if (Item.descr.trim().length()>=25){
            txtDescr.setSingleLine(false);
        }
        txtDescr.setText(Item.descr.trim());
       //Agregar codigos a listView
        for (ItemPedido Item_Pedido: extras){
            if (Item_Pedido.IDItem==Item.IDItem){
                lstextras_Pedido.add(Item_Pedido);
            }
        }
        if (lstextras_Pedido.size()>0){
            lvExtras.setAdapter(new ItemsExtraAdapter(this, lstextras_Pedido));
        }

        return rowView;
    }

    private class ItemsExtraAdapter extends BaseAdapter {
      //  private final Context context;
        private final ItemsPedido_extraAdapter itemsPedido_extraAdapter;
        private final List<ItemPedido> items;
        public ItemsExtraAdapter(ItemsPedido_extraAdapter itemsPedido_extraAdapter, List<ItemPedido> lstextras_pedido) {
            this.itemsPedido_extraAdapter = itemsPedido_extraAdapter;
            //  this.context = itemsPedido_extraAdapter;
            this.items = lstextras_pedido;
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            try {
                View rowView = convertView;

                if (convertView == null) {
                    // Create a new view into the list.
                    LayoutInflater inflater = (LayoutInflater) context
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    rowView = inflater.inflate(R.layout.views_modificadores_items, parent, false);
                }

                // Set data into the view.
                TextView txtCantidad = (TextView) rowView.findViewById(R.id.tvCanti_ModifcadoresItems);
                TextView txtDescr = (TextView) rowView.findViewById(R.id.tvItemModificador_ModifcadoresItems);
                TextView txtItemTotal = (TextView) rowView.findViewById(R.id.tvTotal_ModifcadoresItems);

                ItemPedido item = this.items.get(position);
                txtCantidad.setText(String.format("%.2f", item.cantidad));
                if (item.descr.trim().length() >= 25) {
                    txtDescr.setSingleLine(false);
                }
                txtDescr.setText(item.descr.trim());
                double Total = item.getMontoTotal();
                txtItemTotal.setText(String.format("%,10.2f", Total));


                return rowView;
            } catch (Exception ex) {
                ToastManager.show(context, ex.getMessage().toString() + "\n" + ex.getLocalizedMessage().toString(), ToastManager.ERROR, 1);
                return null;
            }
        }
    }
}
