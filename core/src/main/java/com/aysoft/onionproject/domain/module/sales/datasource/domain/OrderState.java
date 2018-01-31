package com.aysoft.onionproject.domain.module.sales.datasource.domain;

import com.aysoft.onionproject.infrastructure.seedwork.service.repository.domain.AbstractEntity;
import javax.persistence.*;

@Entity
@Table(name="order_state", schema="public")
public class OrderState extends AbstractEntity {
    private static final long serialVersionUID = 2134849429388718491L;
    public static final OrderState CREATED = new OrderState(1);
    public static final OrderState INITIALIZED = new OrderState(2);
    public static final OrderState IN_PROGRESS = new OrderState(3);
    public static final OrderState PROCESSED = new OrderState(4);
    public static final OrderState UNKNOW = new OrderState(5);
    private int id;
    private String state;

    public OrderState() {}

    public OrderState(int id) {
        this.id = id;
    }

    //<editor-fold desc="Encapsulation">
    @Id
    @Column(name="pk_order_state")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="state", nullable=false, length=30)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    //</editor-fold>
}
