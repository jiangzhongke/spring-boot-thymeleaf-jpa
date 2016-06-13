package com.jvmhub.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jvmhub.springboot.domain.Thing;



public interface CacheDemoService {
	  public void create(Thing thing);
	  public Thing findById(Long id);
	  public Iterable<Thing> findAll();
	  public Iterable<Thing> findByTitle(String title);
	  public Thing update(Thing thing);
	  public void delete(Long id);
	  
	  public Page<Thing> findByPage(Pageable pageable);
}
