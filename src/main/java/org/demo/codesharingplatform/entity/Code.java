package org.demo.codesharingplatform.entity;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

@Entity
@Table(name = "code")
public class Code {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "code")
    private String code;

    @Column(name = "time_to_live")
    private Duration timeToLive;

    @Column(name = "views_left")
    private Integer viewsLeft;


    public Code() {
    }

    public Code(Long id, LocalDateTime date, String code) {
        this.id = id;
        this.date = date;
        this.code = code;
    }

    public Code(Long id, LocalDateTime date, String code, Duration secondsToLive, Integer viewsLeft) {
        this.id = id;
        this.date = date;
        this.code = code;
        this.timeToLive = secondsToLive;
        this.viewsLeft = viewsLeft;
    }

    public Code(Long id, LocalDateTime date, String code, Duration secondsToLive) {
        this.id = id;
        this.date = date;
        this.code = code;
        this.timeToLive = secondsToLive;
    }

    public Code(Long id, LocalDateTime date, String code, Integer viewsLeft) {
        this.id = id;
        this.date = date;
        this.code = code;
        this.viewsLeft = viewsLeft;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Duration getSecondsToLive() {
        return timeToLive;
    }

    public void setSecondsToLive(Duration secondsToLive) {
        this.timeToLive = secondsToLive;
    }

    public Integer getViewsLeft() {
        return viewsLeft;
    }

    public void setViewsLeft(Integer viewsLeft) {
        this.viewsLeft = viewsLeft;
    }

    public void decrementViewsLeft(Integer viewsLeft) {
        this.viewsLeft--;
    }
}
