package com.cellarhq.domain

import com.github.slugify.Slugify
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors
import org.hibernate.validator.constraints.NotEmpty

import javax.persistence.Column

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
