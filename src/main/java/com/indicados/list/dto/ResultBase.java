package com.indicados.list.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class ResultBase {

    @Column(name = "year")
    private String year;

    @Column(name = "year_two")
    private String year_two;

    @Column(name = "title")
    private String title;

    @Column(name = "studios")
    private String studios;

    @Column(name = "producers")
    private String producers;

    @Column(name = "diferenca")
    private Integer diferenca;
}
