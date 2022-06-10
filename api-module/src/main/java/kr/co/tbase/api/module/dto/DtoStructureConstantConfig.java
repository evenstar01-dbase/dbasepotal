package kr.co.tbase.api.module.dto;
import kr.co.tbase.api.module.dto.sample.SampleDto;
import kr.co.tbase.common.module.annotation.MakeDtoStructure;

/**
 * json 형태의 상수를 자동으로 생성할 Dto클래스를 targetClasses에 넣어주세요.
 * json으로 만들 각 Dto에는 {@io.swagger.v3.oas.annotations.media.Schema}가 description필드와 함께 선언되어 있어야 합니다.
 */
@MakeDtoStructure(
        targetClasses = {
                SampleDto.class,
        },
        structureDetailContainer = "DtoStructureConstant"
)
public class DtoStructureConstantConfig {
}