package com.flightbookings.entities;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;


@Entity
@Component
public class IdGeneratorTable {

	@Id
	private Integer id;
    private String Description;
    private Date date;
    private String prefix;
    private Integer CurrentId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public Integer getCurrentId() {
		return CurrentId;
	}
	@Override
	public String toString() {
		return "IdGeneratorTable [id=" + id + ", Description=" + Description + ", date=" + date + ", prefix=" + prefix
				+ ", CurrentId=" + CurrentId + "]";
	}
	public void setCurrentId(Integer currentId) {
		CurrentId = currentId;
	}
    
    
}
