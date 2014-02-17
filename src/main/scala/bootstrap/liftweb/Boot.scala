package bootstrap.liftweb

import net.liftweb._
import util._
import Helpers._

import common._
import http._
import js.jquery.JQueryArtifacts
import sitemap._
import Loc._
import mapper._

import code.blog.model._
import net.liftmodules.JQueryModule

/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot {
  def boot {

    // see: https://fmpwizard.telegr.am/blog/lift-and-h2 for viewing h2 tables
    //if (Props.devMode || Props.testMode) {
    LiftRules.liftRequest.append({case r if (r.path.partPath match {
      case "console" :: _ => true
      case _ => false}
      ) => false})
  //}

    if (!DB.jndiJdbcConnAvailable_?) {
      val vendor =
        new StandardDBVendor(Props.get("db.driver") openOr "org.h2.Driver",
          Props.get("db.url") openOr
            "jdbc:h2:lift_proto.db;AUTO_SERVER=TRUE",
          Props.get("db.user"), Props.get("db.password"))

      LiftRules.unloadHooks.append(vendor.closeAllConnections_! _)

      DB.defineConnectionManager(DefaultConnectionIdentifier, vendor)
    }

    // Use Lift's Mapper ORM to populate the database
    // you don't need to use Mapper to use Lift... use
    // any ORM you want
    Schemifier.schemify(true, Schemifier.infoF _, User)

    // where to search snippet
    LiftRules.addToPackages("code.blog")

    // Build SiteMap
    def sitemap = SiteMap(
      Menu("Home") / "index",// >> User.AddUserMenusAfter, // the simple way to declareb a menu
//      Menu("用户账户") / "user" submenus(
//          Menu("代替") / "tobetaken" >> User.AddUserMenusHere), 
      Menu("用户账户") / "user" >> User.AddUserMenusHere,
      Menu("博客设置") / "blog" / "index" submenus(
          Menu("上传博客") / "blog" / "add",
          Menu("删除博客") / "blog" / "delete",
          Menu("下载博客") / "blog" / "download"
      ),
      Menu("我的博客") / "blog" / "display",

      // more complex because this menu allows anything in the
      // /static path to be visible
      Menu(Loc("Static", Link(List("static"), true, "/static/index"),
        "Static Content")))

    def sitemapMutators = User.sitemapMutator

    // set the sitemap.  Note if you don't want access control for
    // each page, just comment this line out.
    LiftRules.setSiteMapFunc(() => sitemapMutators(sitemap))

    //LiftRules.handleMimeFile = OnDiskFileParamHolder.apply
    //Init the jQuery module, see http://liftweb.net/jquery for more information.
    LiftRules.jsArtifacts = JQueryArtifacts
    JQueryModule.InitParam.JQuery = JQueryModule.JQuery172
    JQueryModule.init()

    //Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    // What is the function to test if a user is logged in?
    LiftRules.loggedInTest = Full(() => User.loggedIn_?)

    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) =>
      new Html5Properties(r.userAgent))

    // Make a transaction span the whole HTTP request
    S.addAround(DB.buildLoanWrapper)
  }
}
