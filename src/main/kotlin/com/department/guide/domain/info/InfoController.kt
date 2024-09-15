package com.department.guide.domain.info

import com.department.guide.global.httpStatus.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@Tag(name = "info-controller", description = "학과 정보 관련 API")
@RestController
@RequestMapping("/api/informations")
class InfoController(
    private val infoService: InfoService
) {

    @Operation(summary = "학과 혜택 리스트 조회", description = "학과에서 제공하는 혜택 전체 조회")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "혜택 조회 성공")
    ])
    @GetMapping("/benefit")
    fun getBenefitList():
            BaseResponse<List<Benefit>> {

        infoService.benefitList()
        return BaseResponse(data = infoService.benefitList(), message = "혜택 조회 성공")
    }

    @Operation(summary = "졸업 요건 조회", description = "졸업 요건 전체 조회")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "졸업 요건 조회 성공")
    ])
    @GetMapping("/requirements")
    fun getRequirementsList():
            BaseResponse<List<Requirements>> {

        infoService.benefitList()
        return BaseResponse(data = infoService.requirementsList(), message = "졸업 요건 조회 성공")
    }
}