package com.gamul.gamul.repository;

import com.gamul.gamul.domain.entity.Bookmark;
import com.gamul.gamul.domain.entity.Market;
import com.gamul.gamul.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    @Query("select b from Bookmark b join fetch b.market where b.member = :member")
    List<Bookmark> findAllByMember(@Param("member") Member member);

    Optional<Bookmark> findByMemberAndMarket(Member member, Market market);

    boolean existsById(Long id);

    boolean existsByMemberAndMarket(Member member, Market market);
}
