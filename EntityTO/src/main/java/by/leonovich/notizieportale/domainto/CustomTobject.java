package by.leonovich.notizieportale.domainto;

import by.leonovich.notizieportale.domain.enums.StatusEnum;

import java.io.Serializable;

/**
 * Created by alexanderleonovich on 20.06.15.
 */
public class CustomTobject implements Serializable {
    private static final long serialVersionUID = -834688050128923656L;

    protected StatusEnum status;

    public CustomTobject() {
    }

    public CustomTobject(StatusEnum status) {
        this.status = status;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
