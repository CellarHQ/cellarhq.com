package com.cellarhq.domain

import com.cellarhq.generated.tables.records.OrganizationRecord
import com.cellarhq.jooq.annotations.Sanitize
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
class Organization extends com.cellarhq.generated.tables.pojos.Organization {

  //todo: remove when we get to groovy 2.4.1
  @SuppressWarnings(['UnnecessaryOverridingMethod', 'UnnecessaryGetter'])
  @Override
  @NotEmpty
  @Column(name = 'name')
  String getName() {
    return super.getName()
  }

  void setSlug(String slug) {
    super.setSlug(new Slugify().slugify(slug))
  }

  boolean getEditable() {
    !locked
  }
}
