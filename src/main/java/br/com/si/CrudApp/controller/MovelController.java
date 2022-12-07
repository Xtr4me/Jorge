package br.com.si.CrudApp.controller;

import br.com.si.CrudApp.model.MovelModel;
import br.com.si.CrudApp.service.MovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movel")
public class MovelController {

    @Autowired
    private MovelService service;

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml" })
    public MovelModel findById(@PathVariable("id") long id){
        return service.findById(id).get();
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml" })
    public List<MovelModel> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/find/{name}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml" })
    public List<MovelModel> findByName(@PathVariable("name") String name){
        return service.findByName(name);
    }

    @PostMapping
    public MovelModel save(@RequestBody MovelModel model){
        return service.save(model);
    }

    @PutMapping
    public MovelModel update(@RequestBody MovelModel model){
        return service.update(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
