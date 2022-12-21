package com.snva.resumeprocessor;

import com.snva.resumeprocessor.properties.FileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication

@EnableConfigurationProperties({
		FileUploadProperties.class
})
public class ResumeProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResumeProcessorApplication.class, args);
	}

}
