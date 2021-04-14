import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class EmployeeRepositoryTest extends Assertions {
    private static EmployeesRepository employeesRepository=null;

    @BeforeAll
    public static void before() {
        employeesRepository = EmployeesRepository.getEmployeeRepository();
    }

    @Test
    void getRepository() {
        EmployeesRepository employeesRepository1 = EmployeesRepository.getEmployeeRepository();
        EmployeesRepository employeesRepository2 = EmployeesRepository.getEmployeeRepository();

        assertEquals(employeesRepository1, employeesRepository1);

    }

    @Test
    void addEmploy() throws RepositoryException {
        Employee employee1 = employeesRepository.add(new Employee(null, "kingkong@gmail.com"));

        Employee employee2 = employeesRepository.findById(employee1.getId());

        assertEquals(employee1, employee2);

    }

    @Test
    void testAvoidAddDuplicatedEmail() {

        Exception exception = assertThrows(RepositoryException.class, () -> {

            Employee employee1 = employeesRepository.add(new Employee(null, "kingkong@gmail.com"));
            Employee employee2 = employeesRepository.add(new Employee(null, "kingkong@gmail.com"));

        });


    }

    @Test
    void getArrayOfEmployeesEmails() throws RepositoryException {

        int initialSize = employeesRepository.getEmployeesEmailList().length;
        Employee e1 = employeesRepository.add(new Employee(null, "michael@gmail.com"));
        Employee e2 = employeesRepository.add(new Employee(null, "joe@gmail.com"));
        Employee e3 = employeesRepository.add(new Employee(null, "daniel@gmail.com"));
        Employee e4 = employeesRepository.add(new Employee(null, "robert@gmail.com"));
        Employee e5 = employeesRepository.add(new Employee(null, "madona@gmail.com"));
        Employee e6 = employeesRepository.add(new Employee(null, "obama@gmail.com"));

        String[] employeeList =  employeesRepository.getEmployeesEmailList();

        assertEquals(6+initialSize, employeeList.length);

        long count1 = Arrays.stream(employeeList).filter(email->email.equals("michael@gmail.com")).count();
        long count2 = Arrays.stream(employeeList).filter(email->email.equals("joe@gmail.com")).count();
        long count3 = Arrays.stream(employeeList).filter(email->email.equals("daniel@gmail.com")).count();
        long count4 = Arrays.stream(employeeList).filter(email->email.equals("robert@gmail.com")).count();
        long count5 = Arrays.stream(employeeList).filter(email->email.equals("madona@gmail.com")).count();
        long count6 = Arrays.stream(employeeList).filter(email->email.equals("obama@gmail.com")).count();

        assertEquals(1, count1);
        assertEquals(1, count2);
        assertEquals(1, count3);
        assertEquals(1, count4);
        assertEquals(1, count5);
        assertEquals(1, count6);


    }

    @Test
    void findByEmail() throws RepositoryException {
        Employee e1 = employeesRepository.add(new Employee(null, "hulk@gmail.com"));
        Employee e2 = employeesRepository.add(new Employee(null, "thor@gmail.com"));
        Employee e3 = employeesRepository.add(new Employee(null, "strange@gmail.com"));

        Employee answer = employeesRepository.findByEmail("thor@gmail.com");

        assertEquals(e2, answer);

    }


}
