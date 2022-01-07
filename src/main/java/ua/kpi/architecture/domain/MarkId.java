package ua.kpi.architecture.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class MarkId implements Serializable {
    private Integer student;
    private Integer subject;
}
