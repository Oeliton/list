package com.indicados.list.service;

import com.indicados.list.Repository.DocCSVJDBCRepository;
import com.indicados.list.Repository.DocCSVRepository;
import com.indicados.list.dto.ResultBase;
import com.indicados.list.dto.ResultDocCSV;
import com.indicados.list.model.DocCSV;
import com.indicados.list.model.ResultCSV;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CSVReadServiceTest {


    @InjectMocks
    private CSVReadService csvReadService;

    @Mock
    private DocCSVRepository docCSVRepository;

    @Mock
    private DocCSVJDBCRepository docCSVJDBCRepository;

    @Test
    public void findAll() {

        when(docCSVRepository.findAll()).thenReturn(createDocCSV());
        List<DocCSV> docCSVS = csvReadService.findAll();

        assertEquals(createDocCSV(), docCSVS);
    }

    @Test
    public void findMinMax() {
        when(docCSVJDBCRepository.findAllResul()).thenReturn(createResultCSV());

        ResultDocCSV resultDocCSV = csvReadService.findMinMax();

        assertEquals(createResultDocCSV(), resultDocCSV);
    }

    private List<DocCSV> createDocCSV() {
        List<DocCSV> docCSVS = new ArrayList<>();
        DocCSV docCSV = new DocCSV();
        docCSV.setProducers("teste");
        docCSVS.add(docCSV);
        return docCSVS;
    }

    private List<ResultCSV> createResultCSV() {
        List<ResultCSV> resultCSVS = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            ResultCSV csv = new ResultCSV();
            csv.setId(1L);
            csv.setYear("1900");
            csv.setTitle("Titulo");
            csv.setStudios("Estudio");
            csv.setProducers("Produtor");
            csv.setId(Long.valueOf(i));
            resultCSVS.add(csv);
        }


        return resultCSVS;
    }

    private ResultDocCSV createResultDocCSV() {
        ResultDocCSV resultDocCSV = new ResultDocCSV();
        ResultBase resultBase = new ResultBase();
        resultBase.setYear("1900");
        resultBase.setYear_two("1900");
        resultBase.setTitle("Titulo");
        resultBase.setStudios("Estudio");
        resultBase.setProducers("Produtor");
        resultBase.setDiferenca(0);


        List<ResultBase> resultMin = new ArrayList<>();
        resultMin.add(resultBase);
        resultDocCSV.setMin(resultMin);

        List<ResultBase> resultMax = new ArrayList<>();
        resultMax.add(resultBase);
        resultDocCSV.setMax(resultMax);

        return resultDocCSV;
    }
}