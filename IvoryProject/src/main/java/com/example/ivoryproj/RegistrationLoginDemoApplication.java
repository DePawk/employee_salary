package com.example.ivoryproj;

import com.example.ivoryproj.entity.Employee;
import com.example.ivoryproj.entity.Salary;
import com.example.ivoryproj.repository.EmployeeRepository;
import com.example.ivoryproj.repository.SalaryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RegistrationLoginDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrationLoginDemoApplication.class, args);

	}
		@Bean
		CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository)
		{
			return args ->{
				File myObj = new File("src\\main\\resources\\Files\\Employees.txt");
				BufferedReader myReader = new BufferedReader(new FileReader(myObj));
				String line = myReader.readLine();
				line = myReader.readLine();
				List<Employee> eList = new ArrayList<>();
				while (line != null)
				{
					String[] words = line.split(",");
					eList.add(new Employee(Integer.parseInt(words[0]),words[1],Integer.parseInt(words[2]),words[3],words[4],words[5],words[6]));
					line = myReader.readLine();
				}
				employeeRepository.saveAll(eList);
				myReader.close();
			};
		}
	@Bean
	CommandLineRunner commandLineRunner2(SalaryRepository salaryRepository)
	{
		return args -> {
			File myObj = new File("src\\main\\resources\\Files\\Salaries.txt");
			BufferedReader myReader = new BufferedReader(new FileReader(myObj));
			String line = myReader.readLine();
			line = myReader.readLine();
			List<Salary> sList = new ArrayList<>();
			while (line != null)
			{
				String[] words = line.split(",");
				sList.add(new Salary(Integer.parseInt(words[0]),words[1],Float.parseFloat(words[2]),Float.parseFloat(words[3]),Float.parseFloat(words[4])));
				line = myReader.readLine();
			}
			myReader.close();

			List<Salary> dbSalaries = salaryRepository.findAll();

			if (sList.size() != dbSalaries.size() || !sList.containsAll(dbSalaries)) {
				salaryRepository.deleteAll();
				salaryRepository.saveAll(sList);
				System.out.println("Data updated in database");
			} else {
				System.out.println("Data in file is same as database. No update needed");
			}
		};
	}


}
