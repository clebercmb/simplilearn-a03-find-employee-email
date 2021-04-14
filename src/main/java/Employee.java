public class Employee {
    private final String id;

    private String email;

    public Employee(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }

}
