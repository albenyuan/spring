package com.albenyuan.spring.batch.model;

import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.util.List;

public class SchemaFileResource extends FileSystemResource {

    public SchemaFileResource(String path) {
        super(path);
    }

    public SchemaFileResource(File file) {
        super(file);
    }

    public SchemaFileResource(Path filePath) {
        super(filePath);
    }

    public SchemaFileResource(FileSystem fileSystem, String path) {
        super(fileSystem, path);
    }

    private Long fileId;

    private List<Object> schemas;

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public List<Object> getSchemas() {
        return schemas;
    }

    public void setSchemas(List<Object> schemas) {
        this.schemas = schemas;
    }
}
