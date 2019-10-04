package com.cg.pcm.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


// Bean class of the Project
@Entity
@Table(name = "project")
@SequenceGenerator(name = "seq_project", initialValue=1001)
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_project")
	private long id;
	private String name;
	private String description;
	private Date startDate;
	private Date endDate;
	private String businessUnit;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Project(long id, String name, String description, Date startDate, Date endDate, String businessUnit) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.businessUnit = businessUnit;
	}

	public Project(String name, String description, Date startDate, Date endDate, String businessUnit) {
		super();
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.businessUnit = businessUnit;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", description=" + description + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", businessUnit=" + businessUnit + "]";
	}

}
