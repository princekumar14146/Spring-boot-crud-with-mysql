package com.employeeDetails.aws2.Service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.employeeDetails.aws2.Entity.Employee;
import com.employeeDetails.aws2.Exceptions.HandleAllException;
import com.employeeDetails.aws2.Exceptions.NoEmployeeWithSuchNameException;
import com.employeeDetails.aws2.Exceptions.NoSuchEmailFoundException;
import com.employeeDetails.aws2.Repository.EmployeeRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @SneakyThrows
    @Override
    public ResponseEntity<String> saveData(Employee employee){
        log.info("Execution of saveEmployee() method of Service class starts");
        List<Employee> list=employeeRepository.findAll();
        Boolean value=list.stream().anyMatch(user->user.getEmployeeEmail().equals(employee.getEmployeeEmail()));
          if(value) {
              log.error("Exception Occur", HandleAllException.class);
              throw new HandleAllException("Email " + employee.getEmployeeEmail() + " Already Exists");
          }
          else
          {
              log.info("All data of employee follows proper validation");
              employeeRepository.save(employee);
              return ResponseEntity.ok("Data Saved Successfully");
          }


    }

    @Override
    public ResponseEntity<List<Employee>> getAllEmployee() {
        log.info("Execution of getAllEmployee() method of service class starts");
       return ResponseEntity.status(HttpStatus.OK).body(employeeRepository.findAll());
    }

    @SneakyThrows
    @Override
    public ResponseEntity<Employee> getEmployeeById(Integer emp_id) {
        log.info("Execution of getEMployeeById() of service class starts");
        List<Employee> list=employeeRepository.findAll();
        Boolean value=list.stream().anyMatch(user->user.getEmployeeId()==emp_id);
        if(value.equals(false))
        {
            log.error("Exception occurs", HandleAllException.class);
           throw new HandleAllException("Employee with "+emp_id+" not available");
        }
        else{

            return ResponseEntity.status(HttpStatus.OK).body(employeeRepository.findById(emp_id).get());
        }
    }

    @SneakyThrows
    public ResponseEntity<List<Employee>> getEmployeeByName(String name)
    {
        List<Employee> list= employeeRepository.findByEmployeeFirstName(name);

        if(list.isEmpty())
        {
            throw new NoEmployeeWithSuchNameException("No Employee Found With "+name+" in data please provide correct name");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
    }

    @SneakyThrows
    public ResponseEntity<Employee> getEmployeeByEmail(String emp_email)
    {
       Employee emp=employeeRepository.getByEmail(emp_email);
       if(emp.getEmployeeEmail().equals(emp_email))
       {
           return ResponseEntity.status(HttpStatus.OK).body(emp);
       }
       else {
           throw new NoSuchEmailFoundException("Invalid Email provide valid Email");
       }
    }

    @SneakyThrows
    public ResponseEntity<String> deleteDetailsByEmployeeId(Integer employeeId)
    {
         Optional<Employee> op =employeeRepository.findById(employeeId);
         if(op.isEmpty())
         {
             throw new HandleAllException("EmployeeId "+employeeId+" details is not in database");
         }
         else
         {
             employeeRepository.deleteById(employeeId);
             return ResponseEntity.status(HttpStatus.OK).body("Details of employeeId "+employeeId+" is deleted sucessfully");
         }
    }

    @SneakyThrows
    @Override
    public ResponseEntity<String> updateEmployeeById(Integer emp_id, Employee employee) {
        log.info("Execution of updateEmployeeById() of service class starts");
        List<Employee> list=employeeRepository.findAll();
        Boolean value= list.stream().anyMatch(user->user.getEmployeeId()==emp_id);
        if(value.equals(false))
        {
            log.error("Exception occurs", HandleAllException.class);
            throw new HandleAllException(  "EmployeeId="+emp_id+" is invalid please provide valid employee id");
        }
        else {
            employee.setEmployeeId(emp_id);
            employeeRepository.save(employee);
           return ResponseEntity.status(HttpStatus.OK).body("Details updated Successfully");
        }

    }
}
