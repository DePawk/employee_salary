package com.example.ivoryproj.repository;
import com.example.ivoryproj.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository  extends JpaRepository<Employee,Long> {
@Query ("SELECT e FROM Employee e ORDER BY e.Code ASC")
    List<Employee> findAllByCodeAsc();

@Query("SELECT e FROM Employee e WHERE e.Activity = :activity")
   List<Employee> findAllByActivity(@Param("activity") int activity);

@Query ("SELECT e FROM Employee e ORDER BY e.Name ASC")
    List<Employee> findAllByNameAsc();
    @Query("SELECT e FROM Employee e WHERE e.Code BETWEEN :codeFrom AND :codeTo")
    List<Employee> findEmployeesByCodeRange(@Param("codeFrom") Integer codeFrom, @Param("codeTo") Integer codeTo);

}
