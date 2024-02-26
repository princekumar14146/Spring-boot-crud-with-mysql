package com.employeeDetails.aws2.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Integer employeeId;

    @NotBlank(message = "Employee first name not be null")
    @Column(name = "emp_first_name")
    private String employeeFirstName;

    @NotBlank(message = "Employee last name not be blank")
    @Column(name = "emp_last_name")
    private String employeeLastName;

    @NotNull(message = "Employee Salary not be null ")
    @Column(name = "emp_sal")
    private Double employeeSalary;

    @Email(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@gmail\\.com$" ,message = "Invalid email Address please provide correct one")
    @Column(name = "emp_email")
    private String employeeEmail;

    @Size(min = 0 , max = 10, message = "Address size must be between 0 to 10 alphabets not more than that")
    @Column(name = "emp_address")
    private String employeeAddress;



}
