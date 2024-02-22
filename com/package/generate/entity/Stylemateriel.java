package com.package.generate.entity;


import jakarta.persistence.*;



@Entity
@Table(name = "stylemateriel")
public class Stylemateriel {

	@Column(name = "id_materiel")
	Integer idMateriel;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	Integer id;
	@Column(name = "id_style")
	Integer idStyle;




	public Stylemateriel(){}

	public Integer getIdMateriel(){
		return this.idMateriel;
	}
	public void setIdMateriel(Integer idMateriel){
		this.idMateriel = idMateriel;
	}
	public Integer getId(){
		return this.id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getIdStyle(){
		return this.idStyle;
	}
	public void setIdStyle(Integer idStyle){
		this.idStyle = idStyle;
	}


}
