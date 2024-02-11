namespace com.district.test.entity;


using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;



[Table(name = "activite_bouquet")]
public class ActiviteBouquet {

	[Foreignkey("id_activite_fk")]
	Activite activite { get; set; }
	[Foreignkey("id_bouquet_fk")]
	Bouquet bouquet { get; set; }




	public ActiviteBouquet(){}



}
