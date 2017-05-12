package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import models.Person
import play.api.libs.json._
import play.api.libs.json.JsValue
import play.api.libs.functional.syntax._
//import models.DB

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() extends Controller {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */

   case class Person(id: Int, name: String)

  val persons = List(
    Person(1,"david"),
    Person(2,"yisus"),
    Person(3,"andres")
  )

  implicit val personWrite = Json.writes[Person]
  implicit val personRead = Json.reads[Person]
  def index = Action { implicit request =>
    Ok("hola a todos")
  }

  def listPerson = Action {
    val json = Json.toJson(persons)
    val person = json.validate[Person]
    Ok(json)
  }


  def getPerson = Action { implicit request =>

    val json =Json.toJson(request.body.asJson)
    val person = json.validate[Person].get
    Ok("hola " + person.name + " pendejo!")
  }
}
