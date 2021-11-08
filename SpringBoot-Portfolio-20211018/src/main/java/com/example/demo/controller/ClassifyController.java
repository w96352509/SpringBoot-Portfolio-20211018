package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Classify;
import com.example.demo.repository.ClassifyRepository;

@RestController
@RequestMapping("/classify")
public class ClassifyController {
	
	@Autowired
	private ClassifyRepository classifyRepository;
	
	@RequestMapping(value = {"/", "/query"})
	public List<Classify> query() {
		return classifyRepository.findAll();
	}
	
	@RequestMapping(value = {"/{id}", "/get/{id}"})
	public Classify get(@PathVariable("id") Integer id) {
		Classify classify = classifyRepository.findById(id).get();
		return classify;
	}
	
	@PostMapping(value = {"/", "/add"})
	@Transactional
	public Classify add(@RequestBody Map<String, String> map) {
		Classify classify = new Classify();
		classify.setName(map.get("name"));
		if(map.get("tx") == null) {
			classify.setTx(false);
		} else {
			classify.setTx(true);
		}
		classifyRepository.save(classify);
		return classify;
	}
	
	@PutMapping(value = {"/{id}", "/update/{id}"})
	@Transactional
	public Boolean update(@PathVariable("id") Integer id, @RequestBody Map<String, String> map) {
		Classify classify = get(id);
		classify.setName(map.get("name"));
		if(map.get("tx") == null) {
			classify.setTx(false);
		} else {
			classify.setTx(true);
		}
		classifyRepository.saveAndFlush(classify);
		return true;
	}
	
	@DeleteMapping(value = {"/{id}", "/delete/{id}"})
	@Transactional
	public Boolean delete(@PathVariable("id") Integer id) {
		classifyRepository.deleteById(id);
		return true;
	}
	
	
	
}