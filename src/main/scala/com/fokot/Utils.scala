package com.fokot

import scalatags.Text
import scalatags.Text.all._

object Utils {

  def stylesheet(s: String): Text.TypedTag[String] =
    link(
      rel:="stylesheet",
      `type`:="text/css",
      href:=s
    )

  val styles = stylesheet("/static/styles.css")

  val jQuery = script(
    src:="https://cdn.jsdelivr.net/npm/jquery@3.4.1/dist/jquery.min.js"
  )

  val prismCss = stylesheet("https://cdn.jsdelivr.net/npm/prismjs@1.17.1/themes/prism.css")

  val prismJs = script(
    src:="https://cdn.jsdelivr.net/npm/prismjs@1.17.1/prism.min.js"
  )
  val prismJsScala = script(
    src:="https://cdn.jsdelivr.net/npm/prismjs@1.17.1/plugins/autoloader/prism-autoloader.min.js"
  )

  def removeOffset(code: String): String = {
    val nonEmptyLines = code.lines.dropWhile(_.trim.isEmpty).toList
    val offset = nonEmptyLines.head.takeWhile(_ == ' ').length
    "\n" + nonEmptyLines.map(l => if(l.trim.isEmpty) l else l.substring(offset)).mkString("\n")
  }

  def scalaCode(codeString: String): Frag =
    pre(
      code(
        `class`:= "language-scala",
        removeOffset(codeString)
      )
    )

  def x(s: String): Text.TypedTag[String] =
    span(`class`:="x", s)

  def disqus(link: String): Frag =
    div(
      `class`:="read",
      id:="disqus_thread",
      script(
        s"""
        /**
        *  RECOMMENDED CONFIGURATION VARIABLES: EDIT AND UNCOMMENT THE SECTION BELOW TO INSERT DYNAMIC VALUES FROM YOUR PLATFORM OR CMS.
        *  LEARN WHY DEFINING THESE VARIABLES IS IMPORTANT: https://disqus.com/admin/universalcode/#configuration-variables*/
        /*
        var disqus_config = function () {
        this.page.url = ${link};  // Replace PAGE_URL with your page's canonical URL variable
        this.page.identifier = ${link}; // Replace PAGE_IDENTIFIER with your page's unique identifier variable
        };
        */
        (function() { // DON'T EDIT BELOW THIS LINE
        var d = document, s = d.createElement('script');
        s.src = 'https://fokot-github-io.disqus.com/embed.js';
        s.setAttribute('data-timestamp', +new Date());
        (d.head || d.body).appendChild(s);
        })();
        """
      ),
    )

}
