package com.cellarhq.handlebars.helpers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.github.jknack.handlebars.Options;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.handlebars.NamedHelper;

public class PaginationHelper implements NamedHelper<Object> {

    private static final Logger logger = LoggerFactory.getLogger(PaginationHelper.class);

    public static final String HELPER_NAME = "pagination";

    @Override
    public CharSequence apply(Object context, Options options) throws IOException {

        Map<String, Object> paginationInfoMap;

        try {
            int currentPageNumber = options.param(0, 1);
            int totalPageCount = options.param(1, 1);
            int pageGroupCount = options.param(2, 10);

            int firstPageIdx = (((currentPageNumber - 1) / pageGroupCount)) * pageGroupCount + 1;
            int lastPageIdx = (((currentPageNumber - 1) / pageGroupCount)) * pageGroupCount + pageGroupCount;

            int previousIdx = lastPageIdx - pageGroupCount;
            int nextIdx = lastPageIdx + 1;

            boolean canGoPrevious = firstPageIdx > 1 ? true : false;
            boolean canGoNext = totalPageCount > lastPageIdx ? true : false;

            int displayedLastPage = totalPageCount < lastPageIdx ? totalPageCount : lastPageIdx;

            paginationInfoMap = this.makePaginationInfoMap(canGoPrevious, canGoNext, currentPageNumber, firstPageIdx, displayedLastPage, previousIdx,
                    nextIdx);

        } catch (Exception e) {
            logger.debug(e.getMessage());
            paginationInfoMap = Maps.newHashMap();
        }

        return options.fn(paginationInfoMap);
    }

    private Map<String, Object> makePaginationInfoMap(boolean canGoPrevious, boolean canGoNext, int page, int firstPage, int displayedLastPage,
                                                      int previousIdx, int nextIdx) {

        Map<String, Object> paginationInfoMap = Maps.newHashMap();
        List<Map> pageList = Lists.newArrayList();

        for (int i = firstPage; i <= displayedLastPage; i++) {
            Map<String, Object> numberMap = Maps.newHashMap();
            numberMap.put("page", String.valueOf(i));
            numberMap.put("isCurrent", (i == page ? true : false));
            pageList.add(numberMap);
        }

        paginationInfoMap.put("canGoPrevious", canGoPrevious);
        paginationInfoMap.put("previousIdx", previousIdx);
        paginationInfoMap.put("pages", pageList);
        paginationInfoMap.put("canGoNext", canGoNext);
        paginationInfoMap.put("nextIdx", nextIdx);

        return paginationInfoMap;
    }

    @Override
    public String getName() {
        return HELPER_NAME;
    }
}
