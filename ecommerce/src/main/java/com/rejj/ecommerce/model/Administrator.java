package com.rejj.ecommerce.model;

import jakarta.persistence.*;

@Table(name = "ADMINISTRATORS")
@Entity
public class Administrator extends User{

    public Administrator() {
        super();
    }

    public Administrator(Integer id, String name, String email, String password, String address) {
        super(id, name, email, password, address);
    }

}
