package br.com.si.CrudApp.controller;

import br.com.si.CrudApp.model.EmployerModelV1;
import br.com.si.CrudApp.service.EmployerServiceV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employer/v1")

public class EmployerControllerV1 {

    @Autowired
    private EmployerServiceV1 service;

    @GetMapping("/{id}")
    public Optional<EmployerModelV1> findById(@PathVariable("id") long id){
        return service.findById(id);
    }

    @PostMapping
    public EmployerModelV1 save(@RequestBody EmployerModelV1 model){
        return service.save(model);
    }

    @GetMapping
    public List<EmployerModelV1> findAll(){
        return service.findAll();
    }

    @PutMapping
    public EmployerModelV1 update(@RequestBody EmployerModelV1 model){
        return service.update(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find/name/{name}")
    public List<EmployerModelV1> findByName(@PathVariable("name") String name){
        return service.findByName(name);
    }

}
