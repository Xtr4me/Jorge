package br.com.si.CrudApp.repository;

import br.com.si.CrudApp.model.MovelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovelRepository extends JpaRepository<MovelModel, Long> {

    //..a optional return
    Optional<MovelModel> findById(long id);

    //..JPA derived query to retrive a list by name
    List<MovelModel> findByNameContainsIgnoreCase(String name);

}
