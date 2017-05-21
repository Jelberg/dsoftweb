package com.example.elberg.prueba2principal;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsoluteLayout;
import android.widget.Checkable;

/**
 * Created by elberg on 21/05/17.
 */

public class CheckedAbsoluteLayout extends AbsoluteLayout implements Checkable {

    /**
     * Esta variable es la que nos sirve para almacenar el estado de este widget
     */
    private boolean _checked=false;

    /**
     * Este array se usa para que los drawables que se usen
     * reaccionen al cambio de estado especificado
     * En nuestro caso al "state_checked"
     * que es el que utilizamos en nuestro selector
     */
    private final int[] CHECKED_STATE_SET = {
            android.R.attr.state_checked
    };


    public CheckedAbsoluteLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Este método es el que cambia el estado del widget
     * @param checked true para activarlo y false para desactivarlo
     */

    @Override
    public void setChecked(boolean checked) {
        _checked = checked;
        //Cuando cambiamos el estado, debemos informar a los drawables
        //que este widget tenga vinculados
        refreshDrawableState();
        invalidate();
    }

    /**
     * Este método devuelve el estado del widget
     *
     * @return true o false
     */
    @Override
    public boolean isChecked() {
        return _checked;
    }

    /**
     * Este método cambia el estado de nuestro widget
     * Si estaba activo se desactiva y viceversa
     */
    @Override
    public void toggle() {
        setChecked(!_checked);
    }

    /**
     * Se encarga de combinar los diferentes "estados" de un widget
     * para informar a los drawables.
     * Si nuestro widget está "checked" le añadimos el estado CHECKED_STATE_SET
     *
     * @return el array de estados del widget
     */
    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }
}
