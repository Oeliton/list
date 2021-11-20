package com.indicados.list.model;

import lombok.Data;

import javax.persistence.Column;

@Data
public class ResultCSV {

    @Column(name = "id")
    private Long id;

    @Column(name = "year")
    private String year;

    @Column(name = "title")
    private String title;

    @Column(name = "studios")
    private String studios;

    @Column(name = "producers")
    private String producers;
}
