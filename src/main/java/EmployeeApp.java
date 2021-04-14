import java.util.Arrays;
import java.util.Scanner;

public class EmployeeApp {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        try {
            System.out.print("Enter list of the employees emails separated by space: ");
            String emailsList = input.nextLine();

            System.out.print("Enter the email to be searched: ");
            String search = input.nextLine();

            EmployeesRepository employeesRepository = EmployeesRepository.getEmployeeRepository();

            String[] employeeList = emailsList.split(" ");
            for (String email : employeeList) {
                employeesRepository.add(new Employee(null, email));
            }

            Employee employeeFound = employeesRepository.findByEmail(search);

            if( employeeFound != null ) {
                System.out.printf("\n\nEmail %s found!", employeeFound.getEmail() );
            } else {
                System.out.printf("\n\nEmail %s not found!", search );
            }


        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }
        finally {
            input.close();
            input = null;
        }

    }
}
