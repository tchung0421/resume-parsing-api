package com.snva.resumeprocessor.service;

import com.snva.resumeprocessor.exception.FileStorageException;
import com.snva.resumeprocessor.properties.FileUploadProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileStorageService implements  IFileStorageService {

    private  final Path dirLocation;

    @Autowired
    public FileStorageService(FileUploadProperties fileUploadProperties) {

        this.dirLocation = Paths.get(fileUploadProperties.getLocation())
                .toAbsolutePath()
                .normalize();
    }

    @Override
    @PostConstruct
    public void init() {
        try{
            Files.createDirectories(this.dirLocation);
        }catch (Exception ex){
            throw  new FileStorageException("Could not upload to the Drirectory");
        }

    }

    @Override
    public String saveFile(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            Path dFile = this.dirLocation.resolve(fileName);
            Files.copy(multipartFile.getInputStream(),dFile, StandardCopyOption.REPLACE_EXISTING);
            return  fileName;
        }
        catch (Exception e){
            throw  new FileStorageException("Could not upload the stated "+ multipartFile.getOriginalFilename());
        }
    }
        // YAGNY
    @Override
    public Resource loadFile(String fileName) {
        try {
            Path dFile = this.dirLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(dFile.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new FileStorageException("Rhe file was not found");
            }
        } catch (Exception e) {
            throw new FileStorageException("Could Not process the file for read");
        }

    }

@Override
    public   List<String> allFileNames() {
        try {
            Path dFile = this.dirLocation.normalize();

            File f = new File(dFile.toAbsolutePath().toString());
            File[] files=  f.listFiles();
            List<String >
                     fileName = new ArrayList<>();

            for (File d : files  ) {
                fileName.add(d.getName()+"|"+d.getAbsolutePath());
            }

          return  fileName;
        } catch (Exception e) {
            throw new FileStorageException("Could Not process the file for read");
        }

    }
}
