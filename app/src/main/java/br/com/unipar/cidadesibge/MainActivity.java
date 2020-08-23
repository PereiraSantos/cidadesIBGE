package br.com.unipar.cidadesibge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import br.com.unipar.cidadesibge.adapter.CidadeAdapter;
import br.com.unipar.cidadesibge.adapter.EstadoAdapter;
import br.com.unipar.cidadesibge.entities.Cidade;
import br.com.unipar.cidadesibge.entities.Estado;
import br.com.unipar.cidadesibge.rest.CidadeService;
import br.com.unipar.cidadesibge.rest.EstadoService;
import br.com.unipar.cidadesibge.service.RetrofitService;
import br.com.unipar.cidadesibge.task.AsyncCidadeInsert;
import br.com.unipar.cidadesibge.task.AsyncCidadeListAll;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView cidades;
    private ListView estados;
    private  List<Cidade> listaCidades = new ArrayList<Cidade>();
    private TextView id;
    private String textoPadrao = "deve ser informado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.cidades = findViewById(R.id.lstItens);
        this.estados = findViewById(R.id.lstItens);

        recarregarEstado();
    }

    public void carregarMunicipios(View view){
        id = findViewById(R.id.estado);
        if(id.getText().toString().equals("")) mensagem("Id "+ textoPadrao);
        else {
            listarMunicipil();
            limparFormulario();
        }
    }

    public void recarregarEstado(View view){
        recarregarEstado();
        limparFormulario();

    }

    public void validaEstado(View view){
        id = findViewById(R.id.estado);
        if(id.getText().toString().equals("")) mensagem("Id "+ textoPadrao);
        else {
            salvaCidadeMunicipil(id);
            limparFormulario();
        }
    }

    public void listarMunicipil(){
        id = findViewById(R.id.estado);
        CidadeService cidadeService = RetrofitService.getInstance().create(CidadeService.class);
        final WeakReference<Context> weakReference = new WeakReference(this);
        cidadeService.groupList(Integer.parseInt(id.getText().toString())).enqueue(new Callback<List<Cidade>>() {
            @Override
            public void onResponse(Call<List<Cidade>> call, Response<List<Cidade>> response) {
                CidadeAdapter cidadeAdapter = new CidadeAdapter(response.body(), weakReference);
                cidades.setAdapter(cidadeAdapter);

            }

            @Override
            public void onFailure(Call<List<Cidade>> call, Throwable t) {
                Toast.makeText(weakReference.get(), "Não foi possível carregar as cidades.", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void salvaCidadeMunicipil(TextView id){
       Cidade cidade = new Cidade();
        CidadeService cidadeService = RetrofitService.getInstance().create(CidadeService.class);
        final WeakReference<Context> weakReference = new WeakReference(this);
        cidadeService.groupList(Integer.parseInt(id.getText().toString())).enqueue(new Callback<List<Cidade>>() {
            @Override
            public void onResponse(Call<List<Cidade>> call, Response<List<Cidade>> response) {
                listaCidades.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Cidade>> call, Throwable t) {
                Toast.makeText(weakReference.get(), "Não foi possível carregar as cidades.", Toast.LENGTH_LONG).show();
            }
        });

        new AsyncCidadeInsert(MainActivity.this, listaCidades).execute();
        mensagem("Cidades salva com sucesso");

    }

    public void listarCidade(View view){
        new AsyncCidadeListAll(MainActivity.this).execute();
    }

    public void limparFormulario(){
        id.setText("");
    }

    public void recarregarEstado(){
        EstadoService estadoService  = RetrofitService.getInstance().create(EstadoService.class);
        final WeakReference<Context> weakReference = new WeakReference(this);
        estadoService.findAll().enqueue(new Callback<List<Estado>>() {
            @Override
            public void onResponse(Call<List<Estado>> call, Response<List<Estado>> response) {
                EstadoAdapter estadoAdapter = new EstadoAdapter(response.body(), weakReference);
                estados.setAdapter(estadoAdapter);
            }

            @Override
            public void onFailure(Call<List<Estado>> call, Throwable t) {
                Toast.makeText(weakReference.get(), "Não foi possível carregar as cidades.", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void mensagem(String texto){
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show();
    }
}

/*
   * usando async devido esta acorendo erro.
     [Cannot access database on the main thread since it may
     potentially lock the UI for a long period of time.]
   * assim não foi usado OpenHelper.

 */