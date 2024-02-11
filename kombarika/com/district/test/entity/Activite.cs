namespace com.district.test.entity;


using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;



[Table(name = "activite")]
public class Activite {

	[Id]
	[Column(name = "id_activite")]
	int idActivite { get; set; }
	[Column(name = "nom_activite")]
	string nomActivite { get; set; }




	public Activite(){}



}
