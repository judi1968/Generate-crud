package com.package.generate.entity;


import jakarta.persistence.*;



@Entity
@Table(name = "weatherforecast")
public class Weatherforecast {

	@Column(name = "summary")
	String summary;
	@Column(name = "temperaturec")
	Integer temperaturec;
	@Column(name = "temperaturef")
	Integer temperaturef;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	Integer id;




	public Weatherforecast(){}

	public String getSummary(){
		return this.summary;
	}
	public void setSummary(String summary){
		this.summary = summary;
	}
	public Integer getTemperaturec(){
		return this.temperaturec;
	}
	public void setTemperaturec(Integer temperaturec){
		this.temperaturec = temperaturec;
	}
	public Integer getTemperaturef(){
		return this.temperaturef;
	}
	public void setTemperaturef(Integer temperaturef){
		this.temperaturef = temperaturef;
	}
	public Integer getId(){
		return this.id;
	}
	public void setId(Integer id){
		this.id = id;
	}


}
