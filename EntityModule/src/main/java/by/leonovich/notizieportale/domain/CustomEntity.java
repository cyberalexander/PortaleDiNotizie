package by.leonovich.notizieportale.domain;

import by.leonovich.notizieportale.domain.util.StatusEnum;

import javax.persistence.*;

/**
 * Created by alexanderleonovich on 22.05.15.
 */
public class CustomEntity {


    public CustomEntity() {
    }

    @Column(name = "F_STATUS")
    @Enumerated(EnumType.STRING)
    protected StatusEnum status;

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
