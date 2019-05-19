package com.mequa.mequasovsoft;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.util.Strings;
import com.mequa.mequasovsoft.BO.UserBO;
import com.mequa.mequasovsoft.CALBACKS.UserCalback;
import com.mequa.mequasovsoft.MODAL.User;
import com.mequa.mequasovsoft.Util.Setings;

import org.json.JSONException;

import java.io.IOException;

public class principal extends AppCompatActivity {

    UserBO ubo =  new UserBO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ((Button) findViewById(R.id.buttonCadastrar)).setOnClickListener(cadastrar);
        ((Button) findViewById(R.id.buttonentrar)).setOnClickListener(logar);

        verificaLogado();
    }

    public void verificaLogado(){
        try {
            User u = ubo.verificaLogado("",getApplicationContext());
            if(u != null) {
                Setings.user = u;
                Intent intent = new Intent(principal.this, ListMedicao.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    View.OnClickListener logar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            User u = new User();
            u.setId(((TextView)findViewById(R.id.TextCPF)).getText().toString());
            u.setCPF(((TextView)findViewById(R.id.TextCPF)).getText().toString());
            u.setSenha(((TextView)findViewById(R.id.TextSenha)).getText().toString());


            try {
                ubo.Login(v, new UserCalback() {
                    @Override
                    public <T> void onCalback(T User) {
                        try {
                            ubo.addFile((User) User,getApplicationContext());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(principal.this, ListMedicao.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }, u);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    };

    View.OnClickListener cadastrar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            User u = new User();
            u.setId(((TextView)findViewById(R.id.TextCPF)).getText().toString());
            u.setCPF(((TextView)findViewById(R.id.TextCPF)).getText().toString());
            u.setSenha(((TextView)findViewById(R.id.TextSenha)).getText().toString());

            if(Strings.emptyToNull(u.getSenha()) != null && Strings.emptyToNull(u.getCPF()) != null){
                ubo.add(u, getApplicationContext(), new UserCalback() {
                    @Override
                    public <T> void onCalback(T User) {
                        try {
                            ubo.addFile((User) User,getApplicationContext());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(principal.this, ListMedicao.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
            }else{
                Snackbar.make(v, "CPF e Senha não podem Estar Brancos.", Snackbar.LENGTH_LONG).show();
            }

        }
    };

    /*@Override
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
    }*/
}
