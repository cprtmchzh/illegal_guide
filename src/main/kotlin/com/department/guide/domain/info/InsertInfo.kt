package com.department.guide.domain.info

import jakarta.annotation.PostConstruct
import org.bson.Document
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class InsertInfo {

    @Autowired
    private lateinit var benefitRepository: BenefitRepository

    @Autowired
    private lateinit var requirementsRepository: RequirementsRepository

    @PostConstruct
    fun insertRequirements() {

        val case1 = "{'학점' : '전공 학점/총 학점 (72/130)', '봉사 활동' : '사랑 나눔 봉사단/ 총 봉사 활동 (12/32)'}"

        val case2 = "{'학점' : '전공 학점/총 학점 (72/130)', '학과 영역 1 (택 1)' : 'TOEIC, JPT 등의 공인 어학 능력 시험에서 " +
                "TOEIC 800점 이상의 동일 수준 어학 능력 충족, 임베디드기사, 인터넷정보관리사1급, 정보보안기사, OCJP, OCA, " +
                "OCP, CCNA, CCNP, SQLP, 게임그래픽전문가 TOPCIT(250점 이상) 게임프로그래밍전문가, 정보통신기사, " +
                "정보기술프로젝트관리전문가, 정보시스템감리사, 네트워크관리사1급, 전자계산기기사, 취업자 또는 본교 대학원 입학 예정자, " +
                "리눅스마스터1급, 전자상거래관리사, 정보관리기술사, 멀티미디어콘텐츠제작전문가, 전자계산기조직응용기사, " +
                "컴퓨터시스템응용기술사, 정보처리기사', '학과 영역 2 (택 1)' : '[SW개발 및 활용분야] 오픈소스 기반 산학협력 공동 " +
                "프로젝트 구현 및 구현 결과물의 교내, 외 전시회 출품, [SW개발 및 활용분야] TOPCIT 2회 이상 응시(3, 4학년 재학시. " +
                "단, 1회 응시 후 250점 이상 취득자는 추가 응시 면제)', '봉사 활동' : '사랑 나눔 봉사단/ 총 봉사 활동 (12/32)'}"

        requirementsRepository.save(Requirements("CASE_1", "20학번 이전", Document.parse(case1)))
        requirementsRepository.save(Requirements("CASE_2", "20학번 이후", Document.parse(case2)))
    }

    @PostConstruct
    fun insertBenefit() {

        val caseV3 = "V3, V3 Net, 내 PC 지키미, CleanAX, 기숙사용 V3"
        val caseMicrosoft = "Office, Windows, Office365"
        val caseGoogle = "Google Apps"
        val caseHancome = "Hancome Office"
        val caseSPSS = "SPSS, SPSS Amos"
        val caseTG = "투게더그룹 FONT"
        val caseAdobe = "Adobe"

        benefitRepository.save(Benefit("Benefit_1", "V3", caseV3))
        benefitRepository.save(Benefit("Benefit_2", "Microsoft", caseMicrosoft))
        benefitRepository.save(Benefit("Benefit_3", "V3", caseGoogle))
        benefitRepository.save(Benefit("Benefit_4", "Hancome", caseHancome))
        benefitRepository.save(Benefit("Benefit_5", "SPSS", caseSPSS))
        benefitRepository.save(Benefit("Benefit_6", "투게더그룹 폰트", caseTG))
        benefitRepository.save(Benefit("Benefit_7", "Adobe", caseAdobe))

    }
}