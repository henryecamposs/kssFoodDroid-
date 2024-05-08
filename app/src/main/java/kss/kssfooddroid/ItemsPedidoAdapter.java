package kss.kssfooddroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import kss.kssfooddroid.util.ToastManager;

/**
 * Created by KSS on 24/06/2015.
 */
public class ItemsPedidoAdapter extends BaseAdapter {

    private Context context;
    private List<ItemPedido> items;


    public ItemsPedidoAdapter(Context context, List<ItemPedido> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            View rowView = convertView;

            if (convertView == null) {
                // Create a new view into the list.
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rowView = inflater.inflate(R.layout.views_itemspedido, parent, false);
            }

            // Set data into the view.
            TextView txtCantidad = (TextView) rowView.findViewById(R.id.txtItemCantidad);
            TextView txtDescr = (TextView) rowView.findViewById(R.id.txtItemDescr);
            TextView txtItemTotal = (TextView) rowView.findViewById(R.id.txtItemTotal);

            ItemPedido item = this.items.get(position);
            txtCantidad.setText(String.format("%.2f", item.cantidad));
            if (item.descr.trim().length() >= 25) {
                txtDescr.setSingleLine(false);
            }
            txtDescr.setText(item.descr.trim());
            double Total = item.getMontoTotal();
            txtItemTotal.setText(String.format("%,10.2f", Total));
            ImageView ivItem = (ImageView) rowView.findViewById(R.id.imgItem);
            if (item.getEsEliminado()) {
                ivItem.setImageResource(R.drawable.btn_cancel_r);
            } else {
                ivItem.setVisibility(View.INVISIBLE);
            }

            return rowView;
        } catch (Exception ex) {
            ToastManager.show(context, ex.getMessage().toString() + "\n" + ex.getLocalizedMessage().toString(), ToastManager.ERROR, 1);
            return null;
        }

    }
}
