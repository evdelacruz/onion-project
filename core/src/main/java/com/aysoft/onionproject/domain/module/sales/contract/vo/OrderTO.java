package com.aysoft.onionproject.domain.module.sales.contract.vo;

import com.aysoft.onionproject.infrastructure.seedwork.binding.vo.TransferObject;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.*;
import java.time.LocalDate;

public class OrderTO extends TransferObject {
    private static final long serialVersionUID = 5426232815219965497L;
    private int id;
    private transient Integer state;
    private LocalDate date;
    private String referenceId;
    private int priority;

    public OrderTO() {}

    //<editor-fold desc="Encapsulation">
    public int getId() {
        return id;
    }

    public OrderTO setId(int id) {
        this.id = id;
        return this;
    }

    @Null
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Null
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @NotBlank
    @Size(min=1, max=32)
    @Pattern(regexp="(\\p{javaLetterOrDigit})+")
    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    @Min(1)
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    //</editor-fold>
}
