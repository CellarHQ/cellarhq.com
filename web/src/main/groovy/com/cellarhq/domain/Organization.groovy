package com.cellarhq.domain

import com.cellarhq.annotations.Sanitize
import com.cellarhq.generated.tables.records.OrganizationRecord
import com.github.slugify.Slugify
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors
import org.hibernate.validator.constraints.NotEmpty

import javax.persistence.Column

@Sanitize(recordType = OrganizationRecord, fields = [
        'name',
        'description',
        'phone',
        'website',
        'address',
        'address2',
        'locality',
        'postalCode',
        'country',
        'region'
])
@CompileStatic
@InheritConstructors
class Organization  extends com.cellarhq.generated.tables.pojos.Organization {

    @Override
    @NotEmpty
    @Column(name = 'name')
    String getName() {
        return super.name
    }

    void setSlug(String slug) {
        super.slug = new Slugify().slugify(slug)
    }

    boolean getEditable() {
        !locked
    }
}
