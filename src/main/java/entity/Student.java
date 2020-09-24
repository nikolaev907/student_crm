package entity;

import java.util.Date;
import java.util.Objects;

public class Student {
    private int id;
    private String name;
    private String surname;
    private String group;
    private Date date_receipt;
    private int status = 1;

    public Student() {
    }

    public Student(int id, String name, String surname, String group, Date date_receipt, int status) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.group = group;
        this.date_receipt = date_receipt;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Date getDate_receipt() {
        return date_receipt;
    }

    public void setDate_receipt(Date date_receipt) {
        this.date_receipt = date_receipt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                status == student.status &&
                Objects.equals(name, student.name) &&
                Objects.equals(surname, student.surname) &&
                Objects.equals(group, student.group) &&
                Objects.equals(date_receipt, student.date_receipt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, group, date_receipt, status);
    }
}
