package com.example.ivoryproj.controller;
import com.example.ivoryproj.entity.Salary;
import com.example.ivoryproj.repository.EmployeeRepository;
import com.example.ivoryproj.dto.UserDto;
import com.example.ivoryproj.entity.Employee;
import com.example.ivoryproj.entity.User;
import com.example.ivoryproj.repository.SalaryRepository;
import com.example.ivoryproj.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
public class AuthController {

    private UserService userService;
    private EmployeeRepository employeeRepository;
    private SalaryRepository salaryRepository;
    Logger logger =LoggerFactory.getLogger(AuthController.class);
    public AuthController(UserService userService, EmployeeRepository employeeRepository, SalaryRepository salaryRepository) {
        this.userService = userService;
        this.employeeRepository = employeeRepository;
        this.salaryRepository = salaryRepository;
    }

    @GetMapping("index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }




    @GetMapping("/employees/All")
    public String getEmployees(Model model) throws IOException {
        List<Employee> employees = employeeRepository.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employees);
        logger.trace("a User has invoked the function getEmployees");
        model.addAttribute("json", json);
        logger.info(model.toString());
        return "employeesAll";
    }

    @GetMapping("/employees/ByCode")
    public String getEmployeesByCode(Model model) throws JsonProcessingException {
        List<Employee> employees = employeeRepository.findAllByCodeAsc();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employees);

        logger.trace("a User has invoked the function getEmployeesByCode");
        model.addAttribute("json", json);
        logger.info(model.toString());

        return "employeesByCode";
    }
    @GetMapping("/employees/Activity")
    public String getEmployeesByActivity(Model model) throws JsonProcessingException {
        List<Employee> employees = employeeRepository.findAllByActivity(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employees);

        logger.trace("a User has invoked the function getEmployeesByActivity");
        model.addAttribute("json", json);
        logger.info(model.toString());

        return "employeesActivity";
    }
    @GetMapping("/employees/ByName")
    public String getEmployeesByName(Model model) throws JsonProcessingException {
        List<Employee> employees = employeeRepository.findAllByNameAsc();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employees);

        logger.trace("a User has invoked the function getEmployeesByName");
        model.addAttribute("json", json);
        logger.info(model.toString());

        return "employeesName";
    }
    @GetMapping("/employees")
    public String getEmployeeHome()
    {
        return "employees";
    }
    @GetMapping("/employees/Salary")
    public String getEmployeeBySalary(Model model, @RequestParam("code") Integer code) throws JsonProcessingException {
        List<Salary> salaries = salaryRepository.findByEmployeeCode(code);
        if (salaries.isEmpty()) {
            model.addAttribute("message", "Employee code " + code + " does not exist.");
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(salaries);

            logger.trace("a User has invoked the function getEmployeesBySalary");
            model.addAttribute("json", json);
            logger.info(model.toString());
        }

        return "employeesSalaries";
    }
    @GetMapping("/employees/Total")
    public String getTotalSalary(Model model, @RequestParam("code") Integer code)
    {
        Float salaries = salaryRepository.getTotalSalaryByEmployeeCode(code);
        logger.trace(" a User has invoked the function getTotalSalary");
        model.addAttribute("salaries",salaries);
        logger.trace(model.toString());
        return "employeeTotal";
    }
    @GetMapping("/employees/CodeRange")
    public String getEmployeeCodeRange(Model model, @RequestParam("codeStart") Integer codeStart, @RequestParam("codeEnd") Integer codeEnd) throws JsonProcessingException {
        List<Employee> employees = employeeRepository.findEmployeesByCodeRange(codeStart, codeEnd);
        if (employees.isEmpty()) {
            model.addAttribute("message", "No employees found within the specified range.");
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employees);
            logger.trace("a User has invoked the function getEmployeesByCodeRange");
            model.addAttribute("json", json);
            logger.info(model.toString());
        }
        return "employeeCodeRange";
    }



}
