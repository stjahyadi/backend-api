package com.singtel.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Person implements Serializable {
	private static final long serialVersionUID = 7793367275462433253L;
	private String code;
	private String name;
	private Integer age;
	private BigDecimal premium;
	private String gender;

	public Person() {
	}

	public Person(String code, String name, Integer age, BigDecimal amount, String gender) {
		this.code = code;
		this.name = name;
		this.age = age;
		this.premium = amount;
		this.gender = gender;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public BigDecimal getAmount() {
		return premium;
	}

	public void setAmount(BigDecimal amount) {
		this.premium = amount;
	}

}
