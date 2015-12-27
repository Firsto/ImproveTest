package ru.firsto.improvetest.model;

import javax.persistence.*;

/**
 * User: razor
 * Date: 25.12.2015
 */

@Entity
@Table(name= "cat", schema = "improvetest")
public class Catalog extends NamedEntity {

    public Catalog() {
    }

    public Catalog(Integer id, String name) {
        super(id, name);
    }

    @Id
    @Column(name = "id")
    @Override
    public Integer getId() {
        return super.getId();
    }

    @Basic
    @Column(name = "name")
    @Override
    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "id=" + id +
                ", name=" + name +
                "}";
    }
}
