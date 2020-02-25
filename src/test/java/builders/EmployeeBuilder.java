package builders;

import entities.Employee;

import java.util.Date;

public class EmployeeBuilder {
    private Employee employee;

    public EmployeeBuilder(){
        employee = new Employee();
    }

    public EmployeeBuilder(Employee employee) {
        this.employee = employee;
    }

    public EmployeeBuilder withId(int id){
        employee.setEmp_no(id);
        return this;
    }

    public EmployeeBuilder withBirthDate(Date birthDate){
        employee.setBirth_date(birthDate);
        return this;
    }

    public EmployeeBuilder withHireDate(Date hireDate){
        employee.setHire_date(hireDate);
        return this;
    }

    public EmployeeBuilder withFirstName(String firstName){
        employee.setFirst_name(firstName);
        return this;
    }

    public EmployeeBuilder withLastName(String lastName){
        employee.setLast_name(lastName);
        return this;
    }

    public EmployeeBuilder withGender(String gender){
        employee.setGender(gender);
        return this;
    }

    public Employee create(){
        return this.employee;
    }
}
