package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaginatedList<T> {
    public static final Integer DEFAULT_PAGE_SIZE = 10;
    public List<T> list;
    public List<List<T>> listOfPages;
    public Integer pageSize = DEFAULT_PAGE_SIZE;
    public Integer currentPage = 0;

    public PaginatedList() {
    }

    public PaginatedList(List<T> list) {
        this.list = list;
        initPages();
    }

    public PaginatedList(List<T> list, int pageSize) {
        this.list = list;
        this.pageSize = pageSize;
        initPages();
    }

    public List<T> getPage(int pageNumber) {
        if (listOfPages == null ||
                pageNumber > listOfPages.size() ||
                pageNumber < 1) {
            return Collections.emptyList();
        }
        currentPage = pageNumber;
        List<T> page = listOfPages.get(--pageNumber);
        return page;
    }

    public void sortPage() {
    }

    public void setPage(List<T> list, int pageNumber) {
        List<List<T>> listOfPages = this.listOfPages;
        for (int i = 1; i <= numberOfPages(); i++) {
            if (i == pageNumber) {
                listOfPages.set(--pageNumber, list);
                break;
            }
        }
        this.listOfPages = listOfPages;
        restorePageToList();
    }

    public int numberOfPages() {
        if (listOfPages == null) {
            return 0;
        }
        return listOfPages.size();
    }

    public List<T> nextPage() {
        List<T> page = getPage(++currentPage);
        return page;
    }

    public List<T> previousPage() {
        List<T> page = getPage(--currentPage);
        return page;
    }

    public void initPages() {
        if (list == null || listOfPages != null) {
            return;
        }

        if (pageSize <= 0 || pageSize > list.size()) {
            pageSize = list.size();
        }

        Integer numOfPages = (int) Math.ceil((double) list.size() / (double) pageSize);
        listOfPages = new ArrayList<List<T>>(numOfPages);
        for (int pageNum = 0; pageNum < numOfPages; ) {
            int from = pageNum * pageSize;
            int to = Math.min(++pageNum * pageSize, list.size());
            listOfPages.add(list.subList(from, to));
        }
    }

    public void restorePageToList() {
        list.clear();
        for (List<T> listPage : listOfPages
        ) {
            for (T model : listPage
            ) {
                list.add(model);
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= 62; i++) {
            list.add(i);
        }

        PaginatedList<Integer> paginatedList = new PaginatedList<Integer>(list);
        while (true) {
            List<Integer> page = paginatedList.nextPage();
            if (page == null || page.isEmpty()) {
                break;
            }
            for (Integer value : page) {
                System.out.println(value);
            }
            System.out.println("------------");
        }
    }
}
