package br.com.si.CrudApp.repository;

import br.com.si.CrudApp.model.EmployerModelV1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployerRepositoryV1 extends JpaRepository<EmployerModelV1, Long> {

    public Optional<EmployerModelV1> findById(long id);

    List<EmployerModelV1> findByNameContainsIgnoreCaseOrderByName(String name);


}
