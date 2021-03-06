package com.mequa.mequasovsoft;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.mequa.mequasovsoft.BO.MedicaoBO;
import com.mequa.mequasovsoft.BO.PlantaBO;
import com.mequa.mequasovsoft.BO.UserBO;
import com.mequa.mequasovsoft.CALBACKS.FireBaseCalback;
import com.mequa.mequasovsoft.CALBACKS.MedicaoApiBaseCalback;
import com.mequa.mequasovsoft.MODAL.Medicao;
import com.mequa.mequasovsoft.MODAL.Planta;
import com.mequa.mequasovsoft.Util.Setings;
import com.mequa.mequasovsoft.Util.Util;
import com.mequa.mequasovsoft.item_view_holder.MedicaoListItem;
import com.mequa.mequasovsoft.Util.BaseActivity;
import com.xwray.groupie.GroupAdapter;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ListMedicao extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public GroupAdapter adapter =  new GroupAdapter();
    private Spinner spn1;
    PlantaBO pbo = new PlantaBO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_medicao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Atualizar pantas

        Util.ctrateprogressDialog(ListMedicao.this);
        RecyclerView ListmedicaoView = findViewById(R.id.medicaoListView);
        ListmedicaoView.setLayoutManager(new GridLayoutManager(ListMedicao.this, 1));
        ListmedicaoView.setAdapter(adapter);

        try {
            pbo.setEventiListenerMedicao(getApplicationContext());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        loadPlanta();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.medir);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                MedicaoBO m =  new MedicaoBO();
                m.medir(view, getApplicationContext(), new MedicaoApiBaseCalback() {
                    @Override
                    public void onCalback(Medicao medicao) {
                        List<Medicao> lm = new ArrayList<Medicao>();
                        lm.add(medicao);
                        populateViewListPessoa(lm,adapter);
                        Util.hidload("medindo");
                        Snackbar.make(findViewById(R.id.nav_view), R.string.msg_sucesso_medida, Snackbar.LENGTH_LONG).show();
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openWifi();
                    }
                });
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ((TextView) navigationView.getHeaderView(0).findViewById(R.id.CPFUser)).setText(Setings.user.cpf);

        try {
            Load();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void loadPlanta(){
        spn1 = (Spinner) findViewById(R.id.listadeplantas);

        try {
            List<Planta> lplanta =  pbo.listaAll(getApplicationContext());
            List<String> lsnplanta =  new ArrayList<String>();
            lsnplanta.add(getResources().getString(R.string.selectpl));


            if(lplanta!=null && lplanta.size() > 0) {
                for (Planta p : lplanta) {
                    lsnplanta.add(p.getNome());
                }

                ArrayAdapter<String> arrayAdapter =
                        new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, lsnplanta);

                ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
                spinnerArrayAdapter.setDropDownViewResource(R.layout.planta_spinner_item);
                spn1.setAdapter(spinnerArrayAdapter);

                spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
                        String nome = parent.getItemAtPosition(posicao).toString();

                        try {
                            if (!nome.equals(getResources().getString(R.string.selectpl))) {
                                Setings.planta = pbo.getPlanta(nome, getApplication());
                            } else {
                                Setings.planta = null;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }else{
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        loadPlanta();
                    }
                }, 3301);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_medicao, menu);
        return true;
    }

    private void Load() throws InstantiationException, IllegalAccessException {
        MedicaoBO Lpbo = new MedicaoBO();

        Lpbo.setEventiListenerMedicao(new FireBaseCalback() {
            @Override
            public <T> void onCalback(List<T> list) {
                List<Medicao> lpromo = (List<Medicao>) list;
                Collections.sort(lpromo);
                adapter.clear();
                populateViewListPessoa(lpromo,adapter);

                Util.hidload("medindo");
                //Snackbar.make(findViewById(R.id.ListMedicaoView), R.string.msg_sucesso_medida, Snackbar.LENGTH_LONG).show();
            }
        },Setings.user);
    }

    public void populateViewListPessoa(List<Medicao> lm, GroupAdapter ga){
        for (Medicao m: lm) {
            ga.add(new MedicaoListItem(m, getApplicationContext()));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reloadPl) {
            loadPlanta();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_sair) {
            UserBO ubo = new UserBO();
            try {
                ubo.Deslogando(getApplicationContext());
                Intent intent = new Intent(ListMedicao.this,principal.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
