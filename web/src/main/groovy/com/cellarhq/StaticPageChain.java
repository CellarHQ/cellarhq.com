package com.cellarhq;

import com.google.common.collect.ImmutableMap;
import ratpack.func.Action;
import ratpack.handling.Chain;

import static ratpack.handlebars.Template.handlebarsTemplate;

public class StaticPageChain implements Action<Chain> {
  @Override
  public void execute(Chain chain) throws Exception {
    chain
      .get("about", ctx -> ctx.render(handlebarsTemplate("about.html", ImmutableMap.of("title", "About", "pageId", "about"))))
      .get("privacy-policy", ctx -> ctx.render(handlebarsTemplate("privacy.html", ImmutableMap.of("title", "Privacy Policy", "pageId", "privacy"))))
      .get("", ctx -> ctx.render(handlebarsTemplate("terms-of-service.html", ImmutableMap.of("title", "Terms Of Service", "pageId", "term"))))
      .fileSystem("public", f -> f.files());
  }
}
