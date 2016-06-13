package com.jvmhub.springboot;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jvmhub.springboot.domain.Thing;
import com.jvmhub.springboot.repository.ThingRepository;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootThymeleafApplication.class)
@WebAppConfiguration
public class SpringBootThymeleafApplicationTests {

	@Autowired
	ThingRepository thingrepo;
	
	@Test
	public void contextLoads() {
		System.out.println(thingrepo.count());
		/*
		List<Thing> vals = thingrepo.findByContentLike("abcd");
		assertEquals(vals.size(),0);
		
		List<Thing> vals2 = thingrepo.findByContentLike("%123456789%");
		assertNotEquals(vals2.size(),0);
		
		List<Thing> vals3 = thingrepo.findByDatefieldGreaterThan(new Date("2016/06/03 00:00:00"));
		assertNotEquals(vals3.size(),0);
		
		List<Thing> vals4 = thingrepo.findByDatefieldBetween(new Date("2016/06/03 00:00:00"),new Date("2016/06/05 00:00:00"));
		assertNotEquals(vals4.size(),0);
		
		Page<Thing> tmppage1 = thingrepo.findPartFields(new PageRequest(0,1));
		assertNotEquals(tmppage1.getContent().size(),0);
		*/
		Thing tmpthing = new Thing("a1234","a1012345678901234567890123456789");
		tmpthing.setPhone("32432432432");
		tmpthing.setMoney(new BigDecimal("2"));
		thingrepo.save(tmpthing);
		//批量保存测试
		List<Thing> tmpthinglist = new ArrayList<Thing>();
		tmpthinglist.add(new Thing("new1_4","newcontent1_012345678901234567890123456789"));
		tmpthinglist.add(new Thing("new2_4","newcontent2_012345678901234567890123456789"));
		List<Thing>tmpresultlist = thingrepo.save(tmpthinglist);
		assertNotEquals(tmpresultlist.size(),0);
	}

}
