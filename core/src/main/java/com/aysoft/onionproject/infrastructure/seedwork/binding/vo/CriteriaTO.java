package com.aysoft.onionproject.infrastructure.seedwork.binding.vo;

public class CriteriaTO extends TransferObject {
    private static final long serialVersionUID = -631799705757521671L;
    private int page = 0;
    private int size = 20;

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
