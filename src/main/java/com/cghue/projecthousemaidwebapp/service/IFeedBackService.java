package com.cghue.projecthousemaidwebapp.service;

import com.cghue.projecthousemaidwebapp.domain.FeedBack;
import com.cghue.projecthousemaidwebapp.domain.dto.req.FeedBackReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.FeedBackResDto;

import java.util.List;

public interface IFeedBackService {
    List<FeedBackResDto> getAllFeedBacks();
    FeedBackResDto saveFeedBack(FeedBackReqDto feedBackReqDto);
    boolean updateFeedBack(Long idFeedback, Long userId, String description, Integer percent);
    boolean deleteFeedBack(Long id, Long userId);
}
