package com.cellarhq.webapp.handlers

import com.cellarhq.domain.Availability
import com.cellarhq.domain.Drink
import ratpack.handling.Context
import ratpack.handling.Handler

import static ratpack.handlebars.Template.handlebarsTemplate

class AddBeersHtmlHandler implements Handler {
  @Override
  void handle(Context ctx) throws Exception {
    ctx.render handlebarsTemplate('beer/new-beer.html', [
      org         : null,
      drink       : new Drink(),
      title       : 'CellarHQ : Add New Beer',
      pageId      : 'beers.new',
      availability: Availability.toHandlebars()
    ])
  }
}
