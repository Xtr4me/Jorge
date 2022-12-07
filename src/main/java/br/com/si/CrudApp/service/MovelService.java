package br.com.si.CrudApp.service;

import br.com.si.CrudApp.model.MovelModel;
import br.com.si.CrudApp.repository.MovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovelService {

    @Autowired
    private MovelRepository repository;

    public Optional<MovelModel> findById(long id){
        return repository.findById(id);
    }

    public List<MovelModel> findAll(){
        return repository.findAll();
    }

    public List<MovelModel> findByName(String name){
        return repository.findByNameContainsIgnoreCase(name);
    }

    public MovelModel save(MovelModel model){
        return repository.save(model);
    }

    public MovelModel update(MovelModel model){
        var found = repository.findById(model.getId());
        if(found.isPresent()){
            found.get().setName(model.getName());
            return repository.save(found.get());
        } else {
            return null;
        }
    }

    public void delete(long id){
        var found = repository.findById(id);
        if(found.isPresent()){
            repository.delete(found.get());
        }
    }


}
