package com.cellarhq.handlebars.helpers;

import com.github.jknack.handlebars.Options;
import ratpack.handlebars.NamedHelper;

import java.io.IOException;

public class CdnHelper implements NamedHelper<String> {

    public static final String HELPER_NAME = "cdn";
    public static final Long VERSION = System.currentTimeMillis();

    @Override
    public CharSequence apply(String context, Options options) throws IOException {
        if (isProductionEnv()) {
            // TODO We need to compile our assets when building a production container so CF is refreshed
            //      automatically. Temporary and less-performant solution is to use a static version query param.
            //      each app startup will wind up putting things in CF.
            return "//cdn-www.cellarhq.com" + context + "?v=" + VERSION;
        }
        return context;
    }

    private boolean isProductionEnv() {
        String deployment = System.getenv("CHQ_DEPLOYMENT");
        return deployment != null && deployment.equals("production");
    }

    @Override
    public String getName() {
        return HELPER_NAME;
    }
}
