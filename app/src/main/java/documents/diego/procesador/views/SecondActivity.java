package documents.diego.procesador.views;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import documents.diego.procesador.Procesador;
import documents.diego.procesador.R;

public class SecondActivity extends AppCompatActivity {

    Fragment listFragment = new ListFragment();
    Fragment tableFragment = new TableFragment();

    Procesador procesador;

    private ArrayList<ArrayList<String>> tabla = new ArrayList<ArrayList<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        Intent intent = getIntent();

        Bundle args = intent.getExtras();


        tableFragment.setArguments(args);

        FragmentManager fm = getSupportFragmentManager();

        fm.beginTransaction().replace(R.id.frame_layout_fragment_second, tableFragment).commit();

    }

    public void onClickList(View view){

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_fragment_second, listFragment).commit();

    }

    public void onCLickTable(View view){

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_fragment_second, tableFragment).commit();
    }
}
