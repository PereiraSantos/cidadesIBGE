package br.com.unipar.cidadesibge.task;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import br.com.unipar.cidadesibge.database.AppDatabase;
import br.com.unipar.cidadesibge.entities.Cidade;

public class AsyncCidadeListAll extends AsyncTask<Integer, Integer, Boolean> {

    private Activity activity;

    public AsyncCidadeListAll(Activity activity){
        this.activity = activity;
    }

    @Override
    protected Boolean doInBackground(Integer... integers) {
       List<Cidade> lista = AppDatabase.getCommection(activity.getApplicationContext()).cidadeDao().cidades();
        return true;
    }
}
