package com.cghue.projecthousemaidwebapp.restcontroller;

import com.cghue.projecthousemaidwebapp.domain.dto.req.FeedBackReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.FeedBackResDto;
import com.cghue.projecthousemaidwebapp.service.IFeedBackService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
@AllArgsConstructor
public class FeedBackRestController {
    protected final IFeedBackService feedBackService;

    @GetMapping
    public ResponseEntity<List<FeedBackResDto>> getAllFeedBacks() {
        return ResponseEntity.ok(feedBackService.getAllFeedBacks());
    }

    @PostMapping
    public ResponseEntity<FeedBackResDto> createdFeedBack(@RequestBody FeedBackReqDto feedBack) {
        return ResponseEntity.ok(feedBackService.saveFeedBack(feedBack));
    }
}
