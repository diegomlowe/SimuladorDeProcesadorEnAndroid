package documents.diego.procesador.views;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import documents.diego.procesador.ES;
import documents.diego.procesador.ExpandableProcesador;
import documents.diego.procesador.Procesador;
import documents.diego.procesador.Proceso;
import documents.diego.procesador.R;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    Spinner spinner;
    String politica = "Round Robin";

    ArrayList<ArrayList<String>> tabla;// = new ArrayList<>();

    RecyclerView recyclerProcesos;


    public static List<Procesador> procesadoresList;

    ArrayList<Proceso> procesosList;

    public static int np=0;


    Procesador procesador;
    Proceso p;

    ProcesosAdapter adapterProcesos;

    LinearLayoutManager lm;

    NavigationView navigationView;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle mDrawerToggle;

    FragmentProceso fragmentProceso = new FragmentProceso();

    FragmentManager fm = getSupportFragmentManager();

    FrameLayout frameLayout;

    Button backFromFragment;
    Button enter;

    TextView txtViewDesalojo;
    EditText utRSO, utES, utDesalojo, etDesalojo;
    boolean desaolojoVisible = true;

    TextView prueba;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this, R.array.politicas, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);
        spinner.setOnItemSelectedListener(this);

        backFromFragment = findViewById(R.id.button_back_fragment);
        enter = findViewById(R.id.btn_enter);


        prueba = findViewById(R.id.txt_view_prueba);


        if(savedInstanceState!=null){

            procesadoresList = savedInstanceState.getParcelableArrayList("lista procesadores");
            procesosList = savedInstanceState.getParcelableArrayList("lista procesos");
            //adapterProcesos.notifyDataSetChanged();


        } else {

            procesadoresList = new ArrayList<>();
            procesosList = new ArrayList<>();
        }

        prueba.setText(String.valueOf(procesosList.size()));


        drawerLayout =(DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        frameLayout = (FrameLayout)findViewById(R.id.frame_layout_fragment_nav_drawer);

        utRSO = (EditText) findViewById(R.id.et_ut_so);
        utES = (EditText) findViewById(R.id.et_ut_es);
        utDesalojo = (EditText)findViewById(R.id.et_ut_desalojo);


        txtViewDesalojo = (TextView)findViewById(R.id.txt_desalojo);
        etDesalojo = (EditText)findViewById(R.id.et_ut_desalojo);

        // RECYCLERVIEW

        lm = new LinearLayoutManager(this);
        lm.setSmoothScrollbarEnabled(false);
        lm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerProcesos = (RecyclerView)findViewById(R.id.recycler_view_main);
        recyclerProcesos.setLayoutManager(lm);
        adapterProcesos = new ProcesosAdapter(procesosList);
        recyclerProcesos.setLongClickable(true);
        recyclerProcesos.setAdapter(adapterProcesos);
        //recyclerProcesos.setNestedScrollingEnabled(false);



    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {

        if(procesadoresList.size()>0)outState.putParcelableArrayList("lista procesadores", (ArrayList<? extends Parcelable>) procesadoresList);
        if(procesosList.size()>0)outState.putParcelableArrayList("lista procesos", procesosList);

        super.onSaveInstanceState(outState);
    }

    public static int getNP(){return np;}

    public static List<Procesador> getProcesadoresList(){return procesadoresList;}

    //SPINNER
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        politica = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), politica, Toast.LENGTH_SHORT).show();


        if(politica.equals("Round Robin")) {

            txtViewDesalojo.setVisibility(View.VISIBLE);
            etDesalojo.setVisibility(View.VISIBLE);

        } else if (politica.equals("MCP")||politica.equals("FIFO")||politica.equals("MCP con Desalojo")){

            txtViewDesalojo.setVisibility(View.INVISIBLE);
            etDesalojo.setVisibility(View.INVISIBLE);
            desaolojoVisible=false;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //ENTER

    public void onClickEnter(View view){

        boolean pass=true;

        try {

            if(Integer.valueOf(utRSO.getText().toString())<=0) {
                utRSO.setError("Las UT del SO deben ser mayor a cero");
                pass=false;
            }
        } catch (NumberFormatException e){

            utRSO.setError("Este campo no puede quedar vacio");
            pass=false;

        }

        try {

            if(Integer.valueOf(utES.getText().toString())<=0) {
                utES.setError("Las UT de E/S deben ser mayor a cero");
                pass=false;
            }
        } catch (NumberFormatException e){

            utES.setError("Este campo no puede quedar vacio");
            pass=false;

        }

        if(desaolojoVisible){

            try {

                if(Integer.valueOf(utDesalojo.getText().toString())<=0) {
                    utDesalojo.setError("Las UT de E/S deben ser mayor a cero");
                    pass=false;
                }
            } catch (NumberFormatException e){

                utDesalojo.setError("Este campo no puede quedar vacio");
                pass=false;

            }
        }

        if(procesosList.size()==0)pass=false;

        if(pass){

            for(Proceso p: procesosList){

                for(ES es: p.getEsList()){

                    es.setUt(Integer.valueOf(utES.getText().toString()));

                }

            }


            procesador = new Procesador(String.valueOf(procesadoresList.size()+1), procesosList,  Integer.valueOf(utRSO.getText().toString()),
                    Integer.valueOf(utES.getText().toString()), politica);


            int desalojo=0;

            if(etDesalojo.getVisibility()==View.VISIBLE) desalojo=Integer.valueOf(etDesalojo.getText().toString());


            tabla = procesador.procesar(desalojo);

            procesadoresList.add(procesador);




            Intent intent = new Intent(this, SecondActivity.class);

            Bundle args = new Bundle();


            args.putStringArrayList("Tiempo", tabla.get(0));
            args.putStringArrayList("Rutinas", tabla.get(1));
            args.putStringArrayList("Ejecutando", tabla.get(2));
            args.putStringArrayList("ColaListos", tabla.get(3));
            args.putStringArrayList("ColaBloqueados", tabla.get(4));

/*
            args.putParcelable("procesador", procesador);
            args.putInt("desalojo", desalojo);

            //procesador=null;

/*

            Fragment tableFragment = new TableFragment();
            tableFragment.setArguments(args);
            FragmentManager fm = getSupportFragmentManager();

            fm.beginTransaction().replace(R.id.frame_layout_fragment_nav_drawer, tableFragment).addToBackStack(null).commit();*/


            onSaveInstanceState(new Bundle());




            intent.putExtras(args);
            startActivity(intent);

        }

    }

    //NEW PROCESS FRAGMENT

    public void onClickFAB(View view){


        enter.setVisibility(View.INVISIBLE);


        backFromFragment.setVisibility(View.VISIBLE);
        fm.beginTransaction().replace(R.id.frame_layout_fragment_main, fragmentProceso).commit();

    }

    public void onClickAgregarProceso(View view){

        boolean pass = true;

        try {

            if(Integer.valueOf(fragmentProceso.ti.getText().toString())<=0) {
                fragmentProceso.ti.setError("El valor del tiempo de ingreso debe ser mayor a cero");
                pass=false;
            }
        } catch (NumberFormatException e){

            fragmentProceso.ti.setError("Este campo no puede quedar vacio");
            pass=false;

        }

        try {

            if (Integer.valueOf(fragmentProceso.ut.getText().toString()) <= 0) {
                fragmentProceso.ut.setError("El valor del tiempo de ingreso debe ser mayor a cero");
                pass = false;

            }
        }catch(NumberFormatException e){

            fragmentProceso.ut.setError("Este campo no puede quedar vacio");
            pass = false;

        }


        try{

            if(!fragmentProceso.es1.getText().equals(null)&&!fragmentProceso.ut.getText().equals(null)) {
                if (Integer.valueOf(fragmentProceso.es1.getText().toString()) >= Integer.valueOf(fragmentProceso.ut.getText().toString())) {
                    fragmentProceso.es1.setError("El ingreso de la E/S debe ser menor que las unidades de tiempo del proceso");
                    pass = false;
                } else if (Integer.valueOf(fragmentProceso.es1.getText().toString()) <= 0) {
                    fragmentProceso.es2.setError("El ingreso de la E/S debe ser mayor a cero");
                    pass = false;

                }
            }

            if(!fragmentProceso.es2.getText().equals(null)&&!fragmentProceso.ut.getText().equals(null)) {

                if (Integer.valueOf(fragmentProceso.es2.getText().toString()) >= Integer.valueOf(fragmentProceso.ut.getText().toString())) {
                    fragmentProceso.es2.setError("El ingreso de la E/S debe ser menor que las unidades de tiempo del proceso");
                    pass = false;
                } else if (Integer.valueOf(fragmentProceso.es2.getText().toString()) <= 0) {
                    fragmentProceso.es2.setError("El ingreso de la E/S debe ser mayor a cero");
                    pass = false;

                }
            }

            if(!fragmentProceso.es3.getText().equals(null)&&!fragmentProceso.ut.getText().equals(null)) {

                if (Integer.valueOf(fragmentProceso.es3.getText().toString()) >= Integer.valueOf(fragmentProceso.ut.getText().toString())) {
                    fragmentProceso.es3.setError("El ingreso de la E/S debe ser menor que las unidades de tiempo del proceso");
                    pass = false;
                } else if (Integer.valueOf(fragmentProceso.es3.getText().toString()) <= 0) {
                    fragmentProceso.es3.setError("El ingreso de la E/S debe ser mayor a cero");
                    pass = false;

                }
            }



        } catch (NumberFormatException e){

           // pass=false;

        }


        if(pass){

            ArrayList<ES> esList = new ArrayList<>();

            try{

                esList.add(new ES(Integer.valueOf(fragmentProceso.es1.getText().toString())));

            } catch(NumberFormatException e){}

            try{

                esList.add(new ES(Integer.valueOf(fragmentProceso.es2.getText().toString())));

            }catch(NumberFormatException e){}

            try{

                esList.add(new ES(Integer.valueOf(fragmentProceso.es3.getText().toString())));

            }catch(NumberFormatException e){}


            p = new Proceso(np+1, Integer.valueOf(fragmentProceso.ut.getText().toString()),
                    Integer.valueOf(fragmentProceso.ti.getText().toString()), esList );

            procesosList.add(p);

            adapterProcesos.notifyDataSetChanged();

            np++;


            fm.beginTransaction().remove(fragmentProceso).commitAllowingStateLoss();
            backFromFragment.setVisibility(View.INVISIBLE);
            enter.setVisibility(View.VISIBLE);


            fragmentProceso.ut.setText(null);
            fragmentProceso.ti.setText(null);
            fragmentProceso.es1.setText(null);
            fragmentProceso.es2.setText(null);
            fragmentProceso.es3.setText(null);

            prueba.setText(String.valueOf(procesosList.size()));

            Toast.makeText(this, "P"+p.getId()+ " agregado", Toast.LENGTH_SHORT).show();



        }

    }

    public void onClickOutsideFragment(View view){

        fm.beginTransaction().remove(fragmentProceso).commit();
        backFromFragment.setVisibility(View.INVISIBLE);
        enter.setVisibility(View.VISIBLE);



    }


    //NAVIGATION VIEW

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mDrawerToggle.onOptionsItemSelected(item)){

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            Fragment fragment;


            if(item.getItemId()==R.id.nav_historial){

               /* Bundle args = new Bundle();

                args.putParcelableArrayList("lista procesadores", procesadoresList);*/

                frameLayout.setVisibility(View.VISIBLE);
                fragment = new FragmentHistorial();
               // fragment.setArguments(args);
                fragmentTransaction.replace(R.id.frame_layout_fragment_nav_drawer, fragment).commit();



            } else if(item.getItemId()==R.id.nav_teoria){

                fragment = new FragmentTeoria();
                frameLayout.setVisibility(View.VISIBLE);
                fragmentTransaction.replace(R.id.frame_layout_fragment_nav_drawer, fragment).commit();

            } else if(item.getItemId()==R.id.nav_simulador){

                frameLayout.setVisibility(View.INVISIBLE);

            }

        drawerLayout.closeDrawers();


        return false;
    }
}
