package com.cellarhq.handlebars.helpers;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Options;
import ratpack.handlebars.NamedHelper;
import ratpack.http.Request;
import ratpack.util.MultiValueMap;

import java.io.IOException;
import java.util.Formatter;

/**
 * Creates a link to help with data table sorting.
 *
 * In order to use this, you will need to pass in the Ratpack Request object in as the context, along with the
 * following options:
 *
 * <ul>
 *     <li><strong>field</strong>: The field that will be used for sorting</li>
 *     <li><strong>title</strong>: The title of the link</li>
 * </ul>
 *
 * <code>
 *     {{tableSort request field="my.field" title="My title"}}
 *
 *     // produces:
 *
 *     <a href="/foo/bar?field=my.field&order=desc" title="My title"><i class="fa fa-sort-alpha-desc"></i> My title</a>
 * </code>
 *
 * The helper will automatically detect whether or not the link orders need to be reversed. Furthermore, a sorting
 * icon will only be displayed for the field that is currently being sorted to reduce the amount of noise on the page.
 */
public class DataTableSortingHelper implements NamedHelper<Request> {

    private static final String ORDER_ASC = "asc";
    private static final String ORDER_DESC = "desc";
    private static final String DEFAULT_ORDER = ORDER_DESC;

    public static final String HELPER_NAME = "tableSort";

    @Override
    public CharSequence apply(Request context, Options options) throws IOException {
        String field = options.hash("field").toString();
        String title = options.hash("title").toString();

        validate(title, field);

        MultiValueMap<String, String> queryParams = context.getQueryParams();
        String order = makeOrder(
                queryParams.get("order"),
                queryParams.get("field"),
                field);
        String icon = makeIcon(order, field.equals(queryParams.get("field")));

        Formatter fm = new Formatter(new StringBuilder());
        fm.format("<a href=\"/%1$s?field=%2$s&order=%3$s\" title=\"%5$s\">%5$s %4$s</a>",
                context.getPath(),
                field,
                order,
                icon,
                title);

        return new Handlebars.SafeString(fm.toString());
    }

    private static void validate(String title, String field) {
        assert title != null : "Title option was not provided";
        assert field != null : "Field option was not provided";
    }

    private static String makeOrder(String currentOrder, String currentField, String field) {
        if (currentOrder == null || !currentField.equals(field)) {
            return DEFAULT_ORDER;
        }
        return reverseOrder(normalizeOrder(currentOrder));
    }

    private static String normalizeOrder(String order) {
        String normalized = order;
        if (!ORDER_DESC.equals(order) && !ORDER_ASC.equals(order)) {
            normalized = DEFAULT_ORDER;
        }
        return normalized;
    }

    private static String reverseOrder(String order) {
        if (order.equals(ORDER_ASC)) {
            return ORDER_DESC;
        } else {
            return ORDER_ASC;
        }
    }

    private static String makeIcon(String order, boolean isCurrentField) {
        if (isCurrentField) {
            if (order.equals(ORDER_ASC)) {
                return "<i class=\"fa fa-sort-alpha-desc\"></i>";
            } else {
                return "<i class=\"fa fa-sort-alpha-asc\"></i>";
            }
        } else {
            return "";
        }
    }

    @Override
    public String getName() {
        return HELPER_NAME;
    }
}
