package br.com.unipar.cidadesibge.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import br.com.unipar.cidadesibge.entities.Cidade;

@Dao
public interface CidadeDao {

    @Query("SELECT * FROM cidades u")
    List<Cidade>cidades();

    @Insert
    void insertAll(List<Cidade> cidades);
}
