package com.dxc.fakebookbackend.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {
  private final Path root = Paths.get("uploads");
  @Override
  public void init() {
    try {
      Files.createDirectory(root);
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize folder for upload!");
    }
  }
  @Override
  public void save(MultipartFile file, String userName) {
    try {
    	Path path = Paths.get("uploads/" + userName);
    	
    	if(Files.exists(path)) {
    		Files.copy(file.getInputStream(), path.resolve(file.getOriginalFilename()));
    	} else {
    		Files.createDirectory(path);
    		Files.copy(file.getInputStream(), path.resolve(file.getOriginalFilename()));
    	}
   
    
    } catch (Exception e) {
      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
    }
  }
  
  @Override
  public Resource load(String filename, String userName) {
    try {
    	Path path = Paths.get("uploads/" + userName);
      Path file = path.resolve(filename);
      Resource resource = new UrlResource(file.toUri());
      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new RuntimeException("Could not read the file!");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }
  
  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(root.toFile());
  }
  
  @Override 
  public void deleteUser(String userName) {
	  Path path = Paths.get("uploads/" + userName);
	  FileSystemUtils.deleteRecursively(path.toFile());
  }
  
  @Override
  public Stream<Path> loadAll() {
    try {
      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
    } catch (IOException e) {
      throw new RuntimeException("Could not load the files!");
    }
  }
  
  @Override 
  public void deleteFile(String userName, String filePath) {
	  Path filetoDeletePath = Paths.get("uploads/" + userName + "/" + filePath);
	  try {
		Files.delete(filetoDeletePath);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
			  
	  
  }
}
