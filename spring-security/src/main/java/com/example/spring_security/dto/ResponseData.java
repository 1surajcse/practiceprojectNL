package com.example.spring_security.dto;/*
 * @author -Suraj Tiwari
 */

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseData {
    private String fileType;
    private String fileName;
    private long  fileSize;
    private String downloadURL;
}
