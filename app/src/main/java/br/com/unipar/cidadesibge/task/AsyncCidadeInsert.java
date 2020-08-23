package br.com.unipar.cidadesibge.task;

import android.app.Activity;
import android.os.AsyncTask;

import java.util.List;

import br.com.unipar.cidadesibge.database.AppDatabase;
import br.com.unipar.cidadesibge.entities.Cidade;

public class AsyncCidadeInsert extends AsyncTask<Integer, Integer, Boolean> {

    private Activity activity;
    private List<Cidade> lista;

    public AsyncCidadeInsert(Activity activity, List<Cidade> lista){
        this.activity = activity;
        this.lista = lista;
    }

    @Override
    protected Boolean doInBackground(Integer... integers) {
        AppDatabase.getCommection(activity.getApplicationContext()).cidadeDao().insertAll(lista);
        return  true;
    }

}
