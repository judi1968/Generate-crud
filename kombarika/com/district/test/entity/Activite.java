package com.district.test.entity;


import jakarta.persistence.*;



@Entity
@Table(name = "activite")
public class Activite {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_activite")
	Integer idActivite;
	@Column(name = "nom_activite")
	String nomActivite;




	public Activite(){}

	public Integer getIdActivite(){
		return this.idActivite;
	}
	public void setIdActivite(Integer idActivite){
		this.idActivite = idActivite;
	}
	public String getNomActivite(){
		return this.nomActivite;
	}
	public void setNomActivite(String nomActivite){
		this.nomActivite = nomActivite;
	}


}
