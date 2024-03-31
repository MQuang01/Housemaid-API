package com.cghue.projecthousemaidwebapp.domain.dto.req;

import com.cghue.projecthousemaidwebapp.domain.dto.res.CategoryResDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobReqDto {
    private String name;

    private Double price;

    private Integer timeApprox;

    private String typeJob;

    private Long categoryId;
}
