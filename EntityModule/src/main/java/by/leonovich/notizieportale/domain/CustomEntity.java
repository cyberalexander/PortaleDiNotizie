package by.leonovich.notizieportale.domain;

import by.leonovich.notizieportale.domain.util.StatusEnum;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by alexanderleonovich on 22.05.15.
 */
@MappedSuperclass
public class CustomEntity implements Serializable {
    private static final long serialVersionUID = 4519960040846861L;

    @Column(name = "F_STATUS")
    @Enumerated(EnumType.STRING)
    protected StatusEnum status;

    public CustomEntity() {
    }

    public CustomEntity(StatusEnum status) {
    this.status = status;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
