package com.example.krompirica.Worker;

import com.example.krompirica.Role.RoleEntity;
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

    @Column(name="storedHash", nullable = false)
    private byte[] storedHash;

    @Column(name="storedSalt", nullable = false)
    private byte[] storedSalt;

    @Column(name="role_id", nullable = false)
    private Integer role;

    public WorkerEntity(String username){
        this.username=username;
    }
}
