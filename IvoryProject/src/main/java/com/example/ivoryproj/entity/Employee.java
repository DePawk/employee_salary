package com.example.ivoryproj.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    private Integer Code;
    private String Name;
    private Integer Activity;
    private String ADDRESS_STREET;
    private String ADDRESS_NUMBER;
    private String ADDRESS_CITY;
    private String ADDRESS_COUNTRY;


}
