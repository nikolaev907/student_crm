package entity;

import java.util.LinkedList;
import java.util.Objects;

public class Term {
    private LinkedList<Discipline> disciplines = new LinkedList<Discipline>();
    private int id;
    private String name;
    private String duration;

    public LinkedList<Discipline> getDisciplines() {
        return disciplines;
    }

    public void addDiscipline(Discipline discipline) {
        disciplines.add(discipline);
    }

    public void setDisciplines(LinkedList<Discipline> disciplines) {
        this.disciplines = disciplines;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Term term = (Term) o;
        return id == term.id &&
                disciplines.equals(term.disciplines) &&
                name.equals(term.name) &&
                duration.equals(term.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(disciplines, id, name, duration);
    }

    @Override
    public String toString() {
        return "Term{" +
                "disciplines=" + disciplines +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
