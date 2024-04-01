package com.cghue.projecthousemaidwebapp.domain;

import com.cghue.projecthousemaidwebapp.domain.dto.res.JobListResDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.JobResDto;
import com.cghue.projecthousemaidwebapp.domain.enumeration.ETypeJob;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "jobs")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    private FileInfo fileInfo;

    private Double price;

    private Integer timeApprox;

    @Enumerated(EnumType.STRING)
    private ETypeJob typeJob;

    @ManyToOne
    private Category category;


    public JobResDto toResDto(){
        if (fileInfo == null) {
            return new JobResDto(id, name, null, price, timeApprox, category.toResDto());
        }
        return new JobResDto(id, name, fileInfo.toResDto(), price, timeApprox, category.toResDto());
    }
    public JobListResDto toListResDto() {
        return new JobListResDto(id, name, fileInfo.getFileUrl(), typeJob, price, timeApprox, category);
    }
}
