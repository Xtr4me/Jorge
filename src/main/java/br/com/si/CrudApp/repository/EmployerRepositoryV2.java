package br.com.si.CrudApp.repository;


import br.com.si.CrudApp.model.EmployerModelV2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployerRepositoryV2 extends JpaRepository<EmployerModelV2, Long> {

    public Optional<EmployerModelV2> findById(long id);

    List<EmployerModelV2> findByNameContainsIgnoreCaseOrderByName(String name);



    Page<EmployerModelV2> findAll(Pageable pageable);

    List<EmployerModelV2> findAll();


}
