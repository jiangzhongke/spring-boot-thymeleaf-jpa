package com.jvmhub.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jvmhub.springboot.domain.Thing;
import com.jvmhub.springboot.repository.ThingRepository;
import com.jvmhub.springboot.service.CacheDemoService;

@Service
public class CacheDemoServiceImpl implements CacheDemoService {

	/**
	 * 缓存的key
	 */
	public static final String THING_ALL_KEY = "\"thing_all\"";
	/**
	 * value属性表示使用哪个缓存策略，缓存策略在ehcache.xml
	 */
	public static final String DEMO_CACHE_NAME = "demo";

	@Autowired
	public ThingRepository data;

	@CacheEvict(value = DEMO_CACHE_NAME, key = THING_ALL_KEY)
	@Override
	public void create(Thing thing) {
		data.save(thing);
	}

	@Cacheable(value = DEMO_CACHE_NAME, key = "#thing.getId()+'thing'")
	@Override
	public Thing findById(Long id) {
		System.err.println("没有走缓存！" + id);
		return data.findOne(id);
	}

	@Cacheable(value = DEMO_CACHE_NAME, key = THING_ALL_KEY)
	@Override
	public Iterable<Thing> findAll() {
		return data.findAll();
	}
	
	//@Cacheable(value = DEMO_CACHE_NAME, key = THING_ALL_KEY)
	@Override
	public Iterable<Thing> findByTitle(String title) {
		return data.findByTitle(title);
	}

	@Override
	@CachePut(value = DEMO_CACHE_NAME, key = "#thing.getId()+'thing'")
	@CacheEvict(value = DEMO_CACHE_NAME, key = THING_ALL_KEY)
	public Thing update(Thing thing) {
		System.out.println(thing);
		data.save(thing);
		
		return thing;
	}

	@CacheEvict(value = DEMO_CACHE_NAME,key = THING_ALL_KEY )
	@Override
	public void delete(Long id) {
		data.delete(id);
		
	}
	
	public Page<Thing> findByPage(Pageable pageable){
		return data.findAll(pageable);
	}
	 
	 

}
