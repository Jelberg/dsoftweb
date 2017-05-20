package com.example.elberg.prueba2principal;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.ClipData;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    Toolbar toolbar ;
    ArrayList<Activ> _activs = new ArrayList<Activ>();
    ListView _listView;
    ArrayAdapter<Activ> adaptador;

    private TextView _tvdisplaydate;
    private FloatingActionButton _fabChangeDate;

    private int year;
    private int month;
    private int day;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setCurrentDate();
        clickEvent();

        // Diseño del toolbar
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        getSupportActionBar().setTitle(R.string._ttl_m05_activity);

        _listView = (ListView) findViewById(R.id.lv_m05_listactivity);

        inflateActivitys();
        //Instanciamos el adaptador, le pasamos el arraylist y al listview la pasamos nuestro adapter
        // como adaptador de contenido
        adaptador = new ArrayAdapter<Activ>( this, android.R.layout.simple_list_item_1, _activs);

        /**
         *  Metodo para seleccionar un elemento de la lista
         */
        _listView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

              //  if(_listView.getItem())

                return true;
            }
        });

    }


    /**
     * Matodo que llena los atributos que se veran en la lista
     */
    public void inflateActivitys (){
        //Aqui deberia haber un metodo que llene el ArrayList con los objetos
        //este add solo es una prueba para que se pueda ver ellist view personalizado
        _activs.add(new Activ(12,"YOGA",12.43,34.34,"12-12-12"));
        _activs.add(new Activ(12,"futbol",12.43,34.34,"12-12-12"));
        _activs.add(new Activ(12,"tennis",12.43,34.34,"12-12-12"));
        _activs.add(new Activ(12,"escala",12.43,34.34,"12-12-12"));
        _listView.setAdapter(new AdapterItem(this, _activs));
    }


    /**
     * Infla el menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Acciones  que son consecuencia de opcionar algun item del action bar
     * @param item  Seleccion del item
     * @return
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.itm_m05_delete) {
            Toast.makeText( getApplicationContext(),R.string._tst_m05_messagedelete,
                    Toast.LENGTH_SHORT).show();
                    dialogConfirmation();

            return true;
        }
        if (id == R.id.itm_m05_modify) {
            Toast.makeText( getApplicationContext(),R.string._tst_m05_messagemodify, Toast.LENGTH_SHORT).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Dialogo emergente para confirmacion de eliminacion de actividad
     */

    public void dialogConfirmation(){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle(R.string._ttl_m05_questiondeleteactivity);
        builder.setMessage(R.string._dlg_m05_quetiondeleteactivity)
                .setPositiveButton(R.string._dlg_m05_done, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                })
                .setNegativeButton(R.string._dlg_m05_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        android.app.AlertDialog dialog = builder.create();
        dialog.show();
    }


    /**
     * Actualiza el textView que muestra la fecha
     * @param view
     * @param year
     * @param monthOfYear
     * @param dayOfMonth
     */

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        this.month = monthOfYear;
        this.day = dayOfMonth;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        String s = formatter.format(calendar.getTime());

        _tvdisplaydate.setText(s);
    }


    /**
     * Pone la fecha actual para que al llamar el DateDialog lo ubique en esa fecha
     */
    public void setCurrentDate()
    {
        _tvdisplaydate= (TextView) findViewById(R.id.tv_m05_date);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        String s = formatter.format(c.getTime());

        //_tvdisplaydate.setText(s);
    }


    /**
     *  Acciona el DateDialog para el la seleccion de la fecha
     */
    public void clickEvent()
    {
        _fabChangeDate = (FloatingActionButton) findViewById(R.id.fab_m05_datepickerserch);
        _fabChangeDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                {
                    Bundle b = new Bundle();
                    b.putInt(DateDialog.YEAR, year);
                    b.putInt(DateDialog.MONTH, month);
                    b.putInt(DateDialog.DATE, day);
                    DialogFragment picker = new DateDialog();
                    picker.setArguments(b);
                    picker.show(getFragmentManager(), "fragment_date_picker");
                }
            }
        });

    }


}
