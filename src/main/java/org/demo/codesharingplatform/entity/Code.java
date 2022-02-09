package org.demo.codesharingplatform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Duration;
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
    private Duration time = Duration.ZERO;

    @Column(name = "views")
    private int views = 0;

    @JsonIgnore
    @Column(name = "is_secret")
    private boolean isSecret = false;


    public Code() {
    }

    public Code(LocalDateTime date, String code) {
        this.date = date;
        this.code = code;
    }

    public Code(LocalDateTime date, String code, Duration time, int views) {
        this.date = date;
        this.code = code;
        this.time = time;
        this.views = views;
    }

    public Code(LocalDateTime date, String code, Duration time) {
        this.date = date;
        this.code = code;
        this.time = time;
    }

    public Code(LocalDateTime date, String code, int views) {
        this.date = date;
        this.code = code;
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

    public Duration getTime() {
        return time;
    }

    public void setTime(Duration time) {
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

    public boolean isSecret() {
        return isSecret;
    }

    public void setSecret(boolean secret) {
        isSecret = secret;
    }

    public LocalDateTime getEstimateTime() {
        return getDate().plusSeconds(getTime().getSeconds());
    }

    public long getSecondsLeft() {
        return ChronoUnit.SECONDS.between(LocalDateTime.now(), getDate().plusSeconds(getTime().getSeconds()));
    }
}
