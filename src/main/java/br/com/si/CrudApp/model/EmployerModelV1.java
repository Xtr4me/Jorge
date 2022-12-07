package br.com.si.CrudApp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "employer")
@AllArgsConstructor
@NoArgsConstructor
public class EmployerModelV1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter @Getter
    private long id;

    @Column(name = "name", nullable = false, length = 50)
    @Setter @Getter
    private String name;

    @Column(name = "age", nullable = false, length = 50)
    @Setter @Getter
    private int age;

    @Column(name = "cargo", nullable = false, length = 50)
    @Setter @Getter
    private String cargo;



}
