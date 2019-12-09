package com.fokot

import scalatags.Text
import scalatags.Text.all.Frag
import scalatags.Text.all._
import scalatex.{CalibanAuth, CalibanAuthSnippet, UltimateTicTacToe, UltimateTicTacToeSnippet}

object Articles {

  case class Article(
    name: String,
    title: String,
    snippet: Frag,
    article: Frag,
    tags: List[String],
    date: String,
    image: String,
    link: String,
  )

  object Article {
    def simple(
      name: String,
      title: String,
      snippet: Frag,
      article: Frag,
      tags: List[String],
      date: String,
      imageName: String,
    ): Article = {
      val image = s"/static/img/${imageName}"
      Article(
        name,
        title,
        snippet,
        toArticle(title, snippet, article, image, tags, date),
        tags,
        date,
        image,
        s"post/$name"
      )
    }
  }

  val styles = link(
    rel:="stylesheet",
    `type`:="text/css",
    href:="/static/styles.css"
  )

  val jQuery = script(
    src:="https://cdn.jsdelivr.net/npm/jquery@3.4.1/dist/jquery.min.js"
  )

  val prismCss = link(
    rel:="stylesheet",
    `type`:="text/css",
    href:="https://cdn.jsdelivr.net/npm/prismjs@1.17.1/themes/prism.css"
  )

  val prismJs = script(
    src:="https://cdn.jsdelivr.net/npm/prismjs@1.17.1/prism.min.js"
  )
  val prismJsScala = script(
    src:="https://cdn.jsdelivr.net/npm/prismjs@1.17.1/plugins/autoloader/prism-autoloader.min.js"
  )

  def toMainPage(b: Frag): Frag =
    html(
      head(
        styles,
        jQuery
      ),
      body(
        b
      )
    )

  def toArticle(title: String, snippet: Frag, article: Frag, image: String, tags: List[String], date: String): Frag =
    html(
      head(
        styles,
        prismCss,
        prismJs,
        prismJsScala,
      ),
      body(
        img(`class`:= "article-image", src:= image),
        a(`class`:="main-page-link",
          href:="/",
          "fokot.github.io"
        ),
        a(`class`:="github-icon",
          href:="https://github.com/fokot",
          img(src:= "/static/img/github.svg")
        ),
        div(
          `class`:="read",
          h1(title),
          div(`class`:="tags", date + " on " + tags.mkString(", ")),
          snippet,
          article
        )
      )
    )

  def toUltimateTicTacToeArticle(title: String, snippet: Frag, article: Frag, image: String, tags: List[String], date: String): Frag =
    html(
      head(
        styles,
        link(
          rel:="stylesheet",
          `type`:="text/css",
          href:="/static/ultimate-tic-tac-toe/styles.css"
        ),
      ),
      body(
        img(`class`:= "article-image", src:= image),
        a(`class`:="main-page-link",
          href:="/",
          "fokot.github.io"
        ),
        a(`class`:="github-icon",
          href:="https://github.com/fokot",
          img(src:= "/static/img/github.svg")
        ),
        div(
          `class`:="read",
          h1(title),
          div(`class`:="tags", date + " on " + tags.mkString(", ")),
          snippet,
          article
        )
      )
    )

  lazy val articles: Seq[Article] = List(
    Article.simple(
      "caliban-auth.html",
      "Authentication in Caliban",
      CalibanAuthSnippet(),
      CalibanAuth(),
      List("Caliban", "ZIO", "GraphQL", "Scala"),
      "2019-12-07",
      "sunset.jpg"
    ),
    Article(
      "ultimate-tic-tac-toe.html",
      "Ultimate Tic tac toe",
      UltimateTicTacToeSnippet(),
      toUltimateTicTacToeArticle("Ultimate Tic tac toe", UltimateTicTacToeSnippet(), UltimateTicTacToe(),
        "/static/img/tic-tac-toe.jpg", List("Clojure", "Reagent", "React", "Game"), "2019-12-07"
      ),
      List("Clojure", "Reagent", "React", "Game"),
      "2019-12-07",
      "/static/img/tic-tac-toe.jpg",
      "post/ultimate-tic-tac-toe.html"
    )
  )

  def removeOffset(code: String): String = {
    val nonEmptyLines = code.lines.dropWhile(_.trim.isEmpty).toList
    val offset = nonEmptyLines.head.takeWhile(_ == ' ').length
    "\n" + nonEmptyLines.map(l => if(l.trim.isEmpty) l else l.substring(offset)).mkString("\n")
  }

  def x(s: String): Text.TypedTag[String] =
    span(`class`:="x", s)

}
