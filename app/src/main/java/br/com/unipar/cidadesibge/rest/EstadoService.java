package br.com.unipar.cidadesibge.rest;

import java.util.List;

import br.com.unipar.cidadesibge.entities.Cidade;
import br.com.unipar.cidadesibge.entities.Estado;
import retrofit2.Call;
import retrofit2.http.GET;

public interface EstadoService {

    @GET("/api/v1/localidades/estados")
    Call<List<Estado>> findAll();
}
