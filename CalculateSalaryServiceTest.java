import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CalculateSalaryServiceTest {

    private CalculateSalaryService salaryService;
    private List<Employee> employees;

    @Before
    public void setUp() {
        salaryService = new CalculateSalaryService();
        employees = new ArrayList<>();

        // Adding sample data for testing
        employees.add(new Employee("Ashish", "A", "IT", "Pune", "Software Engineer", 10000.0));
        employees.add(new Employee("Amit", "R", "HR", "Pune", "Recruiter", 12000.0));
        employees.add(new Employee("Ramesh", "D", "HR", "Pune", "Senior Recruiter", 14000.0));
        employees.add(new Employee("Jaya", "S", "IT", "Pune", "Tech Lead", 15000.0));
        employees.add(new Employee("Smita", "M", "IT", "Bangalore", "Recruiter", 16000.0));
        employees.add(new Employee("Umesh", "A", "IT", "Bangalore", "Software Engineer", 12000.0));
        employees.add(new Employee("Pooja", "R", "HR", "Bangalore", "Software Engineer", 12000.0));
        employees.add(new Employee("Ramesh", "D", "HR", "Pune", "Recruiter", 16000.0));
        employees.add(new Employee("Bobby", "S", "IT", "Bangalore", "Tech Lead", 20000.0));
        employees.add(new Employee("Vipul", "M", "IT", "Bangalore", "Software Engineer", 14000.0));
    }

    @Test
    public void testFindAverageSalary() {
        // Redirect System.out to capture printed output for assertions
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        // Execute the method
        salaryService.findAverageSalary(employees);

        // Check for expected average salary values in output
        String output = outContent.toString().trim();
        assertTrue(output.contains("Pune --> Software Engineer --> 10000.00"));
        assertTrue(output.contains("Pune --> Recruiter --> 14000.00")); // Average of 12000.0 and 16000.0
        assertTrue(output.contains("Bangalore --> Tech Lead --> 20000.00"));
        assertTrue(output.contains("Bangalore --> Software Engineer --> 12666.67")); // Average of 12000.0, 12000.0, and 14000.0

        // Reset System.out
        System.setOut(null);
    }

    @Test
    public void testEmptyEmployeeList() {
        // Redirect System.out to capture printed output for assertions
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        // Execute the method with an empty list
        salaryService.findAverageSalary(new ArrayList<>());

        // Check for no output when the employee list is empty
        String output = outContent.toString().trim();
        assertEquals("", output);

        // Reset System.out
        System.setOut(null);
    }
}
