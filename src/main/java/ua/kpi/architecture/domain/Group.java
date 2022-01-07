package ua.kpi.architecture.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "`group`")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Specialty specialty;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Faculty faculty;

    private Integer course;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
    private Set<Student> students;

    @OneToOne
    @JoinColumn(name = "curator")
    private Staff curator;
}
