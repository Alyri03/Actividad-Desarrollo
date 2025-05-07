package desarrollo.integrado.jpql.Services;

import desarrollo.integrado.jpql.DTOs.PendingTaskDTO;
import desarrollo.integrado.jpql.Models.Employee;
import desarrollo.integrado.jpql.Models.Task;
import desarrollo.integrado.jpql.Repositories.TaskRepository;
import desarrollo.integrado.jpql.Repositories.EmployeeRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    @Transactional
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> findByEstado(String estado) {
        return taskRepository.findByEstado(estado);
    }

    @Override
    public List<Task> findByEmployeeId(Long id) {
        return taskRepository.findByEmployeeId(id);
    }

    @Override
    public List<Task> findTasksByEmployeeCargo(String cargo) {
        return taskRepository.findTasksByEmployeeCargo(cargo);
    }

    @Override
    public List<Object[]> countTasksPerEmployee() {
        return taskRepository.countTasksPerEmployee();
    }

    @Override
    public List<Task> findTasksByEmployeeSueldo(Double sueldoMinimo) {
        return taskRepository.findTasksByEmployeeSueldo(sueldoMinimo);
    }

    @Override
    public List<Task> findTasksByEmployeeId(Long id) {
        return taskRepository.findByEmployeeId(id);
    }


    @Override
    public List<Task> findTasksByEstadoJPQL(String estado) {
        return taskRepository.findTasksByEstadoJPQL(estado);
    }

    @Override
    public List<Task> findTasksVencidas() {
        return taskRepository.findTasksVencidas();
    }

    @Override
    public List<Task> findHighPriorityTasksByCargo(String cargo) {
        return taskRepository.findHighPriorityTasksByCargo(cargo);
    }

    @Override
    public List<Task> findTasksBetweenDates(LocalDate startDate, LocalDate endDate) {
        return taskRepository.findTasksBetweenDates(startDate, endDate);
    }

    @Override
    public List<Employee> findEmployeesWithMoreThanXTasks(Long minTasks) {
        return taskRepository.findEmployeesWithMoreThanXTasks(minTasks);
    }

    @Override
    public List<PendingTaskDTO> findPendingTasksWithEmployeeNames() {
        return taskRepository.findPendingTasksWithEmployeeNames();
    }
}
