package com.aysoft.onionproject.infrastructure.seedwork.binding.to;

import javax.validation.constraints.NotNull;

public class Entry<I,V> {
    private I id;
    private V val;

    public Entry() {}

    //<editor-fold desc="Encapsulation">
    @NotNull
    public I getId() {
        return id;
    }

    public void setId(I id) {
        this.id = id;
    }

    public V getVal() {
        return val;
    }

    public void setVal(V val) {
        this.val = val;
    }
    //</editor-fold>
}
