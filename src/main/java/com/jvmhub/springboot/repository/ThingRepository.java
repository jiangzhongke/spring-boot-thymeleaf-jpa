package com.jvmhub.springboot.repository;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jvmhub.springboot.domain.Thing;

public interface ThingRepository extends JpaRepository<Thing, Long>  {
	@Query("select u from Thing u where u.title like ?1%")
	Iterable<Thing> findByTitle(String title);
	//自動生成，無序實現
	List<Thing> findByContentLike(String content);
	List<Thing> findByDatefieldGreaterThan(Date datefield);
	List<Thing> findByDatefieldBetween(Date begindate,Date enddate);	

	//返回部分字段
	@Query(value="select new Thing(u.id,u.title) from Thing u")
	Page <Thing> findPartFields(Pageable pageable);  


}
