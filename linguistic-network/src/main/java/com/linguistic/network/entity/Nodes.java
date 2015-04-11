package com.linguistic.network.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="nodes")
public class Nodes implements Serializable{
	
	private static final long serialVersionUID = 8141467014964980669L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="label")
	private String label;
	
	@Column(name="nodes")
	private String nodes;
	
	public Nodes(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getNodes() {
		return nodes;
	}

	public void setNodes(String nodes) {
		this.nodes = nodes;
	}
	
	@Override
	public String toString(){
		return "id:"+id+" label:"+label+" nodes:"+nodes;
	}
}
