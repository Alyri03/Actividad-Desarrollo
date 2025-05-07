package desarrollo.integrado.jpql.Services;
import desarrollo.integrado.jpql.DTOs.PendingTaskDTO;
import desarrollo.integrado.jpql.Models.Employee;
import desarrollo.integrado.jpql.Models.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {
    List<Task> findAll();
    Task save(Task task);
    void deleteById(Long id);
    List<Task> findByEstado(String estado);
    List<Task> findByEmployeeId(Long employeeId);
    List<Task> findTasksByEmployeeCargo(String cargo);
    List<Object[]> countTasksPerEmployee();
    List<Task> findTasksByEmployeeSueldo(Double sueldoMinimo);
    List<Task> findTasksByEmployeeId(Long id);
    List<Task> findTasksByEstadoJPQL(String estado);
    List<Task> findTasksVencidas();
    List<Task> findHighPriorityTasksByCargo(String cargo);
    List<Task> findTasksBetweenDates(LocalDate startDate, LocalDate endDate);
    List<Employee> findEmployeesWithMoreThanXTasks(Long minTasks);
    List<PendingTaskDTO> findPendingTasksWithEmployeeNames();

}
