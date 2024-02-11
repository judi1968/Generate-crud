package com.district.test.entity;


import jakarta.persistence.*;



@Entity
@Table(name = "activite_bouquet")
public class ActiviteBouquet {

	@JoinColumn(name = "id_activite_fk")
	@ManyToOne
	Activite activite;
	@JoinColumn(name = "id_bouquet_fk")
	@ManyToOne
	Bouquet bouquet;




	public ActiviteBouquet(){}

	public Activite getActivite(){
		return this.activite;
	}
	public void setActivite(Activite activite){
		this.activite = activite;
	}
	public Bouquet getBouquet(){
		return this.bouquet;
	}
	public void setBouquet(Bouquet bouquet){
		this.bouquet = bouquet;
	}


}
