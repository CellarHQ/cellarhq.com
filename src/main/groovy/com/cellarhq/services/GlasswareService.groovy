package com.cellarhq.services

import com.cellarhq.dao.GlasswareDAO
import com.cellarhq.domain.Glassware
import com.google.inject.Inject
import groovy.transform.CompileStatic

@CompileStatic
class GlasswareService {

    private final GlasswareDAO glasswareDAO

    @Inject
    GlasswareService(GlasswareDAO glasswareDAO) {
        this.glasswareDAO = glasswareDAO
    }

    Glassware save(Glassware glassware) {
        glasswareDAO.save(glassware)
    }

    Glassware get(Long id) {
        glasswareDAO.find(id)
    }

    Iterable<Glassware> all() {
        glasswareDAO.findAll()
    }

}
