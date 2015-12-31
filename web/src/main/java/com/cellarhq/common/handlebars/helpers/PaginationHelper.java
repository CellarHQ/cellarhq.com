package com.cellarhq.common.handlebars.helpers;

import com.github.jknack.handlebars.Options;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.netty.handler.codec.http.QueryStringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.handlebars.NamedHelper;
import ratpack.http.Request;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PaginationHelper implements NamedHelper<Request> {

  public static final String HELPER_NAME = "pagination";
  public static final String PAGE_QUERY_PARAM = "page";
  private static final Logger logger = LoggerFactory.getLogger(PaginationHelper.class);

  @Override
  public CharSequence apply(Request context, Options options) throws IOException {
    Map<String, Object> paginationInfoMap;

    try {
      int currentPageNumber = options.param(0, 1);
      int totalPageCount = options.param(1, 1);
      int pageGroupCount = options.param(2, 10);

      PagingStrategy strategy = new BasicPagingStrategy(currentPageNumber, totalPageCount, pageGroupCount);

      paginationInfoMap = makePaginationInfoMap(context, strategy);
    } catch (Exception e) {
      logger.debug(e.getMessage());
      paginationInfoMap = Maps.newHashMap();
    }

    return options.fn(paginationInfoMap);
  }

  public Map<String, Object> makePaginationInfoMap(Request request, PagingStrategy strategy) {
    Map<String, Object> infoMap = Maps.newHashMap();
    List<Map> pageList = Lists.newArrayList();

    int displayedLastPage = strategy.getLastDisplayedPageIndex();
    int currentPage = strategy.getCurrentPageNumber();

    for (int i = strategy.getFirstPageIndex(); i <= displayedLastPage; i++) {
      Map<String, Object> numberMap = Maps.newHashMap();

      numberMap.put("pageUri", getUriWithPage(request, i));
      numberMap.put("page", String.valueOf(i));
      numberMap.put("isCurrent", (i == currentPage));

      pageList.add(numberMap);
    }

    infoMap.put("canGoPrevious", canGoPrevious(currentPage));
    infoMap.put("canGoNext", canGoNext(currentPage, strategy.getTotalPageCount()));
    infoMap.put("previousUri", getUriWithPage(request, strategy.getPreviousIndex()));
    infoMap.put("nextUri", getUriWithPage(request, strategy.getNextIndex()));
    infoMap.put("pages", pageList);

    return infoMap;
  }

  public boolean canGoPrevious(int currentPage) {
    return currentPage > 1;
  }

  public boolean canGoNext(int currentPage, int totalPages) {
    return currentPage != totalPages;
  }

  public QueryStringEncoder makeUriEncoder(Request request) {
    QueryStringEncoder encoder = new QueryStringEncoder(request.getPath());

    for (Map.Entry<String, List<String>> pairs : request.getQueryParams().getAll().entrySet()) {
      pairs.getValue().stream().filter(value -> !pairs.getKey().equals(PAGE_QUERY_PARAM)).forEach(value -> {
        encoder.addParam(pairs.getKey(), value);
      });
    }

    return encoder;
  }

  public String getUriWithPage(Request request, int page) {
    QueryStringEncoder encoder = makeUriEncoder(request);
    encoder.addParam(PAGE_QUERY_PARAM, String.valueOf(page));
    return encoder.toString();
  }

  @Override
  public String getName() {
    return HELPER_NAME;
  }

  public interface PagingStrategy {

    public int getCurrentPageNumber();

    public int getTotalPageCount();

    public int getPageGroupCount();

    public int getFirstPageIndex();

    public int getLastPageIndex();

    public int getNextIndex();

    public int getPreviousIndex();

    public int getLastDisplayedPageIndex();
  }

  public static class BasicPagingStrategy implements PagingStrategy {

    private final int currentPageNumber;
    private final int totalPageCount;
    private final int pageGroupCount;

    public BasicPagingStrategy(int currentPageNumber,
                               int totalPageCount,
                               int pageGroupCount) {

      this.currentPageNumber = currentPageNumber;
      this.totalPageCount = totalPageCount;
      this.pageGroupCount = pageGroupCount;
    }

    public int getCurrentPageNumber() {
      return currentPageNumber;
    }

    public int getTotalPageCount() {
      return totalPageCount;
    }

    public int getPageGroupCount() {
      return pageGroupCount;
    }

    public int getFirstPageIndex() {
      return (((currentPageNumber - 1) / pageGroupCount)) * pageGroupCount + 1;
    }

    public int getLastPageIndex() {
      return (((currentPageNumber - 1) / pageGroupCount)) * pageGroupCount + pageGroupCount;
    }

    public int getNextIndex() {
      return getLastPageIndex() + 1;
    }

    public int getPreviousIndex() {
      return getLastPageIndex() - pageGroupCount;
    }

    public int getLastDisplayedPageIndex() {
      int lastPageIdx = getLastPageIndex();
      return totalPageCount < lastPageIdx ? totalPageCount : lastPageIdx;
    }
  }
}
