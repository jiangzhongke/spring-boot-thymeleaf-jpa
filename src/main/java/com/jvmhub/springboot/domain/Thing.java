package com.jvmhub.springboot.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.jvmhub.springboot.validate.Money;


@Entity  
@Table(name = "thing")
public class Thing{
	
	public Thing() {}
	public Thing(Long id,String title){
		this.id = id;
		this.title = title;
	}
	public Thing(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "标题不能为空")
	@Column(name="title",unique=true,nullable=false)
	private String title;
	
	
	@Temporal(TemporalType.DATE)
	private Date datefield;
	
	@Temporal(TemporalType.TIME)
	private Date timefield;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createtime;
	
	
	@Size(min=30, max= 1000)
	@Lob 
	@Basic(fetch = FetchType.LAZY)
	private String content;
	

	@Pattern(regexp = "1([\\d]{10})|((\\+[0-9]{2,4})?\\(?[0-9]+\\)?-?)?[0-9]{7,8}")
	private String phone;
 

	@Money(message="标准的金额形式为xxx.xx")
	private BigDecimal money;
	
	public Date getDatefield() {
		return datefield;
	}
	public void setDatefield(Date datefield) {
		this.datefield = datefield;
	}
	public Date getTimefield() {
		return timefield;
	}
	public void setTimefield(Date timefield) {
		this.timefield = timefield;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getTransfield() {
		return transfield;
	}
	public void setTransfield(String transfield) {
		this.transfield = transfield;
	}

	@Transient
	private String transfield;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
