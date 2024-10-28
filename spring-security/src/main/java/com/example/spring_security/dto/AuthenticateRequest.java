package com.example.spring_security.dto;/*
 * @author -Suraj Tiwari
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticateRequest {
    private String email;
    private String password;
}
