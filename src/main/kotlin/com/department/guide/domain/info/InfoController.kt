package com.department.guide.domain.info

import com.department.guide.global.httpStatus.BaseResponse
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/informations")
class InfoController(
    private val infoService: InfoService
) {

    @GetMapping("/benefit")
    fun getBenefitList():
            BaseResponse<List<Benefit>> {

        infoService.benefitList()
        return BaseResponse(data = infoService.benefitList(), message = "혜택 조회 성공")
    }

    @GetMapping("/requirements")
    fun getRequirementsList():
            BaseResponse<List<Requirements>> {

        infoService.benefitList()
        return BaseResponse(data = infoService.requirementsList(), message = "졸업 요건 조회 성공")
    }
}