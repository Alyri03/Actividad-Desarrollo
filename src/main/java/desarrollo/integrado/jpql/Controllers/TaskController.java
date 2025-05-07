package desarrollo.integrado.jpql.Controllers;


import desarrollo.integrado.jpql.Models.Task;
import desarrollo.integrado.jpql.Services.TaskService;
import desarrollo.integrado.jpql.Services.EmployeeService;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "task_list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("employees", employeeService.findAll());
        return "task_form";
    }

    @PostMapping
    public String saveTask(@ModelAttribute Task task) {
        taskService.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/queries")
    public String showQueryForm(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "query_form";
    }
    @PostMapping("/by-employee")
    public String tasksByEmployee(@RequestParam Long employeeId, Model model) {
        model.addAttribute("tasks", taskService.findByEmployeeId(employeeId));
        return "task_list";
    }

    @PostMapping("/by-status")
    public String tasksByStatus(@RequestParam String status, Model model) {
        model.addAttribute("tasks", taskService.findByEstado(status));
        return "task_list";
    }

    @PostMapping("/by-position")
    public String tasksByPosition(@RequestParam String cargo, Model model) {
        model.addAttribute("tasks", taskService.findTasksByEmployeeCargo(cargo));
        return "task_list";
    }

    @GetMapping("/count-tasks")
    public String countTasksPerEmployee(Model model) {
        model.addAttribute("taskCounts", taskService.countTasksPerEmployee());
        return "task_count_list";
    }

    @PostMapping("/by-salary")
    public String tasksByMinSalary(@RequestParam Double minSalary, Model model) {
        model.addAttribute("tasks", taskService.findTasksByEmployeeSueldo(minSalary));
        return "task_list";
    }

    @GetMapping("/overdue-tasks")
    public String overdueTasks(Model model) {
        model.addAttribute("tasks", taskService.findTasksVencidas());
        return "task_list";
    }

    @PostMapping("/high-priority")
    public String highPriorityTasks(@RequestParam String cargo, Model model) {
        model.addAttribute("tasks", taskService.findHighPriorityTasksByCargo(cargo));
        return "task_list";
    }

    @PostMapping("/by-date-range")
    public String tasksBetweenDates(@RequestParam @DateTimeFormat(iso =
                                            DateTimeFormat.ISO.DATE) LocalDate startDate,
                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate
                                            endDate,
                                    Model model) {
        model.addAttribute("tasks", taskService.findTasksBetweenDates(startDate, endDate));
        return "task_list";
    }

    @PostMapping("/busy-employees")
    public String employeesWithManyTasks(@RequestParam Long minTasks, Model model) {
        model.addAttribute("employees", taskService.findEmployeesWithMoreThanXTasks(minTasks));
        return "employee_list";
    }

    @GetMapping("/pending-tasks")
    public String pendingTasksWithEmployees(Model model) {
        model.addAttribute("taskDTOs", taskService.findPendingTasksWithEmployeeNames());
        return "pending_tasks";
    }
}
