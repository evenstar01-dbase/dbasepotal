package kr.co.tbase.common.module.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * targetClasses내 각 클래스들을 열고
 * {@io.swagger.v3.oas.annotations.media.Schema} 어노테이션 선언된 부분을 분석해
 * Schema.description을 모아 json 형태의 구조화된 문자열로 만들어준다.
 *
 * 예)
 * 1. 아래와 같은 DTO 클래스를 targetClasses에 넣으면
 *
 * @Schema(description = "sample dto")
 * public class DtoStructureSampleDto {
 *     @Schema(description = "test field")
 *     private boolean test = false;
 *
 *     @Schema(description = "list")
 *     private List<InnerClass> innerClassList;
 *
 *     @Schema(description = "inner class")
 *     public static class InnerClass {
 *         @Schema(description = "inner class field")
 *         private String innerClassField;
 *     }
 * }
 *
 * 2. 다음과 같은 상수 값이 structureDetailContainer 이름을 가진 클래스 내에 생성된다.
 *
 * public static final String DtoStructureSampleDto = "{\n\n"
 *     + "  \"test\": \"// test field\",\n\n"
 *     + "  \"innerClassList\": [\n\n"
 *     + "    {\n\n"
 *     + "      \"innerClassField\": \"// inner class field\"\n\n"
 *     + "    }\n\n"
 *     + "  ]\n\n"
 *     + "}\n\n";
 *
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface MakeDtoStructure {
    /**
     * 설명을 위한 문자열 상수가 생성될 분석 대상 클래스
     */
    Class[] targetClasses() default {};

    /**
     * 설명을 위한 문자열 텍스트가 담길 클래스명
     */
    String structureDetailContainer();
}
