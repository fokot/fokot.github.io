package com.fokot

import os.RelPath
import scalatex.MainPage

object Main extends App {

  val mainPage = MainPage()
  os.write.over(os.pwd / "index.html", Articles.toMainPage(mainPage).render)
  Articles.articles.foreach {
    a => os.write.over(os.pwd / RelPath(a.link), a.toHtml.render, createFolders = true)
  }
  println("RENDERED")
}
