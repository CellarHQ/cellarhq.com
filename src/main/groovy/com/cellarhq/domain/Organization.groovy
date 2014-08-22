package com.cellarhq.domain

import com.github.slugify.Slugify
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors

@CompileStatic
@InheritConstructors
class Organization  extends com.cellarhq.generated.tables.pojos.Organization {

    void setSlug(String slug) {
        super.slug = new Slugify().slugify(slug)
    }
}
