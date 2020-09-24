package entity;

import java.util.Objects;

public class Discipline {
    private int id;
    private String discipline;
    private int status = 1;
    private int mark;

    public Discipline() {
    }

    public Discipline(int id, String discipline, int status, int mark) {
        this.id = id;
        this.discipline = discipline;
        this.status = status;
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discipline that = (Discipline) o;
        return id == that.id &&
                status == that.status &&
                mark == that.mark &&
                Objects.equals(discipline, that.discipline);
    }

    @Override
    public String toString() {
        return "Discipline{" +
                "id=" + id +
                ", discipline='" + discipline + '\'' +
                ", status=" + status +
                ", mark=" + mark +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, discipline, status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
