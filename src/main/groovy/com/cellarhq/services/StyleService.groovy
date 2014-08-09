package com.cellarhq.services

import com.cellarhq.dao.StyleDAO
import com.cellarhq.domain.Style
import com.google.inject.Inject
import groovy.transform.CompileStatic

@CompileStatic
class StyleService {

    private final StyleDAO styleDAO

    @Inject
    StyleService(StyleDAO styleDAO) {
        this.styleDAO = styleDAO
    }

    Style save(Style style) {
        styleDAO.save(style)
    }

    Style get(Long id) {
        styleDAO.find(id)
    }

    Iterable<Style> all() {
        styleDAO.findAll()
    }

}
