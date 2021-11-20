package com.indicados.list.Repository;

import com.indicados.list.model.DocCSV;
import com.indicados.list.model.ResultCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DocCSVJDBCRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String SQLIN = "select film.id, film.year, film.title, film.studios, film.producers, film.winner from filmes as film where film.id in (";
    private final String SQL = "select film.id, film.year, film.title, film.studios, film.producers, film.winner " +
            "from filmes as film where upper(winner)  = 'YES' and film.producers in ( select tmp.producers from " +
            "(select f.producers, count(f.producers) as cont from  filmes as f where upper(winner)  = 'YES' group by f.producers) as tmp where tmp.cont > 1) order by film.year, film.producers";

    public List<ResultCSV> findAllResul() {
        return jdbcTemplate.query(SQL, BeanPropertyRowMapper.newInstance(ResultCSV.class));
    }

    public List<DocCSV> findIdIn(String ids) {
        String sqlIn = SQLIN + ids + ")";
        return jdbcTemplate.query(sqlIn, BeanPropertyRowMapper.newInstance(DocCSV.class));
    }

}
