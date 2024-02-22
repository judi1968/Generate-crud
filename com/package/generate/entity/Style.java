package com.package.generate.entity;


import jakarta.persistence.*;



@Entity
@Table(name = "style")
public class Style {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JoinColumn(name = "id")
	@ManyToOne
	Stylemateriel stylemateriel;
	@Column(name = "nom")
	String nom;




	public Style(){}

	public Stylemateriel getStylemateriel(){
		return this.stylemateriel;
	}
	public void setStylemateriel(Stylemateriel stylemateriel){
		this.stylemateriel = stylemateriel;
	}
	public String getNom(){
		return this.nom;
	}
	public void setNom(String nom){
		this.nom = nom;
	}


}
