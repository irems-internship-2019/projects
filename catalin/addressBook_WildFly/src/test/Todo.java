package test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactid;
    private String firstname;
    private String lastname;

    public String getSummary() {
        return firstname;
    }

    public void setSummary(String summary) {
        this.firstname = summary;
    }

    public String getDescription() {
        return lastname;
    }

    public void setDescription(String description) {
        this.lastname = description;
    }

    @Override
    public String toString() {
        return "Todo [summary=" + firstname + ", description=" + lastname
                + "]";
    }

}