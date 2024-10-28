package com.example.spring_security.dto;/*
 * @author -Suraj Tiwari
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="file_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FileData {
    @Id
    @GeneratedValue
    private  Long id;
    private String fileType;
    private String fileName;
    @Lob
    private byte[] data;
}
