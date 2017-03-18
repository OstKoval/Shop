package com.shop.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ostap on 3/1/17.
 */
@Entity
@Table(name = "role")
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    @Column(name = "name",unique = true)
    private String name;

    public Role(){

    }

    public Role(final String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
