package com.indicados.list.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "filmes")
public class DocCSV {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "year", length = 5, nullable = false)
    private String year;

    @Column(name = "title", length = 1000)
    private String title;

    @Column(name = "studios", length = 1000)
    private String studios;

    @Column(name = "producers", length = 1000)
    private String producers;

    @Column(name = "winner", length = 60)
    private String winner;

    @Column(name = "diferenca", insertable = false)
    private String diferenca;

    @Override
    public String toString() {
        return "DocCSV{" +
                "year='" + year + '\'' +
                ", title='" + title + '\'' +
                ", studios='" + studios + '\'' +
                ", producers='" + producers + '\'' +
                ", winner='" + winner + '\'' +
                '}';
    }
}
