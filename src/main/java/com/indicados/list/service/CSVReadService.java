package com.indicados.list.service;

import com.indicados.list.Repository.DocCSVJDBCRepository;
import com.indicados.list.Repository.DocCSVRepository;
import com.indicados.list.dto.ResultBase;
import com.indicados.list.dto.ResultDocCSV;
import com.indicados.list.model.DocCSV;
import com.indicados.list.model.ResultCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CSVReadService {

    @Autowired
    private DocCSVRepository docCSVRepository;
    @Autowired
    private DocCSVJDBCRepository docCSVJDBCRepository;

    public List<DocCSV> findAll() {
        return docCSVRepository.findAll();
    }

    public List<DocCSV> findInId(String ids) {
        return docCSVJDBCRepository.findIdIn(ids);
    }

    public ResultDocCSV findMinMax() {
        return this.pesqMinMax(docCSVJDBCRepository.findAllResul());
    }

    public ResultDocCSV pesqMinMax(List<ResultCSV> resultCSVS) {

        ResultDocCSV resultDocCSV = new ResultDocCSV();
        List<ResultCSV> produder = new ArrayList<>();
        List<ResultBase> resultBases = new ArrayList<>();

        resultCSVS.forEach(x -> {

            List<ResultCSV> csv = produder.stream().filter(p -> p.getProducers().equals(x.getProducers())).collect(Collectors.toList());
            if (csv != null && csv.size() > 0) {
                ResultBase resultBase = new ResultBase();

                resultBase.setYear(x.getYear());
                resultBase.setYear_two(csv.get(0).getYear());
                resultBase.setTitle(x.getTitle());
                resultBase.setStudios(x.getStudios());
                resultBase.setProducers(x.getProducers());
                resultBase.setDiferenca(getDiferencaValor(x.getYear(), csv.get(0).getYear()));

                resultBases.add(resultBase);
                produder.removeAll(produder);
            } else {
                produder.add(x);
            }

        });

        Integer max = resultBases.stream().max(Comparator.comparingInt(ResultBase::getDiferenca)).get().getDiferenca();
        ;
        Integer min = resultBases.stream().min(Comparator.comparingInt(ResultBase::getDiferenca)).get().getDiferenca();
        ;

        resultDocCSV.setMax(resultBases.stream().filter(x -> x.getDiferenca().equals(max)).collect(Collectors.toList()));
        resultDocCSV.setMin(resultBases.stream().filter(x -> x.getDiferenca().equals(min)).collect(Collectors.toList()));

        return resultDocCSV;
    }

    private Integer getDiferencaValor(String primeiro, String segundo) {
        return Math.abs(Integer.valueOf(primeiro) - Integer.valueOf(segundo));
    }

    private List<ResultBase> convert(List<DocCSV> docCSVS, Integer dif) {
        List<ResultBase> resultBases = new ArrayList<>();
        docCSVS.forEach(x -> {
            ResultBase resultBase = new ResultBase();

            resultBase.setYear(x.getYear());
            resultBase.setTitle(x.getTitle());
            resultBase.setDiferenca(dif);
            resultBase.setStudios(x.getStudios());
            resultBase.setProducers(x.getProducers());

            if (resultBases != null && resultBases.toString().contains(resultBase.getProducers())) {
                resultBases.forEach(s -> {
                    if (s.getProducers() == resultBase.getProducers()) {
                        s.setYear_two(resultBase.getYear());
                    }
                });
            } else {
                resultBases.add(resultBase);
            }
        });
        return resultBases;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void save() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/documento/movielist.csv"));
        String line = br.readLine();

        List<DocCSV> docCSVSList = new ArrayList<>();

        while (line != null) {
            String[] split = line.split(";");
            String[] splitProduce;
            if (split[3].indexOf(",") > 0) {
                splitProduce = split[3].split(",");
            } else {
                splitProduce = split[3].split("and");
            }
            for (int i = 0; i < splitProduce.length; i++) {
                DocCSV docCSV = new DocCSV();

                docCSV.setYear((split.length >= 1) ? split[0] : "");
                docCSV.setTitle((split.length >= 2) ? split[1] : "");
                docCSV.setStudios((split.length >= 3) ? split[2] : "");
                docCSV.setProducers((split.length >= 4) ? splitProduce[i].trim() : "");
                //docCSV.setProducers((split.length >= 4) ? split[3] : "");
                docCSV.setWinner((split.length >= 5) ? split[4] : "");

                docCSVSList.add(docCSV);
            }
            line = br.readLine();
        }

        if (docCSVSList.size() > 0) {
            docCSVRepository.deleteAll();
            docCSVRepository.saveAll(docCSVSList);
            System.out.println("Lista Salva");
        }
    }
}
