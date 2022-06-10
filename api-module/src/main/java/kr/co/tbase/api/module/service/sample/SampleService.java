package kr.co.tbase.api.module.service.sample;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.tbase.api.module.dao.sample.SampleDao;
import kr.co.tbase.api.module.dto.sample.SampleDto;
import kr.co.tbase.api.module.entity.sample.SampleEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class SampleService {
    private final SampleDao sampleDao;

    @Transactional
    public Long saveJpa(SampleDto request) {
        return sampleDao.createJpaSample(request);
    }

    @Transactional
    public void deleteJpa(Long id) {
        sampleDao.deleteJpaSample(id);
    }

    @Transactional
    public void updateJpa(SampleDto sample) {
        SampleEntity data = sampleDao.selectJpaSample(sample.getMemberId());
        data.setName(sample.getName());
    }

    @Transactional(readOnly = true)
    public List<SampleDto> getJpa() {
        return  sampleDao.selectJpaSamples();
    }

    @Transactional(readOnly = true)
    public List<SampleDto> getMybatis() {
        return sampleDao.getSampleList();
    }

    @Transactional
    public Long saveMybatis(SampleDto data) {
        return sampleDao.createMybatisSample(data);
    }

    @Transactional
    public void deleteMybatis(Long id) {
        sampleDao.deleteMybatisSample(id);
    }

    @Transactional
    public void updateMybatis(SampleDto sample) {
        sampleDao.updateMybatisSample(sample);
    }
}
