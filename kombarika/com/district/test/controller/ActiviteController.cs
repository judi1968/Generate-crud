namespace com.district.test.controller;


using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using com.district.test.repository.ActiviteContext;
using com.district.test.entity.Activite;


[ApiController]
[Route('[activite]')]
public class ActiviteController
 {

	private readonly Ilogger<ActiviteController> _logger;


	[HttpPost]
	public ActionResult<Activite> save([FromBody] Activite activite){
	 	_context.MyItems.Add(value);
		_context.SaveChanges();
		return Ok();
	}
	[HttpPut]
	public ActionResult<Activite> update([FromBody] Activite activite){
	 	var temp = activite;
		_context.SaveChanges();
		return Ok();
	}
	[HttpDelete]
	public void delete([FromBody] Activite activite){
	 	_context.MyItems.Remove(activite);
		_context.SaveChanges();
		return Ok();
	}
	[HttpGet]
	public ActionResult<IEnumerable<Activite>> findAll(){
	 	return Ok(_context.activite.ToList());
	}

	public ActiviteController(ActiviteDbContext context) { _context = context; }



}
