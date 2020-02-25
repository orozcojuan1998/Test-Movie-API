package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Employee {

    private int emp_no;
    private Date birth_date;
    private Date hire_date;
    private String first_name;
    private String last_name;
    private String gender;

    public Employee() {
    }

    public Employee(int emp_no, Date birth_date, Date hire_date, String first_name, String last_name, String gender) {
        this.emp_no = emp_no;
        this.birth_date = birth_date;
        this.hire_date = hire_date;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
    }

    public int getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return String.format("Id: %d, Name: %s %s, Gender: %s, Birth: %s, Hire: %s",
                emp_no, first_name, last_name, gender, birth_date, hire_date);
    }
}

