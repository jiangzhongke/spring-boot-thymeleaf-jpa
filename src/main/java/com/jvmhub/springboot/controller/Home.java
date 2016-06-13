package com.jvmhub.springboot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jvmhub.springboot.domain.Post;
import com.jvmhub.springboot.domain.Thing;
import com.jvmhub.springboot.redis.RedisUtil;
import com.jvmhub.springboot.repository.PostRepository;
import com.jvmhub.springboot.service.CacheDemoService;

@Controller
public class Home {

	@Autowired
	private PostRepository 	postRepository; 

	@Autowired
	private RedisUtil 	redisUtil; 
	
	@Autowired
	private CacheDemoService demoService;
	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(Post post) {
		//model.addAttribute("post", new PostEntity());
		return "index";
	}
	
	@RequestMapping(value="/testredis", method=RequestMethod.GET)
	public String testredis(Model model) {
		Object val = redisUtil.get("testredis");
		if(val==null){
			
			redisUtil.set("testredis", "abcd");
		}
		model.addAttribute("val", val);
		
		return "testredis";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String addNewPost(@Valid Post post, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "index";
		}
		postRepository.save(post);//new Post(post.getTitle(), post.getContent()));
		model.addAttribute("posts", postRepository.findAll());
		return "redirect:result";
	}
	
	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public String showAllPosts(Model model) {
		model.addAttribute("posts", postRepository.findAll());
		return "result";
	}
	
	
	@RequestMapping(value="/input", method=RequestMethod.GET)
	public String addThingInput(Thing thing) {
		return "input";
	}
	
	@RequestMapping(value = "/input", method = RequestMethod.POST)
	public String addThing(@Valid Thing thing, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "input";
		}
		demoService.create(thing);//new Thing(thing.getTitle(), thing.getContent()));
		model.addAttribute("thing", demoService.findAll());
		return "redirect:listall";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateThing(@Valid Thing thing, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "update";
		}
		demoService.create(thing);//new Thing(thing.getTitle(), thing.getContent()));
		model.addAttribute("thing", demoService.findAll());
		return "redirect:listall";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteThing(@PathVariable Long id,Model model) {
		demoService.delete(id);
		model.addAttribute("thing", demoService.findAll());
		return "redirect:../listall";
	}
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchThing(String title,Model model) {
		Iterable<Thing> vals = demoService.findByTitle(title);
		model.addAttribute("things", vals);
		
		return "listall";
	}
	
	@RequestMapping(value = "/listall", method = RequestMethod.GET)
	public String showAllThings(Model model) {		
		model.addAttribute("things", demoService.findAll());
		return "listall";
	}
	
	@RequestMapping(value = "/listpage", method = RequestMethod.GET)
	public String listPage( @PageableDefault(value = 15, sort = { "id" }, direction = Direction.DESC) Pageable pageable,Model model) {		
		/*page，第几页，从0开始，默认为第0页
size，每一页的大小，默认为20
sort，排序相关的信息，以property,property(,ASC|DESC)的方式组织，例如sort=firstname&sort=lastname,desc表示在按firstname正序排列基础上按lastname倒序排列 */
		model.addAttribute("page", demoService.findByPage(pageable));
		
		return "listpage";
	}
	
}
