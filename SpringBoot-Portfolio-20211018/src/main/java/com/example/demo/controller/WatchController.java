package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.TStock;
import com.example.demo.entity.Watch;
import com.example.demo.repository.TStockRepository;
import com.example.demo.repository.WatchRepository;

@RestController
@RequestMapping("/watch")
public class WatchController {
    @Autowired
    private WatchRepository watchRepository;
    
    @Autowired
    private TStockRepository tStockRepository;
    
    @GetMapping(value = {"/{id}", "/get/{id}"})
    @Transactional
    public Watch get(@PathVariable("id") Integer id) {
        Watch watch = watchRepository.findById(id).get();
        watch.gettStocks().size(); // 因為 @ManyToMany 預設資料載入是 Lazy, 所以加入此行可取得 tStocks 資料
        return watch;
    }
    
    @GetMapping(value = {"/", "/query"})
    public List<Watch> query() {
        List<Watch> list = watchRepository.findAll();
        return list;
    }
    
    @PutMapping(value = {"/{id}", "/update/{id}"})
    @Transactional
    public Boolean update(@PathVariable("id") Integer id, @RequestBody Map<String, String> map) {
        Watch o_Watch = watchRepository.findById(id).get();
        if (o_Watch == null) {
            return false;
        }
        o_Watch.setName(map.get("name"));
        watchRepository.saveAndFlush(o_Watch);
        return true;
    }
    
    @GetMapping(value = {"/{id}/add/{tstock_id}"})
    @Transactional
    public Watch add_tstock(@PathVariable("id") Integer id, @PathVariable("tstock_id") Integer tstock_id) {
        Watch watch = watchRepository.findById(id).get();
        TStock ts = tStockRepository.findById(tstock_id).get();
        watch.addtStock(ts);
        watchRepository.saveAndFlush(watch);
        return get(id);
    }
    
    @DeleteMapping(value = {"/{id}/remove/{tstock_id}"})
    @Transactional
    public Watch remove_tstock(@PathVariable("id") Integer id, @PathVariable("tstock_id") Integer tstock_id) {
        Watch watch = watchRepository.findById(id).get();
        TStock ts = tStockRepository.findById(tstock_id).get();
        watch.removetStock(ts);
        watchRepository.saveAndFlush(watch);
        return get(id);
    }
    
}