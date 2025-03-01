package com.example.krompirica.Worker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {
    private Integer id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
}
