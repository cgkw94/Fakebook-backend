package com.dxc.fakebookbackend.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.dxc.fakebookbackend.controller.PostController;
import com.dxc.fakebookbackend.dao.PostDao;
import com.dxc.fakebookbackend.dao.UserDao;
import com.dxc.fakebookbackend.entity.FileInfo;
import com.dxc.fakebookbackend.entity.Post;
import com.dxc.fakebookbackend.entity.User;
import com.dxc.fakebookbackend.reponse.ResponseMessage;

@Service
public class PostService {
	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private FilesStorageService storageService;

	private User user;
	
	
	
	
	public Page<Post> getAllPostByUserName(String userName, Pageable pageable) {
		
		return postDao.findByUserUserName(userName, pageable);
	}

	public Page<Post> getAllPost(Pageable pageable) {
		return postDao.findAll(pageable);
	}
	
	public Post createPost(String userName, Post post) {
		
		user = userDao.findByUserName(userName);
		
		post.setUser(user);
		
		return postDao.save(post);
	}

	public Post updatePost(Long postId, Post post) {
		Post postToUpdate = postDao.getOne(postId);
		
		postToUpdate.setContent(post.getContent());
		postToUpdate.setHyperlink(post.getHyperlink());
	
		return postDao.save(postToUpdate);
	}
	
	public Post updateViewCount(Long postId) {
		Post postToUpdate = postDao.getOne(postId);
		Long viewCount = postToUpdate.getViewCount();

		postToUpdate.setViewCount(viewCount + 1);
		return postDao.save(postToUpdate);
	}
	
	 public ResponseEntity<ResponseMessage> uploadFile(MultipartFile file, String content, String hyperlink, String userName) {
		    String message = "";
		    String uploadDir = "C:\\Users\\colin\\Documents\\GitHub\\Fakebook-backend\\demo\\uploads\\";
		    String filename = UUID.randomUUID().toString();
		    try {
		    	user = userDao.findByUserName(userName);
		    	
		    	Post post = new Post();
		    	
		    	post.setUser(user);
		    	post.setContent(content);
		    	post.setHyperlink(hyperlink);
		    	post.setFile_path(uploadDir + file.getOriginalFilename());
		    	
		    	postDao.save(post);
		        storageService.save(file);
		        
		        message = "Uploaded the file successfully: " + file.getOriginalFilename();
		        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		    } catch (Exception e) {
		    	message = "Could not upload the file: " + file.getOriginalFilename() + "!";
		    	return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		    }	
		  }
	 
	  public ResponseEntity<List<FileInfo>> getListFiles() {
	    List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
	      String filename = path.getFileName().toString();
	      String url = MvcUriComponentsBuilder
	          .fromMethodName(PostController.class, "getFile", path.getFileName().toString()).build().toString();
	      return new FileInfo(filename, url);
	    }).collect(Collectors.toList());
	    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	  }
	 
	 
	  public ResponseEntity<Resource> getFile(String filename) {
	    Resource file = storageService.load(filename);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	  }
}
