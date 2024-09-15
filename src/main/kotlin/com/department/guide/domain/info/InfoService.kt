package com.department.guide.domain.info

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class InfoService(
    private val benefitRepository: BenefitRepository,
    private val requirementsRepository: RequirementsRepository
) {

    fun benefitList(): List<Benefit> {
        return benefitRepository.findAll()
    }

    fun requirementsList(): List<Requirements> {
        return requirementsRepository.findAll()
    }
}