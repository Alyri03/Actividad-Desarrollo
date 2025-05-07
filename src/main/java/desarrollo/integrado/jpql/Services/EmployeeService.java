package desarrollo.integrado.jpql.Services;

import java.util.List;
import java.util.Optional;

import desarrollo.integrado.jpql.Models.Employee;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    List<Employee> findAll();
    Optional<Employee> findById(Long id);
    Employee save(Employee employee);
    void deleteById(Long id);
}
