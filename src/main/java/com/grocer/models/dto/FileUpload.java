package com.grocer.models.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class FileUpload {
    @NotNull
    private MultipartFile file;
}