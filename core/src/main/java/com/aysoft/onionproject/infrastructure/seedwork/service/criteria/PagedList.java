package com.aysoft.onionproject.infrastructure.seedwork.service.criteria;

import static java.util.stream.Collectors.toList;
import java.util.List;
import java.util.function.Function;

public class PagedList<E> {
    private List<E> elements;
    private int page;
    private int totalPages;
    private long totalElements;

    public PagedList() {}

    public PagedList(List<E> elements, int page, int totalPages, long totalElements) {
        this.elements = elements;
        this.page = page;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public <S> PagedList<S> map(Function<? super E, ? extends S> converter) {
        return new PagedList<>(elements.stream().map(converter::apply).collect(toList()), page, totalPages,  totalElements);
    }

    //<editor-fold desc="Encapsulation">
    public int getSize() {
        return this.getElements().size();
    }

    public List<E> getElements() {
        return null == elements ? elements = List.of() : elements;
    }

    public void setElements(List<E> elements) {
        this.elements = elements;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
    //</editor-fold>
}
