package ua.kpi.architecture.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "marks")
@IdClass(MarkId.class)
public class Mark {
    @Id
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;

    @Column(name = "mark")
    private Integer score;
    private Integer semester;
}
