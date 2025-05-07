package desarrollo.integrado.jpql.Repositories;

import desarrollo.integrado.jpql.DTOs.PendingTaskDTO;
import desarrollo.integrado.jpql.Models.Employee;
import desarrollo.integrado.jpql.Models.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // 1. Buscar tareas por estado
    @Query("SELECT t FROM Task t WHERE t.estado = :estado")
    List<Task> findByEstado(@Param("estado") String estado);

    // 2. Buscar tareas por ID de empleado
    @Query("SELECT t FROM Task t WHERE t.employee.id = :employeeId")
    List<Task> findByEmployeeId(@Param("employeeId") Long employeeId);

    // 3. Buscar tareas por cargo del empleado
    @Query("SELECT t FROM Task t JOIN t.employee e WHERE e.cargo = :cargo")
    List<Task> findTasksByEmployeeCargo(@Param("cargo") String cargo);

    // 4. Contar tareas por empleado
    @Query("SELECT e.nombres, COUNT(t) FROM Task t JOIN t.employee e GROUP BY e.id")
    List<Object[]> countTasksPerEmployee();

    // 5. Buscar tareas por sueldo mínimo del empleado
    @Query("SELECT t FROM Task t JOIN t.employee e WHERE e.sueldo > :sueldoMinimo")
    List<Task> findTasksByEmployeeSueldo(@Param("sueldoMinimo") Double sueldoMinimo);

    // 6. Buscar tareas por estado usando JPQL
    @Query("SELECT t FROM Task t JOIN t.employee e WHERE t.estado = :estado")
    List<Task> findTasksByEstadoJPQL(@Param("estado") String estado);

    // 7. Tareas vencidas (fecha vencimiento menor a hoy y no completadas)
    @Query("SELECT t FROM Task t WHERE t.fechaVencimiento < CURRENT_DATE AND t.estado <> 'Completado'")
    List<Task> findTasksVencidas();

    // 8. Tareas de alta prioridad por cargo
    @Query("SELECT t FROM Task t JOIN t.employee e WHERE e.cargo = :cargo AND t.prioridad = 'Alta'")
    List<Task> findHighPriorityTasksByCargo(@Param("cargo") String cargo);

    // 9. Tareas entre rango de fechas
    @Query("SELECT t FROM Task t WHERE t.fechaAsignacion BETWEEN :startDate AND :endDate")
    List<Task> findTasksBetweenDates(@Param("startDate") LocalDate startDate,
                                     @Param("endDate") LocalDate endDate);

    // 10. Empleados con más de X tareas asignadas
    @Query("SELECT e FROM Employee e JOIN e.tasks t GROUP BY e.id HAVING COUNT(t) > :minTasks")
    List<Employee> findEmployeesWithMoreThanXTasks(@Param("minTasks") Long minTasks);

    // 11. Tareas pendientes con nombres de empleados
    @Query("SELECT new desarrollo.integrado.jpql.DTOs.PendingTaskDTO(t.id, t.titulo, t.estado, e.nombres) " +
            "FROM Task t JOIN t.employee e WHERE t.estado = 'Pendiente'")
    List<PendingTaskDTO> findPendingTasksWithEmployeeNames();

}
