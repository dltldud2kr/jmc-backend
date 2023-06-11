package com.example.jmcbackend.fileUpload.sevice;

import com.example.jmcbackend.fileUpload.S3.S3Uploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private S3Uploader s3Uploader;

    @Override @Transactional
    public String saveImage(MultipartFile image) throws IOException {
        //프론트에서 도착한 이미지가 있다면
        if(!image.isEmpty()) {
            String uploadFileName = s3Uploader.upload(image,"images");
            return uploadFileName;
        } else {
            return "image is null";
        }
    }
}
