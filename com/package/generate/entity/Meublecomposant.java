package com.package.generate.entity;


import jakarta.persistence.*;



@Entity
@Table(name = "meublecomposant")
public class Meublecomposant {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JoinColumn(name = "id_composant")
	@ManyToOne
	Composants composants;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JoinColumn(name = "id_meuble")
	@ManyToOne
	Meubles meubles;
	@Column(name = "quantite")
	Integer quantite;




	public Meublecomposant(){}

	public Composants getComposants(){
		return this.composants;
	}
	public void setComposants(Composants composants){
		this.composants = composants;
	}
	public Meubles getMeubles(){
		return this.meubles;
	}
	public void setMeubles(Meubles meubles){
		this.meubles = meubles;
	}
	public Integer getQuantite(){
		return this.quantite;
	}
	public void setQuantite(Integer quantite){
		this.quantite = quantite;
	}


}
