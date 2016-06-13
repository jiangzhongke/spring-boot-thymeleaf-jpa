package com.jvmhub.springboot.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import com.jvmhub.springboot.domain.Post;



public interface PostRepository extends CrudRepository<Post, Long> {	
	List<Post> findByTitle(String title);
	
}
