I use [browser-sync](https://www.browsersync.io/) to show preview and hot reload in browser.

Either install it with `npm` and use
```shell script
npm install -g browser-sync
browser-sync start --server --files "./**" --"."
```
or use docker version
```shell script
alias browser-sync='docker run --rm -dt --name browser-sync -p 3000:3000 -v $(PWD):/source -w /source ustwo/browser-sync start --server --files "./**" --"." && open http://localhost:3000'
```
Requirement for `browser-sync` to refresh page is that the page has `body` tag.


Run app with
```shell script
sbt ~run
```

Scalatex is not able to parametrize an entire template as a function :(

