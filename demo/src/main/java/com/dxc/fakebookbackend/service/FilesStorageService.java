package com.dxc.fakebookbackend.service;

import java.nio.file.Path;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
  public void init();
  public void save(MultipartFile file, String userName);
  public Resource load(String filename, String userName);
  public void deleteAll();
  public void deleteUser(String userName);
  public Stream<Path> loadAll();
  public void deleteFile(String userName, String filePath);
}