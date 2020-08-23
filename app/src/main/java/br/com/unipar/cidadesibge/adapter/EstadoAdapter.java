package br.com.unipar.cidadesibge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;

import br.com.unipar.cidadesibge.R;
import br.com.unipar.cidadesibge.entities.Estado;

public class EstadoAdapter extends BaseAdapter {

    private List<Estado> estados;
    private WeakReference<Context> weakReference;
    private LayoutInflater inflater;

    public EstadoAdapter(List<Estado> estados, WeakReference<Context> weakReference) {
        this.estados = estados;
        this.weakReference = weakReference;

        this.inflater = LayoutInflater.from(weakReference.get());
    }

    @Override
    public int getCount() {
        return estados.size();
    }

    @Override
    public Object getItem(int i) {
        return estados.get(i);
    }

    @Override
    public long getItemId(int i) {
        return estados.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        EstadoAdapter.ViewHolder viewHolder;
        Estado estado = (Estado) getItem(i);

        if (view == null) {
            view = inflater.inflate(R.layout.activity_item, null);

            viewHolder = new EstadoAdapter.ViewHolder();


            viewHolder.tvId = (TextView) view.findViewById(R.id.Id);
            viewHolder.tvNome = (TextView) view.findViewById(R.id.Nome);

            view.setTag(viewHolder);
        } else {
            viewHolder = (EstadoAdapter.ViewHolder) view.getTag();
        }

        viewHolder.tvId.setText(String.valueOf(estado.getId()));
        viewHolder.tvNome.setText(estado.getNome());

        return view;
    }

    static class ViewHolder {

        TextView tvId;
        TextView tvNome;
    }

}
