package auth.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String inn;
    private Timestamp date;
    private final List<Person> accounts = new ArrayList<>();

    public static Employee of(int id, String firstName, String lastName, String inn) {
        Employee e = new Employee();
        e.id = id;
        e.firstName = firstName;
        e.lastName = lastName;
        e.inn = inn;
        e.date = new Timestamp(System.currentTimeMillis());
        return e;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public List<Person> getAccounts() {
        return accounts;
    }

    public void addAccount(Person person) {
        accounts.add(person);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id
                && Objects.equals(firstName, employee.firstName)
                && Objects.equals(lastName, employee.lastName)
                && Objects.equals(inn, employee.inn)
                && Objects.equals(date, employee.date)
                && Objects.equals(accounts, employee.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, inn, date, accounts);
    }
}
