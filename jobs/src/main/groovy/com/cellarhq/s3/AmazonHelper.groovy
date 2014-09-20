package com.cellarhq.s3

import com.amazonaws.services.simpledb.model.Attribute
import groovy.util.logging.Slf4j
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeParser
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.DateTimeFormatterBuilder
import org.joda.time.format.ISODateTimeFormat

@Slf4j
class AmazonHelper {

    static final String attrDescription0 = "description0"
    static final String attrDescription2 = "description2"
    static final String attrDescription3 = "description3"
    static final String attrDescription4 = "description4"

    DateTimeParser[] parsers = [
        DateTimeFormat.forPattern( "yyyy-MM" ).getParser(),
        DateTimeFormat.forPattern( "yyyy-MM-dd" ).getParser(),
        ISODateTimeFormat.dateTimeParser().getParser()
    ]

    DateTimeFormatter formatter = new DateTimeFormatterBuilder().append( null, parsers ).toFormatter()

    String getAttribute(List<Attribute> attributes, String name) {
        def attribute = attributes.find {
            name.equalsIgnoreCase(it.name)
        }
        return attribute?.value ?: ''
    }

    String buildDescription(List<Attribute> attributes) {
        String desc = getAttribute(attributes, attrDescription0)

        if (getAttribute(attributes, attrDescription2)) {
            desc += ' ' + getAttribute(attributes, attrDescription2)
        }

        if (getAttribute(attributes, attrDescription3)) {
            desc += ' ' + getAttribute(attributes, attrDescription3)
        }

        if (getAttribute(attributes, attrDescription4)) {
            desc += ' ' + getAttribute(attributes, attrDescription4)
        }

        return desc
    }

    Integer getNumberAttribute(List<Attribute> attributes, String name) {
        def attribute = attributes.find {
            name.equalsIgnoreCase(it.name)
        }

        String str = attribute?.value

        if (!str) return null

        try {
            return str.toInteger()
        } catch (NumberFormatException e) {
            return null
        }
    }

    BigDecimal getBigDecimalAttribute(List<Attribute> attributes, String name) {
        def attribute = attributes.find {
            name.equalsIgnoreCase(it.name)
        }

        String str = attribute?.value

        if (!str) return null

        try {
            return str.toBigDecimal()
        } catch (NumberFormatException e) {
            return null
        }
    }

    DateTime getDateAttribute(List<Attribute> attributes, String name) {
        def attribute = attributes.find {
            name.equalsIgnoreCase(it.name)
        }

        if (attribute?.value) {
            String dataString = attribute.value

            try {
                return formatter.parseDateTime(dataString)
            }  catch (IllegalArgumentException e) {
                println "Could not parse ${dataString}"
            }
        }

        return null
    }
}
