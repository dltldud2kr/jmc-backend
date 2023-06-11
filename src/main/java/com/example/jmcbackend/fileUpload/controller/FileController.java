package com.example.jmcbackend.fileUpload.controller;

import com.example.jmcbackend.fileUpload.sevice.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/s3")
public class FileController {

    private final FileService fileService;
    @PostMapping(value="/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String saveImage(@RequestParam(value="image") MultipartFile image) throws IOException {
        return fileService.saveImage(image);
    }
}
