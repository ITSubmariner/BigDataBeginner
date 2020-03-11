package com.pet.bigdatabegginer.dao;

import com.pet.bigdatabegginer.domain.Limits;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LimitsDaoTest {
    @Autowired
    private LimitsDao limitsDao;

    @Test
    void getMin() {
        Limits min = limitsDao.getMin();
        assertNotNull(min);
    }

    @Test
    void getMax() {
        Limits min = limitsDao.getMax();
        assertNotNull(min);
    }
}