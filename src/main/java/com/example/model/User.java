package com.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id", nullable = false, unique = true)
    private Long id;

    @NotBlank(message = "Username is obbligatoire")
    @Column(name ="username", nullable = false, unique = true)
    private String username;

    @Column(name ="password", nullable = false)
    @Size(min = 3 , message = "Password must be at least 3 characters long")
    private String password;

    @Column(name ="email", nullable = false, unique = true)
    @NotBlank(message = "Email is required")
    private String email;

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}