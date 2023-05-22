package com.example.ivoryproj.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name= "salaries")
public class Salary {

    private Integer EMPLOYEE_CODE;
    private String Month;
    private Float GROSS;
    private Float Tax;
    private Float TOTAL;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public Salary(Integer EMPLOYEE_CODE, String month, Float GROSS, Float tax, Float TOTAL) {
        this.EMPLOYEE_CODE = EMPLOYEE_CODE;
        Month = month;
        this.GROSS = GROSS;
        Tax = tax;
        this.TOTAL = TOTAL;
    }
}
