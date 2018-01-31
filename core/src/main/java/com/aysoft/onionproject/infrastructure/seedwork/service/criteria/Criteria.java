package com.aysoft.onionproject.infrastructure.seedwork.service.criteria;

import com.aysoft.onionproject.infrastructure.seedwork.service.repository.domain.AbstractEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import java.util.List;

public abstract class Criteria<E extends AbstractEntity> extends AbstractSpecification<E> {
    private int page;
    private int size;

    public abstract String sortProperty();

    public PageRequest buildPageRequest() {
        Direction direction = this.asc() ? Direction.ASC : Direction.DESC;
        List<String> properties = List.of(this.sortProperty());
        return new PageRequest(page, size, new Sort(direction, properties));
    }

    protected boolean asc() {
        return false;
    }

    //<editor-fold desc="Encapsulation">
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    //</editor-fold>
}
