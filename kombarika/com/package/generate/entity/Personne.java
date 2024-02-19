package com.package.generate.entity;


import jakarta.persistence.*;



@Entity
@Table(name = "personne")
public class Personne {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_personne")
	Integer idPersonne;
	@Column(name = "nom")
	Integer nom;




	public Personne(){}

	public Integer getIdPersonne(){
		return this.idPersonne;
	}
	public void setIdPersonne(Integer idPersonne){
		this.idPersonne = idPersonne;
	}
	public Integer getNom(){
		return this.nom;
	}
	public void setNom(Integer nom){
		this.nom = nom;
	}


}
