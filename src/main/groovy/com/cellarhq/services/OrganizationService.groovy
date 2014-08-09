package com.cellarhq.services

import com.cellarhq.dao.OrganizationDAO
import com.cellarhq.domain.Organization
import com.google.inject.Inject
import groovy.transform.CompileStatic

@CompileStatic
class OrganizationService {

    private final OrganizationDAO organizationDAO

    @Inject
    OrganizationService(OrganizationDAO organizationDAO) {
        this.organizationDAO = organizationDAO
    }

    Organization save(Organization organization) {
        organizationDAO.save(organization)
    }

    Organization get(Long id) {
        organizationDAO.find(id)
    }

    Iterable<Organization> all() {
        organizationDAO.findAll()
    }

}
