package kss.kssfooddroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import kss.kssfooddroid.util.ToastManager;

/**
 * Created by KSS on 25/06/2015.
 */
public class ItemsMenuAdapter extends BaseAdapter {
    private Context context;
    private List<ItemMenu> items;


    public ItemsMenuAdapter(Context context, List<ItemMenu> items) {
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
                rowView = inflater.inflate(R.layout.views_itemsmenu, parent, false);
            }

            ItemMenu item = this.items.get(position);
            TextView textView = (TextView) rowView.findViewById(R.id.textView1);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView1);

            String strPath = "/mnt/sdcard/dcim/img/" + item.imagen.trim();
            if (item.descr == null) {
                textView.setText(item.nombre.trim());
            } else {
                textView.setText(item.descr.trim());
            }
            // Image Resource
            Bitmap bm =null;
            if ( item.imagen.trim().length()>0){
                 bm = BitmapFactory.decodeFile(strPath);
            }
            if (bm != null) {
                imageView.setImageBitmap(bm);
                textView.getLayoutParams().height = 25;
                if (textView.getText().length() > 11) {
                    textView.setSingleLine(false);
                    textView.setTextSize(12);
                    textView.setText(textView.getText());
                } else {
                    textView.setSingleLine(true);
                    textView.setTextSize(15);
                }
            } else {
                textView.setSingleLine(false);
                textView.setTextSize(17);
                textView.setText(textView.getText());
                imageView.setVisibility(View.INVISIBLE);
            }
            return rowView;
        } catch (Exception ex) {
            ToastManager.show(context, ex.getMessage().toString() + "\n" + ex.getLocalizedMessage().toString(), ToastManager.ERROR, 1);
            return null;
        }

    }

}
