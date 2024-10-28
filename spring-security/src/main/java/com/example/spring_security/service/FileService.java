package com.example.spring_security.service;/*
 * @author -Suraj Tiwari
 */

import com.example.spring_security.dto.FileData;
import com.example.spring_security.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;


    public FileData save(FileData fileData) {
        FileData fileData1=fileRepository.save(fileData);
        return fileData1;
    }

    public Optional<FileData> find(Long fileId) {
       return fileRepository.findById(fileId);
    }
}
