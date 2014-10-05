package com.cellarhq.jooq

import groovy.transform.CompileStatic
import ratpack.http.Request

@CompileStatic
class SortCommand {

    enum Order {
        DESC('desc'),
        ASC('asc')

        final String value

        Order(String value) {
            this.value = value
        }

        static Order fromString(String value) {
            return values().find { it.value == value } ?: DESC
        }
    }

    String field
    Order order

    static SortCommand fromRequest(Request request) {
        return new SortCommand().with { SortCommand self ->
            field = request.queryParams.get('field')
            order = Order.fromString(request.queryParams.get('order'))
            return self
        }
    }

    boolean isValid() {
        return field && order
    }
}
