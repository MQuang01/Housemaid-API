package com.cghue.projecthousemaidwebapp.domain;

import com.cghue.projecthousemaidwebapp.domain.dto.res.JobResDto;
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

    @ManyToOne
    private Category category;

    public JobResDto toResDto(){
        return new JobResDto(id, name, urlImage, price, category.toResDto());
    }
}
