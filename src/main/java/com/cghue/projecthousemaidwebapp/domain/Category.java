package com.cghue.projecthousemaidwebapp.domain;

import com.cghue.projecthousemaidwebapp.domain.dto.res.CategoryResDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.FileInfoResDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Job> jobs;

    @OneToOne
    private FileInfo fileInfo;

    public CategoryResDto toResDto() {
        if (fileInfo == null) {
            return new CategoryResDto(id, name, null, jobs.size());
        }
        return new CategoryResDto(id, name, fileInfo.toResDto(), jobs.size());
    }
}
