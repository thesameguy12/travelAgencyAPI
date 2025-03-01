package com.example.krompirica.Worker;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "worker")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="firstName", nullable = false)
    private String firstName;

    @Column(name="lastName", nullable = false)
    private String lastName;

    @Column(name="username", nullable = false)
    private String username;

    @Column(name="passwordHash", nullable = false)
    private byte[] passwordHash;

    @Column(name="passwordSalt", nullable = false)
    private byte[] passwordSalt;

    public WorkerEntity(String username){
        this.username=username;
    }
}
