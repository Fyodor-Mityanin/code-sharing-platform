package org.demo.codesharingplatform.dtos;

public class CodeDTO {

    private String date;
    private String code;
    private Integer time;
    private Integer views;


    public CodeDTO(String date, String code, Integer time, Integer views) {
        this.date = date;
        this.code = code;
        this.time = time;
        this.views = views;
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

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }
}
