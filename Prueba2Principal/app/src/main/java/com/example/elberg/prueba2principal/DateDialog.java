package com.example.elberg.prueba2principal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.widget.DatePicker;


/**
 *   Clase para generar en dialogo de fecha
 */
public class DateDialog extends DialogFragment {
    public static final String YEAR = "Year";
    public static final String MONTH = "Month";
    public static final String DATE = "Day";
    private DatePickerDialog.OnDateSetListener _Listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this._Listener = (DatePickerDialog.OnDateSetListener) activity;
    }

    @Override
    public void onDetach() {
        this._Listener = null;
        super.onDetach();
    }


    /**
     * Crea el dialogo con la fecha y retorna la opcion seleccionada
     * @param savedInstanceState
     * @return
     */
    @TargetApi(11)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Bundle b = getArguments();
        int y = b.getInt(YEAR);
        int m = b.getInt(MONTH);
        int d = b.getInt(DATE);

        final DatePickerDialog picker = new DatePickerDialog(getActivity(),
                getConstructorListener(), y, m, d);
       // solo funciona para API's mayores  o iguales a Jelly Bean (16)
        if (hasJellyBeanAndAbove()) {

            // Aqui hace la interaccion con el dialogo si es negativa o positiva la respuesta
            picker.setButton(DialogInterface.BUTTON_POSITIVE,
                    getActivity().getString(R.string._dlg_m05_done),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DatePicker dp = picker.getDatePicker();

                                    _Listener.onDateSet(dp,
                                    dp.getYear(), dp.getMonth(), dp.getDayOfMonth());
                        }
                    });
            picker.setButton(DialogInterface.BUTTON_NEGATIVE,
                    getActivity().getString(R.string._dlg_m05_cancel),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {}
                    });
        }
        return picker;
    }


    /**
     *
     * @return true, si la version del SDK actual es mayor o igual que la version del SDK del Jelly bean
     */
    private static boolean hasJellyBeanAndAbove()
    {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    private DatePickerDialog.OnDateSetListener getConstructorListener() {
        return hasJellyBeanAndAbove() ? null : _Listener;
    }

}
