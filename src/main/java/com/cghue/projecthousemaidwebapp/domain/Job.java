package com.cghue.projecthousemaidwebapp.domain;

import com.cghue.projecthousemaidwebapp.domain.dto.res.JobResDto;
import com.cghue.projecthousemaidwebapp.domain.enumeration.ETypeJob;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "jobs")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String urlImage;

    private Double price;

    private Float timeApprox;

    @Enumerated(EnumType.STRING)
    private ETypeJob typeJob;

    @ManyToOne
    private Category category;

    public JobResDto toResDto(){
        return new JobResDto(id, name, urlImage, price, timeApprox, category.toResDto());
    }
}
