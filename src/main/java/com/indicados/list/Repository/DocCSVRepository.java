package com.indicados.list.Repository;

import com.indicados.list.model.DocCSV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocCSVRepository extends JpaRepository<DocCSV, Long> {

    @Query(value = "select film.id, film.year, film.title, film.studios, film.producers, film.winner from filmes as film where film.producers in (\n" +
            "select tmp.producers from (select f.producers, count(f.producers) as cont from filmes as f group by f.producers) as tmp where tmp.cont > 1) order by film.year, film.producers", nativeQuery = true)
    public List<DocCSV> findLista();

}
