package com.indicados.list.service;

import com.indicados.list.dto.ResultDocCSV;
import com.indicados.list.model.DocCSV;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@AutoConfigureDataJpa
class CSVReadServiceBaseTest {

    @Autowired
    private CSVReadService csvReadService;

    @Test
    public void findAllBaseTrue() {
        List<DocCSV> docCSVS = csvReadService.findAll();

        Assertions.assertNotNull(docCSVS);
    }

    @Test
    public void findMinMaxBase() {
        ResultDocCSV docCSV = csvReadService.findMinMax();

        Assertions.assertNotNull(docCSV.getMax());
        Assertions.assertNotNull(docCSV.getMin());
    }
}