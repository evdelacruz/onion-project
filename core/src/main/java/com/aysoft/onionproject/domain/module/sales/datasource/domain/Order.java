package com.aysoft.onionproject.domain.module.sales.datasource.domain;

import com.aysoft.onionproject.infrastructure.seedwork.service.repository.domain.AbstractEntity;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="order", schema="public")
public class Order extends AbstractEntity {
    private static final long serialVersionUID = -7620587805910231845L;
    private int id;
    private OrderState state;
    private LocalDate date;
    private String referenceId;
    private int priority;

    public Order() {}

    //<editor-fold desc="Encapsulation">
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="pk_order")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fk_order_state", referencedColumnName="pk_order_state", nullable=false)
    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    @Column(name="date", nullable=false)
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Column(name="reference_id", unique=true, nullable=false, length=32)
    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    @Column(name="priority", nullable=false)
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    //</editor-fold>
}
