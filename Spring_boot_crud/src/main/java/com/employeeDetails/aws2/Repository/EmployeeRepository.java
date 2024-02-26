package com.employeeDetails.aws2.Repository;

import com.employeeDetails.aws2.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    public List<Employee> findByEmployeeFirstName(String name);
    @Query("select u from Employee u where u.employeeEmail=:n")          // JPQL QUERY
//    @Query(value = "select * from employee where emp_email=:n ",nativeQuery = true)   // SQL QUERY
    public Employee getByEmail(@Param("n") String emp_email);
}
