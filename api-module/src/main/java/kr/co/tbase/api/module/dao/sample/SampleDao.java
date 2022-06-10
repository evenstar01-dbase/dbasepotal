package kr.co.tbase.api.module.dao.sample;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.tbase.api.module.dao.BaseDao;
import kr.co.tbase.api.module.mapper.sample.SampleMapper;
import kr.co.tbase.api.module.dao.repository.sample.SampleRepository;
import kr.co.tbase.api.module.dto.sample.SampleDto;
import kr.co.tbase.api.module.entity.sample.SampleEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Repository
public class SampleDao extends BaseDao<SampleRepository> {
    public List<SampleDto> selectJpaSamples() {
        return SampleMapper.INSTANCE.toSampleDTOs(super.jpa().findAll());
    }

    public SampleEntity selectJpaSample(Long memberId) {
        return super.jpa().findById(memberId).get();
    }

    public Long createJpaSample(SampleDto request) {
        SampleEntity entity = SampleMapper.INSTANCE.toEntity(request);
        entity = super.jpa().saveAndFlush(entity);
        return entity.getMemberId();
    }

    public void deleteJpaSample(Long memberId) {
        SampleEntity entity = new SampleEntity();
        entity.setMemberId(memberId);
        super.jpa().delete(entity);
    }

    public List<SampleDto> getSampleList() {
        return super.mybatis().selectList("mappers.sample.SampleMapper.selectList");
    }

    public Long createMybatisSample(SampleDto data) {
        super.mybatis().insert("mappers.sample.SampleMapper.insertSample", data);
        return data.getMemberId();
    }

    public void deleteMybatisSample(Long memberId) {
        super.mybatis().delete("mappers.sample.SampleMapper.deleteSample", memberId);
    }

    public void updateMybatisSample(SampleDto sample) {
        super.mybatis().update("mappers.sample.SampleMapper.updateSample", sample);
    }
}
