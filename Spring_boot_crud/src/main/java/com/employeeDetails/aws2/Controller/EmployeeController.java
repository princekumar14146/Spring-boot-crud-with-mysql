package com.employeeDetails.aws2.Controller;

import com.employeeDetails.aws2.Entity.Employee;
import com.employeeDetails.aws2.Service.EmployeeServiceImpl;
import org.hibernate.internal.build.AllowSysOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeeData")
public class EmployeeController {

    Logger  logger=LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeServiceImpl employeeService;

    @PostMapping("/save")
    public ResponseEntity<String> saveData(@RequestBody Employee employee)
    {
        logger.info("Starting execution of Controller saveData() to save data into database");
        return employeeService.saveData(employee);

    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Employee>> getAllEmployee()
    {
        logger.info("Execution of GetAllEmployee() of Controller starts");
        return employeeService.getAllEmployee();
    }

    @GetMapping("/get/{emp_id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("emp_id") Integer emp_id)
    {
        logger.info("Execution of getEmployeeById() of controller class starts" );
        return employeeService.getEmployeeById(emp_id);
    }

    @GetMapping("/get1/{name}")
    public ResponseEntity<List<Employee>> getEmployeeByName(@PathVariable("name") String name)
    {
        return employeeService.getEmployeeByName(name);
    }

    @GetMapping("/get2")
    public ResponseEntity<Employee> getEMployeeByEmail(@RequestParam("emp_email") String emp_email)
    {
        return  employeeService.getEmployeeByEmail(emp_email);
    }

    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<String> deleteDetailsByEmployeeId(@PathVariable("employeeId") Integer employeeId)
    {
        return employeeService.deleteDetailsByEmployeeId(employeeId);
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateEmployeeById(@RequestParam Integer emp_id,@RequestBody Employee employee)
    {
        logger.info("execution of updateById() method of controller starts");
        return employeeService.updateEmployeeById(emp_id,employee);
    }
}
