package br.com.si.CrudApp.service;

import br.com.si.CrudApp.model.EmployerModelV2;
import br.com.si.CrudApp.repository.EmployerRepositoryV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployerServiceV2 {

    @Autowired
    private EmployerRepositoryV2 repository;

    public Optional<EmployerModelV2> findById(long id){
        return repository.findById(id);
    }

    public Page<EmployerModelV2> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public EmployerModelV2 save(EmployerModelV2 model){
        return repository.save(model);
    }

    public EmployerModelV2 update(EmployerModelV2 model){
        Optional<EmployerModelV2> p = repository.findById(model.getId());
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
        Optional<EmployerModelV2> p = repository.findById(id);
        if(p.isPresent()){
            repository.delete(p.get());
        }
    }

    public List<EmployerModelV2> findByName(String name){
        return repository.findByNameContainsIgnoreCaseOrderByName(name);
    }


}
