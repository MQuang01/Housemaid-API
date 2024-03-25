package com.cghue.projecthousemaidwebapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rating_employees")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RatingEmployee {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @ManyToOne
    private User user;

    private Float percent;
}
