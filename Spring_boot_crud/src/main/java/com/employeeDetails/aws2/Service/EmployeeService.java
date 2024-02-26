package com.employeeDetails.aws2.Service;

import com.employeeDetails.aws2.Entity.Employee;
import com.employeeDetails.aws2.Exceptions.HandleAllException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {

    public ResponseEntity<String> saveData(Employee employee) throws HandleAllException;
    public ResponseEntity<List<Employee>> getAllEmployee();

    public ResponseEntity<Employee> getEmployeeById(Integer emp_id);

    public ResponseEntity<String> deleteDetailsByEmployeeId(Integer employeeId);

    public ResponseEntity<String> updateEmployeeById(Integer emp_id, Employee employee);
}
