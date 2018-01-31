package com.aysoft.onionproject.infrastructure.seedwork.binding.error;

import java.io.Serializable;

/*
 * Error codes:
 *
 * 1000	- Undefined
 * 1001	- Invalid value
 * 1002	- Invalid format
 * 1003	- Inconsistency operation
 * 1004	- Invalid reference
 * 1005	- Absent field
 *
 * 2000	- Unauthorized
 * 2001	- Forbidden
 */
public class Error implements Serializable {
    private static final long serialVersionUID = 5973471280896101028L;
    private int code;
    private String detail;

    public Error() {}

    public Error(int code, String detail) {
        this.code = code;
        this.detail = detail;
    }

    static public Error err(int code, String detail) {
        return new Error(code, detail);
    }

    //<editor-fold desc="Encapsulation">
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    //</editor-fold>
}
