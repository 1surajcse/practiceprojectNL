package com.example.spring_security.repository;/*
 * @author -Suraj Tiwari
 */

import com.example.spring_security.dto.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileData ,Long> {
}
