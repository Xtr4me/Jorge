package br.com.si.CrudApp.controller;

import br.com.si.CrudApp.model.EmployerModelV2;
import br.com.si.CrudApp.service.EmployerServiceV2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employer/v2")
@Api(value = "Event Controller Version 2.0")
public class EmployerControllerV2 {

    @Autowired
    private EmployerServiceV2 service;

//    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE,
//            MediaType.APPLICATION_JSON_VALUE, "application/x-yaml" })
//    @ApiOperation(value = "Get a Person by id", produces = "JSON", response = PersonModelV2.class)
//    public PersonModelV2 findById(
//            @ApiParam(name = "id", required = true, type = "Integer")
//            @PathVariable("id") long id)
//    {
//        return service.findById(id).get();
//    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE, "application/x-yaml" })
    @ApiOperation(value = "Get a Event by id", produces = "JSON", response = EmployerModelV2.class)
    public EmployerModelV2 findById(
            @ApiParam(name = "id", required = true, type = "Integer")
            @PathVariable("id") long id)
    {
        //..retrieve the model
        var model = service.findById(id);
        //..if is not null, build the HATEOAS link
        if(model.isPresent()){
            buildEntityLinks(model.get());
        }
        //.return the model with links
        return model.get();
    }

    @PostMapping(produces = { MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE, "application/x-yaml" },
    consumes = { MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE, "application/x-yaml" })
    public EmployerModelV2 save(@RequestBody EmployerModelV2 model){
        return service.save(model);
    }


//    @GetMapping(produces = { MediaType.APPLICATION_XML_VALUE,
//            MediaType.APPLICATION_JSON_VALUE, "application/x-yaml" },
//            consumes = { MediaType.APPLICATION_XML_VALUE,
//                    MediaType.APPLICATION_JSON_VALUE, "application/x-yaml" })
//    public List<PersonModelV2> findAll(){
//        return service.findAll();
//    }

    @GetMapping
    public ResponseEntity<PagedModel<EmployerModelV2>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            PagedResourcesAssembler<EmployerModelV2> assembler
    ){
        //..the direction of sort
        var sortDirection =
                "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        //..a PageAble object is an object containing the list of resources
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "Name"));

        //..a Page containing the resource models
        Page<EmployerModelV2> pageModel= service.findAll(pageable);

        //..create the HATEOAS links
        for(EmployerModelV2 event : pageModel){
            buildEntityLinks(event);
        }
        //return the ResponseEntity
        return new ResponseEntity(assembler.toModel(pageModel), HttpStatus.OK);

    }


    @PutMapping
    public EmployerModelV2 update(@RequestBody EmployerModelV2 model){
        return service.update(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find/name/{name}")
    public List<EmployerModelV2> findByName(@PathVariable("name") String name){
        return service.findByName(name);
    }

    //HATEOAS implementation
    public void buildEntityLinks(EmployerModelV2 model){
        //..link to self
        model.add(
               WebMvcLinkBuilder.linkTo( //..link to a method...
                       WebMvcLinkBuilder.methodOn(this.getClass())
                               .findById(model.getId())
               ).withSelfRel()
        );
        //..link to collection
//        model.add(
//                WebMvcLinkBuilder.linkTo(
//                        WebMvcLinkBuilder.methodOn(this.getClass())
//                                .findAll()
//                ).withRel(IanaLinkRelations.COLLECTION)
//        );
    }



}
