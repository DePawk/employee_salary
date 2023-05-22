import com.example.ivoryproj.entity.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeFileReader {
    public static void main(String[] args) throws IOException {
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
        myReader.close();
    }
}
