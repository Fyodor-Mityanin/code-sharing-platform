package org.demo.codesharingplatform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
@Table(name = "code")
public class Code {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "code")
    private String code;

    @Column(name = "time")
    private int time = 0;

    @Column(name = "views")
    private int views = 0;

    @JsonIgnore
    @Column(name = "is_time_restrict")
    private boolean timeRestrict = false;

    @JsonIgnore
    @Column(name = "is_view_restrict")
    private boolean viewRestrict = false;


    public Code() {
    }

    public Code(LocalDateTime date, String code, int time, int views) {
        this.date = date;
        this.code = code;
        this.time = time;
        this.views = views;
    }

    public UUID getId() {
        return id;
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void decrementViews() {
        this.views--;
    }

    public boolean isTimeRestrict() {
        return timeRestrict;
    }

    public void setTimeRestrict(boolean timeRestrict) {
        this.timeRestrict = timeRestrict;
    }

    public boolean isViewRestrict() {
        return viewRestrict;
    }

    public void setViewRestrict(boolean setViewRestrict) {
        this.viewRestrict = setViewRestrict;
    }

    public int getSecondsLeft() {
        int secondsLeft = (int) ChronoUnit.SECONDS.between(LocalDateTime.now(), getDate().plusSeconds(time));
        return Math.max(secondsLeft, 0);
    }
}
