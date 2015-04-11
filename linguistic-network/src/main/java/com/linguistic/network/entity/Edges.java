package com.linguistic.network.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="edges")
public class Edges implements Serializable{

	private static final long serialVersionUID = -6469390232041914922L;

	// source target type id label weight
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="source")
	private int source;

	@Column(name="target")
	private int target;
	
	@Column(name="type")
	private String type;
	
	@Column(name="label")
	private String label;
	
	@Column(name="weight")
	private int weight;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	@Override
	public String toString(){
		return "id:"+id+" source:"+source+" target:"+target+" type:"+type+" label:"+label+" weight:"+weight;
	}

}
