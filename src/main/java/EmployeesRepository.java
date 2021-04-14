import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class EmployeesRepository {
    private static ArrayList<Employee> employeesList = null;

    private EmployeesRepository(){
        if (employeesList == null) {
            employeesList = new ArrayList<Employee>();
        }
    }

    public String[] getEmployeesEmailList() {
        return employeesList.stream().map(Employee::getEmail).toArray(String[]::new);
    }


    public static EmployeesRepository getEmployeeRepository() {
        return new EmployeesRepository();
    }


    public Employee add(Employee e) throws RepositoryException {

        Optional<Employee> answer = employeesList.stream().filter(emp->emp.getEmail().equals(e.getEmail())).findFirst();
        if( answer.isPresent() ) {
            throw new RepositoryException("Email is already registered. Try another email");
        }

        Employee newEmployee = new Employee(UUID.randomUUID().toString(), e.getEmail());
        employeesList.add(newEmployee);

        return newEmployee;
    }


    public Employee findById(String id) {

        Optional<Employee> answer = employeesList.stream().filter(e->e.getId().equals(id)).findFirst();

        return answer.orElse(null);

    }

    public Employee findByEmail(String email) {

        Optional<Employee> answer = employeesList.stream().filter(e->e.getEmail().equals(email)).findFirst();

        return answer.orElse(null);

    }


}
