package desarrollo.integrado.jpql.Controllers;

import desarrollo.integrado.jpql.Models.Employee;
import desarrollo.integrado.jpql.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "employee_list"; // Vista para listar
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee_form"; // Formulario para crear
    }

    @PostMapping
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Employee employee = employeeService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        return "employee_form"; // Reutilizamos formulario para editar
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
        return "redirect:/employees";
    }
}
