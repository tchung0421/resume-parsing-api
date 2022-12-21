package com.snva.resumeprocessor.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IFileStorageService {
        void init();
        String saveFile(MultipartFile multipartFile);
        Resource loadFile(String fileName);
}
