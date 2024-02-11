namespace com.district.test.repository;


using com.district.test.entity.Activite;
using using Microsoft.EntityFrameworkCore;


public class ActiviteController : DbContext {

	public DbSet<Activite> Activite { get; set; }







}
