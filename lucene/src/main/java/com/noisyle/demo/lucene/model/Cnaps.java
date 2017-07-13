package com.noisyle.demo.lucene.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "BANKNAMEANDCODE")
@XmlRootElement(name="Cnaps")
public class Cnaps {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="N_ID")
	private Long id;
	@Column(name="S_BANKCODE")
	private String bankCode;
	@Column(name="S_BANKNAME")
	private String bankName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
}
