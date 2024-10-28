package com.example.RestApiSample.user;/*
 * @author -Suraj Tiwari
 */

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_tbl")
public class User {
    @Id
    private int id;
    private String name;
}
