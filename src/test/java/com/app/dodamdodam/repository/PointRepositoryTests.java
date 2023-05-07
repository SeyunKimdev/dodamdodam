package com.app.dodamdodam.repository;

import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.repository.point.PointRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Rollback(false)
@Transactional
@Slf4j
public class PointRepositoryTests {
    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private MemberRepository memberRepository;

    /*포인트 목록 가져오기*/
    @Test
    public void findPointListByIdTest(){
        pointRepository.findPointByMemberId(2L).stream().map(Point::toString).forEach(log::info);
    }
}