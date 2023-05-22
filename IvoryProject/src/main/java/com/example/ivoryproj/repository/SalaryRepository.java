package com.example.ivoryproj.repository;
import com.example.ivoryproj.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;


public interface SalaryRepository extends JpaRepository<Salary, Long> {

    @Query("SELECT s FROM Salary s WHERE s.EMPLOYEE_CODE = :employeeCode")
    List<Salary> findByEmployeeCode(@Param("employeeCode") Integer employeeCode);

    @Query("SELECT COALESCE(SUM(s.TOTAL), 0) FROM Salary s WHERE s.EMPLOYEE_CODE = :employeeCode")
    Float getTotalSalaryByEmployeeCode(@Param("employeeCode") Integer employeeCode);

}

