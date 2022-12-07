package br.com.si.CrudApp.service;

import br.com.si.CrudApp.model.EmployerModelV1;
import br.com.si.CrudApp.repository.EmployerRepositoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployerServiceV1 {

    @Autowired
    private EmployerRepositoryV1 repository;

    public Optional<EmployerModelV1> findById(long id){
        return repository.findById(id);
    }

    public List<EmployerModelV1> findAll(){
        return repository.findAll();
    }

    public EmployerModelV1 save(EmployerModelV1 model){
        return repository.save(model);
    }

    public EmployerModelV1 update(EmployerModelV1 model){
        Optional<EmployerModelV1> p = repository.findById(model.getId());
        if(p.isPresent()){
            p.get().setName(model.getName());
            p.get().setAge(model.getAge());
            p.get().setCargo(model.getCargo());
            return repository.save(p.get());
        } else {
            return null;
        }
    }

    public void delete(long id){
        Optional<EmployerModelV1> p = repository.findById(id);
        if(p.isPresent()){
            repository.delete(p.get());
        }
    }

    public List<EmployerModelV1> findByName(String name){
        return repository.findByNameContainsIgnoreCaseOrderByName(name);
    }

}
