package com.cellarhq.common.handlebars.helpers;

import com.github.jknack.handlebars.Options;
import ratpack.handlebars.NamedHelper;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Simple helper for selecting an option in a select form element.
 *
 * <code>
 *     {{selectedOption object "getterMethod" "expectedValue"}}
 * </code>
 */
public class SelectedOptionHelper implements NamedHelper<Object> {

    public static final String HELPER_NAME = "selectedOption";

    @Override
    public CharSequence apply(Object context, Options options) throws IOException {
        String getter = options.param(0);
        String expected = options.param(1);

        try {
            Method method = context.getClass().getMethod(getter);
            method.setAccessible(true);
            Object actual = method.invoke(context);

            if (actual != null && expected.equals(actual)) {
                return "selected";
            }
        } catch (NoSuchMethodException e) {
            return "";
        } catch (InvocationTargetException e) {
            return "";
        } catch (IllegalAccessException e) {
            return "";
        }

        return "";
    }

    @Override
    public String getName() {
        return HELPER_NAME;
    }
}
