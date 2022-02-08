package org.demo.codesharingplatform.dtos;


import java.time.LocalDateTime;

public class CodeDTO {

    private String date;

    private String code;

    public CodeDTO(String date, String code) {
        this.date = date;
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
