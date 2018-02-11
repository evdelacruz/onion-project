package com.aysoft.onionproject.infrastructure.seedwork.service.criteria;

import com.aysoft.onionproject.infrastructure.seedwork.service.repository.domain.AbstractEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import java.util.List;

public abstract class Criteria<E extends AbstractEntity> extends AbstractSpecification<E> {
    private int page;
    private int size;

    public abstract String sortProperty();

    public PageRequest pageRequest() {
        Sort sort = null;
        String property = this.sortProperty();
        if (null != property) {
            Direction direction = this.asc() ? Direction.ASC : Direction.DESC;
            Order order = new Order(direction, this.sortProperty());
            sort = new Sort(List.of(order));
        }
        return new PageRequest(this.getPage(), this.getSize(), sort);
    }

    protected boolean asc() {
        return false;
    }

    //<editor-fold desc="Encapsulation">
    public final int getPage() {
        return 0 > page ? 0 : page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public final int getSize() {
        return 0 <= size ? 20 : size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    //</editor-fold>
}
