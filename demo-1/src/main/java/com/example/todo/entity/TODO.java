package com.example.todo.entity;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
 
 
@Entity
@Table(name = "TODOS")
public class TODO {
     
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "El ID de la tarea. Generado automáticamente por la base de datos.")
    @Column(name = "ID", nullable = false)
    private Long id;
    @ApiModelProperty(notes = "El titulo de la tarea a realizar.")
    @Column(name = "TITLE", nullable = false)  
    private String title;
    @ApiModelProperty(notes = "Descripción detallada de la tarea a realizar.")
    @Column(name = "DESC", nullable = false)  
    private String description;
    @ApiModelProperty(notes = "Estado en el que se encuentra la tarea.")
    @Column(name = "STATE", nullable = false)
    private String state;
    @ApiModelProperty(notes = "Fecha de inicio de la tarea. (DD/MM/YYYY)")
    @Column(name = "CDATE", nullable = false)
    private String creationDate;
    @ApiModelProperty(notes = "Fecha en la que se ha modificado la tarea. (DD/MM/YYYY)")
    @Column(name = "MDATE", nullable = true)
    private String modificationDate;
    
    
    public TODO(Long id, String title, String desc,String state,String cdate,String mdate) {
    	this.id=id;
    	this.title=title;
    	this.description=desc;
    	this.state=state;
    	this.creationDate=cdate;
    	this.modificationDate=mdate;
    }
    
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getModificationDate() {
		return modificationDate;
	}
	public void setModificationDate(String modificationDate) {
		this.modificationDate = modificationDate;
	}
    
}
