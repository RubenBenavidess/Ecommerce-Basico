package com.rejj.ecommerce.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ADMIN")
public class Administrator extends User {

    public Administrator() {
        super();
        this.setRole(Role.ADMIN);
    }

    public Administrator(Long id, String username, String email, String password) {
        super();
        this.setId(id);
        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
        this.setRole(Role.ADMIN);
    }

}
