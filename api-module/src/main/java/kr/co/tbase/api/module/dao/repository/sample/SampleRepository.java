package kr.co.tbase.api.module.dao.repository.sample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.tbase.api.module.entity.sample.SampleEntity;

@Repository
public interface SampleRepository extends JpaRepository<SampleEntity, Long> {
}
