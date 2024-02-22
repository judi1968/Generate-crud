package com.package.generate.entity;


import jakarta.persistence.*;



@Entity
@Table(name = "meubles")
public class Meubles {

	@Column(name = "description")
	String description;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	Integer id;
	@Column(name = "type")
	String type;
	@Column(name = "nom")
	String nom;




	public Meubles(){}

	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public Integer getId(){
		return this.id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getNom(){
		return this.nom;
	}
	public void setNom(String nom){
		this.nom = nom;
	}


}
