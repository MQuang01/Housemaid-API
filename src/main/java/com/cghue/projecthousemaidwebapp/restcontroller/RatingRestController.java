package com.cghue.projecthousemaidwebapp.restcontroller;

import com.cghue.projecthousemaidwebapp.domain.dto.res.RatingCategoryResDto;
import com.cghue.projecthousemaidwebapp.service.IRatingCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@AllArgsConstructor
public class RatingRestController {
    protected final IRatingCategoryService ratingCategoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<RatingCategoryResDto>> getAllRatingCategories() {
        return new ResponseEntity<>(ratingCategoryService.getAllRatingCategories(), HttpStatus.OK);
    }
}
