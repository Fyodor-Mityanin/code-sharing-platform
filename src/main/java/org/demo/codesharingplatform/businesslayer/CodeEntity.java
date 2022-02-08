package org.demo.codesharingplatform.businesslayer;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "code")
public class CodeEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "code")
    private String code;

    public CodeEntity() {
    }

    public CodeEntity(Long id, LocalDateTime date, String code) {
        this.id = id;
        this.date = date;
        this.code = code;
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
}
