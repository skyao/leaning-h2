package com.github.skyao.h2demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by sky on 16-1-18.
 */
@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    @Column
    private int id;

    @Column
    private String name;

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

    @Override
    public String toString() {
        return "AddressEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
