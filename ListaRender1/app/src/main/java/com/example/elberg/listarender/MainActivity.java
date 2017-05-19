package com.example.elberg.listarender;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;


import com.example.library_swipelistview.SwipeListViewTouchListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<Activ> _activs = new ArrayList<Activ>();
    ListView _listView;
    ArrayAdapter<Activ> adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _listView = (ListView) findViewById(R.id.lv_m05_listactivity);


          //Aqui deberia haber un metodo que llene el ArrayList con los objetos
        //este add solo es una prueba para que se pueda ver ellist view personalizado
        _activs.add(new Activ(12,"YOGA",12.43,34.34,"12-12-12"));
        _activs.add(new Activ(12,"futbol",12.43,34.34,"12-12-12"));
        _activs.add(new Activ(12,"tennis",12.43,34.34,"12-12-12"));
        _activs.add(new Activ(12,"escala",12.43,34.34,"12-12-12"));

        _listView.setAdapter(new AdapterItem(this, _activs));


        //Instanciamos el adaptador, le pasamos el arraylist y al listview la pasamos nuestro adapter como adaptador de contenido
        adaptador = new ArrayAdapter<Activ>( this, android.R.layout.simple_list_item_1, _activs);
        //_listView.setAdapter(adaptador);

        //Deslizar item para borrarlo
        SwipeListViewTouchListener touchListener =new SwipeListViewTouchListener(_listView,new SwipeListViewTouchListener.OnSwipeCallback() {
            @Override
            public void onSwipeLeft(ListView listView, int [] reverseSortedPositions) {
                //Aqui ponemos lo que hara el programa cuando deslizamos un item ha la izquierda
                _activs.remove(reverseSortedPositions[0]);
                adaptador.notifyDataSetChanged();
            }

            @Override
            public void onSwipeRight(ListView listView, int [] reverseSortedPositions) {
                //Aqui ponemos lo que hara el programa cuando deslizamos un item ha la derecha
                _activs.remove(reverseSortedPositions[0]);
                adaptador.notifyDataSetChanged();
            }
        },true, false);

        //Escuchadores del listView
        _listView.setOnTouchListener(touchListener);
        _listView.setOnScrollListener(touchListener.makeScrollListener());


    _listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            return false;
        }
    });

    }




 /**
     * Metodo que llama al adapter item para cargar la lista
     */

    public void inflateList (){
        ListView lv = (ListView) findViewById(R.id.lv_m05_listactivity);
        AdapterItem adapter = new AdapterItem(this, _activs);
        lv.setAdapter(adapter);
    }


}
