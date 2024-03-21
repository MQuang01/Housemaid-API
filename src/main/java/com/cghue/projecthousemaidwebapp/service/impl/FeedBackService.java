package com.cghue.projecthousemaidwebapp.service.impl;

import com.cghue.projecthousemaidwebapp.domain.FeedBack;
import com.cghue.projecthousemaidwebapp.domain.dto.req.FeedBackReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.FeedBackResDto;
import com.cghue.projecthousemaidwebapp.domain.enumeration.EStatusOrder;
import com.cghue.projecthousemaidwebapp.repository.IFeedBackRepository;
import com.cghue.projecthousemaidwebapp.repository.IOrderRepository;
import com.cghue.projecthousemaidwebapp.repository.IRatingCategoryRepository;
import com.cghue.projecthousemaidwebapp.repository.IUserRepository;
import com.cghue.projecthousemaidwebapp.service.IFeedBackService;
import com.cghue.projecthousemaidwebapp.service.IRatingCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class FeedBackService implements IFeedBackService {
    private final IFeedBackRepository iFeedBackRepository;
    private final IUserRepository iUserRepository;
    private final IOrderRepository iOrderRepository;
    private final IRatingCategoryService ratingCategoryService;
    private final IRatingCategoryRepository iRatingCategoryRepository;


    @Override
    public List<FeedBackResDto> getAllFeedBacks() {
        return iFeedBackRepository
                .findAll()
                .stream()
                .map(FeedBack::toResDto)
                .toList();
    }

    @Override
    @Transactional
    public FeedBackResDto saveFeedBack(FeedBackReqDto feedBackReqDto) {
        FeedBack feedBack = new FeedBack();
        try {
            feedBack.setUser(iUserRepository.findById(feedBackReqDto.getUserId()).get());
            feedBack.setDescription(feedBackReqDto.getDescription());
            feedBack.setPercent(feedBackReqDto.getPercent());
            feedBack.setOrder(iOrderRepository.findById(feedBackReqDto.getOrderId()).get());
            // sau khi save feedback thì tính rating cho cate và employee
            if(feedBack.getOrder().getStatusOrder().equals(EStatusOrder.COMPLETE)) {
                feedBack = iFeedBackRepository.save(feedBack);
                ratingCategoryService.calculateRatingOrder(feedBack.getOrder().getCategory().getId());
            }
            return feedBack.toResDto();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateFeedBack(Long idFeedback, Long userId, String description, Integer percent) {
        FeedBack feedBack = iFeedBackRepository.findById(idFeedback).get();
        try {
            if (userId == null) {
                throw new IllegalArgumentException("Đăng nhập để cập nhật phản hồi.");
            }

            // Kiểm tra quyền hạn của người dùng  || iUserRepository.findById(userId).getRole().equals("ADMIN")
            if (!Objects.equals(userId, feedBack.getUser().getId())) {
                throw new IllegalArgumentException("Chỉ người dùng phản hồi này mới được chỉnh sửa phản hồi.");
            }

            // Cập nhật thông tin phản hồi nếu được cung cấp
            if (description != null) {
                feedBack.setDescription(description);
            }
            if (percent != null) {
                feedBack.setPercent(Float.valueOf(percent));
            }
            iFeedBackRepository.save(feedBack);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteFeedBack(Long id, Long userId) {
        try {
            if (userId == null) {
                throw new IllegalArgumentException("Đăng nhập để xóa phân hồi.");
            }

            if (Objects.equals(userId, iFeedBackRepository.findById(id).get().getUser().getId())) {
                iFeedBackRepository.deleteById(id);
            }else {
                throw new IllegalArgumentException("Chỉ người dùng phản hồi này được xóa.");
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
