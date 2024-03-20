package com.cghue.projecthousemaidwebapp.service.impl;

import com.cghue.projecthousemaidwebapp.domain.FeedBack;
import com.cghue.projecthousemaidwebapp.domain.dto.req.FeedBackReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.FeedBackResDto;
import com.cghue.projecthousemaidwebapp.repository.IFeedBackRepository;
import com.cghue.projecthousemaidwebapp.repository.IOrderRepository;
import com.cghue.projecthousemaidwebapp.repository.IUserRepository;
import com.cghue.projecthousemaidwebapp.service.IFeedBackService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FeedBackService implements IFeedBackService {
    private final IFeedBackRepository iFeedBackRepository;
    private final IUserRepository iUserRepository;
    private final IOrderRepository iOrderRepository;

    @Override
    public List<FeedBackResDto> getAllFeedBacks() {
        return iFeedBackRepository
                .findAll()
                .stream()
                .map(FeedBack::toResDto)
                .toList();
    }

    @Override
    public boolean saveFeedBack(FeedBackReqDto feedBackReqDto) {
        FeedBack feedBack = new FeedBack();
        try {
            feedBack.setUser(iUserRepository.findById(feedBackReqDto.getUserId()).get());
            feedBack.setDescription(feedBackReqDto.getDescription());
            feedBack.setPercent(feedBackReqDto.getPercent());
            feedBack.setOrder(iOrderRepository.findById(feedBackReqDto.getOrderId()).get());
            iFeedBackRepository.save(feedBack);
            return true;
        } catch (Exception e) {
            return false;
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
                feedBack.setPercent(percent);
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
