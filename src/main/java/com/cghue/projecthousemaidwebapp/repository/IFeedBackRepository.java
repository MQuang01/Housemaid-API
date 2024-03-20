package com.cghue.projecthousemaidwebapp.repository;

import com.cghue.projecthousemaidwebapp.domain.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFeedBackRepository extends JpaRepository<FeedBack, Long> {
}
