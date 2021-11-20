package com.indicados.list.dto;

import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
public class ResultDocCSV {

    @Column(name = "min")
    List<ResultBase> min;

    @Column(name = "max")
    List<ResultBase> max;
}
