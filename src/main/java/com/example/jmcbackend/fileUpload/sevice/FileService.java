package com.example.jmcbackend.fileUpload.sevice;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface FileService {

    String saveImage(MultipartFile image) throws IOException;
}
