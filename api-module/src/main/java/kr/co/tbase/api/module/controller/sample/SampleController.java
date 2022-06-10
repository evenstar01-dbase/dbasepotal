package kr.co.tbase.api.module.controller.sample;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import kr.co.tbase.api.module.dto.DtoStructureConstant;
import kr.co.tbase.api.module.dto.sample.SampleDto;
import kr.co.tbase.api.module.service.sample.SampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping({"/api/sample"})
@Api(tags = "Sample")
public class SampleController {
    private final SampleService sampleService;

    @PostMapping("/jpa")
    @Operation(summary = "Sample 데이터 생성 JPA")
    public ResponseEntity createJpaSample(@RequestBody SampleDto sample) throws Exception {
        Map<String, Object> result = new HashMap<>();
        result.put("id", sampleService.saveJpa(sample));
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "Sample 데이터 삭제 JPA")
    @DeleteMapping("/jpa/{id}")
    public ResponseEntity deleteJpaSample(@PathVariable("id") Long id) throws Exception {
        sampleService.deleteJpa(id);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    @Operation(summary = "Sample 데이터 수정 JPA")
    @PutMapping("/jpa")
    @ApiResponse(responseCode = "200", description = DtoStructureConstant.SampleDto)
    public ResponseEntity updateJpaSample(@RequestBody SampleDto sample) throws Exception {
        sampleService.updateJpa(sample);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    @Operation(summary = "Sample 데이터 조회 JPA")
    @GetMapping("/jpa")
    public ResponseEntity getJpaSample() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(sampleService.getJpa());
    }

    @Operation(summary = "Sample 데이터 조회 Mybatis")
    @GetMapping("/mybatis")
    public ResponseEntity getMybatisSample() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(sampleService.getMybatis());
    }

    @Operation(summary = "Sample 데이터 생성 Mybatis")
    @PostMapping("/mybatis")
    public ResponseEntity createMybatisSample(@RequestBody SampleDto sample) throws Exception {
        Long id = sampleService.saveMybatis(sample);
        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "Sample 데이터 삭제 Mybatis")
    @DeleteMapping("/mybatis/{id}")
    public ResponseEntity deleteMybatisSample(@PathVariable("id") Long id) throws Exception {
        sampleService.deleteMybatis(id);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    @Operation(summary = "Sample 데이터 수정 Mybatis")
    @PutMapping("/mybatis")
    public ResponseEntity updateMybatisSample(@RequestBody SampleDto sample) throws Exception {
        sampleService.updateMybatis(sample);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }
}
