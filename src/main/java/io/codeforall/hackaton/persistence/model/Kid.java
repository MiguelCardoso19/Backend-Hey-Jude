package io.codeforall.hackaton.persistence.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "kid")
public class Kid implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer age;
    private String email;
    private String password;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @OneToMany(

            cascade = {CascadeType.ALL},


            orphanRemoval = true,


            mappedBy = "kid",


            fetch = FetchType.EAGER
    )
    private List<Parent> parents;

    @OneToMany(
            cascade = {CascadeType.ALL},

            mappedBy = "kid"
    )
    private List<Event> events;

    public void setParents(List<Parent> parents) {
        this.parents = parents;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Parent> getParents() {
        return parents;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void addParent(Parent parent) {
        parents.add(parent);
        parent.setKid(this);
    }

    public void addEvent(Event event) {
        events.add(event);
        event.setKid(this);
    }
}
