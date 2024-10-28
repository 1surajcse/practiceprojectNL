package com.example.spring_security.controller;/*
 * @author -Suraj Tiwari
 */


import com.example.spring_security.dto.FileData;
import com.example.spring_security.dto.ResponseData;
import com.example.spring_security.service.FileService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping({"/api/v1/demo"})
public class DemoController {
    @Autowired
    private FileService fileService;

    public DemoController() {
    }

    @GetMapping
    public String sayHello() {
        return "welcome to Demo Controller";
    }

    @GetMapping({"/upload"})
    public ResponseEntity<ResponseData> upload(@RequestParam("file") MultipartFile file) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path p = Paths.get("Files-Upload");
        if (!Files.exists(p)) {
            Files.createDirectories(p);
        }
        int fileCode=0;
        try {
            InputStream stream = file.getInputStream();
          fileCode =new Random().nextInt(2);

            try {
                Path filePath = p.resolve(String.valueOf(fileCode));
                Files.copy(stream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (Throwable var8) {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (Throwable var7) {
                        var8.addSuppressed(var7);
                    }
                }

                throw var8;
            }

            if (stream != null) {
                stream.close();
            }
        } catch (IOException var9) {
            IOException e = var9;
            System.out.println("Some Exception" + e);
        }

        FileData fileData = FileData.builder().data(file.getBytes()).fileType(file.getContentType()).fileName(fileName).build();
        this.fileService.save(fileData);
        System.out.println("mapping:  ");


        ResponseData responseData = ResponseData.builder().fileSize(file.getSize()).fileName(fileName).fileType(file.getContentType()).
                downloadURL(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/demo/download/"+fileCode).toUriString()).build();
        return new ResponseEntity(responseData, HttpStatus.OK);
    }

    @GetMapping({"/download/{fileId}"})
    public ResponseEntity<Resource> download(@PathVariable("fileId") String fileId) {
        Optional<FileData> fileData = this.fileService.find(Long.valueOf(fileId));

        return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType((fileData.get()).getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileData.get().getFileName()+"\"").body(new ByteArrayResource(fileData.get().getData()));
    }
}

