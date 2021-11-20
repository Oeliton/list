package com.indicados.list.controller;

import com.indicados.list.dto.ResultDocCSV;
import com.indicados.list.model.DocCSV;
import com.indicados.list.service.CSVReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private CSVReadService csvReadService;

    @GetMapping("/find")
    public ResponseEntity<List<DocCSV>> findAll(){
        return ResponseEntity.ok(csvReadService.findAll());
    }

    @GetMapping("/save")
    public ResponseEntity<String> save() throws IOException {
        csvReadService.save();
        return ResponseEntity.ok("Sucesso");
    }

    @GetMapping("/findminmax")
    public ResponseEntity<ResultDocCSV> findMinMax(){
        return ResponseEntity.ok(csvReadService.findMinMax());
    }
}
