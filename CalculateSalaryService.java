import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class CalculateSalaryService {
    public void findAverageSalary(List<Employee> employees) {
        // Group employees by office location
        ConcurrentMap<String, Map<String, Double>> officeLocationToAvgSalary = employees.parallelStream()
                .collect(Collectors.groupingByConcurrent(Employee::getOfficeLocation,
                        Collectors.groupingBy(Employee::getDesignation,
                                Collectors.averagingDouble(Employee::getSalary))));

        // Print the results
        officeLocationToAvgSalary.forEach((officeLocation, designationAvgSalary) -> {
            designationAvgSalary.forEach((designation, avgSalary) -> {
                System.out.println(officeLocation + " --> " + designation + " --> " + String.format("%.2f", avgSalary));
            });
        });
    }
}
