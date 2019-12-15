package com.fokot

import scalatags.Text.Modifier
import scalatags.Text.all.{Frag, href, _}
import scalatex.{CalibanAuth, CalibanAuthSnippet, UltimateTicTacToe, UltimateTicTacToeSnippet}
import com.fokot.Utils.{stylesheet, styles, jQuery, prismCss, prismJs, prismJsScala, disqus }

object Articles {

  case class Article(
    name: String,
    title: String,
    snippet: Frag,
    mainArticle: Frag,
    tags: List[String],
    date: String,
    imageName: String,
    articleType: Article => Frag
  ) {
    def image: String = s"/static/img/${imageName}"
    def link: String = s"post/$name"
    def toHtml: Frag = articleType(this)
  }

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

  def simpleArticle(article: Article): Frag =
    articleToHtml(article, List(styles, prismCss, prismJs, prismJsScala))

  def ultimateTicTacToeArticle(article: Article): Frag =
    articleToHtml(article, List(styles, stylesheet("/static/ultimate-tic-tac-toe/styles.css")))

  def articleToHtml(article: Article, links: List[Modifier]): Frag =
    html(
      head(links:_*),
      body(
        img(`class`:= "article-image", src:= article.image),
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
          h1(article.title),
          div(`class`:="tags", article.date + " on " + article.tags.mkString(", ")),
          article.snippet,
          article.mainArticle
        ),
        disqus(article.link)
      )
    )


  lazy val articles: Seq[Article] = List(
    Article(
      "caliban-auth.html",
      "Authentication in Caliban",
      CalibanAuthSnippet(),
      CalibanAuth(),
      List("Caliban", "ZIO", "GraphQL", "Scala"),
      "2019-12-07",
      "sunset.jpg",
      simpleArticle
    ),
    Article(
      "ultimate-tic-tac-toe.html",
      "Ultimate Tic tac toe",
      UltimateTicTacToeSnippet(),
      UltimateTicTacToe(),
      List("Clojure", "Reagent", "React", "Game"),
      "2019-12-07",
      "tic-tac-toe.jpg",
      ultimateTicTacToeArticle
    )
  )
}
