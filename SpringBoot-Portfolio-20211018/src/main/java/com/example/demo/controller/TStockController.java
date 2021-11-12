package com.example.demo.controller;

import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Classify;
import com.example.demo.entity.TStock;
import com.example.demo.repository.ClassifyRepository;
import com.example.demo.repository.TStockRepository;

@RestController
@RequestMapping("/tstock")
public class TStockController {

    @Autowired
    private TStockRepository tStockRepository;
    
    @Autowired
    private ClassifyRepository classifyRepository;

    
    @PostMapping(value = {"/", "/add"})
    @Transactional
    public TStock add(@RequestBody Map<String, String> map) {
        Classify classify = classifyRepository.findById(Integer.parseInt(map.get("classify_id"))).get();
        TStock ts = new TStock();
        ts.setName(map.get("name"));
        ts.setSymbol(map.get("symbol"));
        ts.setClassify(classify);
        tStockRepository.save(ts);
        return ts;
    }
    
    @PutMapping(value = {"/", "/update"})
    @Transactional
    public TStock update(@RequestBody Map<String, String> map) {
        Classify classify = classifyRepository.findById(Integer.parseInt(map.get("classify_id"))).get();
        TStock ts = tStockRepository.findById(Integer.parseInt(map.get("id"))).get();
        ts.setName(map.get("name"));
        ts.setSymbol(map.get("symbol"));
        ts.setClassify(classify);
        //service.gettStockRepository().update(Long.parseLong(map.get("id")), map.get("name"), map.get("symbol"));
        return ts;
    }
    
    @DeleteMapping(value = {"/{id}", "/delete/{id}"})
    @Transactional
    public Boolean delete(@PathVariable("id") Integer id) {
    	tStockRepository.deleteById(id);
        return true;
    }
    
    @GetMapping(value = {"/{id}", "/get/{id}"})
    @Transactional
    public TStock get(@PathVariable("id") Integer id) {
        TStock tStock = tStockRepository.findById(id).get();
        return tStock;
    }

    @GetMapping(value = {"/", "/query"})
    public Iterable<TStock> query() {
        return tStockRepository.findAll();
    }

}