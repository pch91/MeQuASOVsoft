package com.mequa.mequasovsoft;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.mequa.mequasovsoft.BO.MedicaoBO;
import com.mequa.mequasovsoft.MODAL.Medicao;
import com.mequa.mequasovsoft.MODAL.User;

public class principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        User u = new User();
        u.setCPF("5555555555555");
        Medicao m = new Medicao();

        m.setChuvendo(true);
        m.setPlanta("teste");
        m.setTemperatura(38);
        m.setUmidade(1555);
        m.setUmidadear(1588);


        MedicaoBO mbo =  new MedicaoBO();
        mbo.add(u,m,getApplicationContext());


        m.setChuvendo(true);
        m.setPlanta("teste");
        m.setTemperatura(36);
        m.setUmidade(1000);
        m.setUmidadear(12222);

        mbo =  new MedicaoBO();
        mbo.add(u,m,getApplicationContext());

        u.setCPF("2222222");

        m.setChuvendo(true);
        m.setPlanta("jogo");
        m.setTemperatura(30);
        m.setUmidade(10222);
        m.setUmidadear(1333);

        mbo =  new MedicaoBO();
        mbo.add(u,m,getApplicationContext());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
