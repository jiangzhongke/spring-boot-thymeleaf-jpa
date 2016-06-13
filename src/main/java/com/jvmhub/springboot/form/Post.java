package com.jvmhub.springboot.form;

import javax.validation.constraints.Size;

public class Post {
	
	@Size(min=4, max=35)
	private String title;
	
	@Size(min=30, max= 1000)
	private String content;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
