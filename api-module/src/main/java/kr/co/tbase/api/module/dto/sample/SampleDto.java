package kr.co.tbase.api.module.dto.sample;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "SampleDto")
public class SampleDto {
    @Schema(description = "Sample 회원번호")
    private Long memberId;
    @Schema(description = "Sample 회원명")
    private String name;
}
