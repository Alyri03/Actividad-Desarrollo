package desarrollo.integrado.jpql.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name ="employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String cargo;

    @Column(nullable = false)
    private Double sueldo;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Task> tasks;
}
