package com.pet.bigdatabegginer.repository;

import com.pet.bigdatabegginer.domain.Limits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitsRepository extends JpaRepository<Limits, Long> {
    Limits findFirstByOrderByDateDesc();
}
