package br.com.unipar.cidadesibge.rest;

import java.util.List;


import br.com.unipar.cidadesibge.entities.Cidade;
import br.com.unipar.cidadesibge.entities.Estado;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface CidadeService {

    @GET("/api/v1/localidades/estados/{UF}/municipios")
    Call<List<Cidade>>  groupList(@Path("UF") int groupId); //findAll(int UF);

}
