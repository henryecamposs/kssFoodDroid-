package kss.kssfooddroid;

/**
 * Created by KSS on 10/07/2015.
 */

import java.lang.reflect.Method;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class kss_widget_NumberPicker {
    private Object picker;
    private Class<?> classPicker;

    public kss_widget_NumberPicker(LinearLayout numberPickerView) {
        picker = numberPickerView;
        classPicker = picker.getClass();

       /* View upButton = numberPickerView.getChildAt(0);
        upButton.setBackgroundResource(R.drawable.kss_widget_numpicker_btnup);

        EditText edDate = (EditText) numberPickerView.getChildAt(1);
        edDate.setTextSize(17);
        edDate.setBackgroundResource(R.drawable.kss_widget_numpicker_background);

        View downButton = numberPickerView.getChildAt(2);
        downButton.setBackgroundResource(R.drawable.kss_widget_numpicker_btndown);*/
    }

    public void setRange(int start, int end) {
        try {
            Method m = classPicker.getMethod("setRange", int.class, int.class);
            m.invoke(picker, start, end);
        } catch (Exception e) {
        }
    }

    public Integer getCurrent() {
        Integer current = -1;
        try {
            Method m = classPicker.getMethod("getCurrent");
            current = (Integer) m.invoke(picker);
        } catch (Exception e) {
        }
        return current;
    }

    public void setCurrent(int current) {
        try {
            Method m = classPicker.getMethod("setCurrent", int.class);
            m.invoke(picker, current);
        } catch (Exception e) {
        }
    }
}