package kr.co.tbase.api.module.mapper.sample;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import kr.co.tbase.api.module.mapper.EntityMapper;
import kr.co.tbase.api.module.dto.sample.SampleDto;
import kr.co.tbase.api.module.entity.sample.SampleEntity;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface SampleMapper extends EntityMapper<SampleEntity, SampleDto> {
    SampleMapper INSTANCE = Mappers.getMapper(SampleMapper.class);
    default List<SampleDto> toSampleDTOs(List<SampleEntity> samples) {
        List<SampleDto> resultList = new ArrayList<>();
        if (null != samples) {
            for (SampleEntity sample : samples) {
                SampleDto result = new SampleDto();
                result.setMemberId(sample.getMemberId());
                result.setName(sample.getName());
                resultList.add(result);
            }
        }

        return resultList;
    }

//    List<SampleDto> toSampleDTOs(List<SampleEntity> samples);
//    SampleDto toSampleDTO(SampleEntity sample);
}
