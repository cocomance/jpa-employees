package com.deokju.jpa.repository

import com.deokju.jpa.entity.Employee
import com.deokju.jpa.entity.Gender
import com.deokju.jpa.entity.Salary
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import java.time.LocalDate
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@SpringBootTest
@Transactional
@Rollback(false)
class SalaryRepositoryTest(
        @Autowired
        val salaryRepository: SalaryRepository,
        @Autowired
        val em:EntityManager

) {

    @Test
    fun `월급저장 테스트`() {

        //월급 줄 놈 찾아오기
        val jaeyuak = em.createQuery("select E from Employee E where E.empNo =:empNo", Employee::class.javaObjectType)
                .setParameter("empNo", 7L)
                .singleResult

        //월급정보 만들기
        val octSalary = Salary(
                LocalDate.of(2019,11,4),
                LocalDate.of(2019,12,5),
                100000000)

        octSalary.addEmployee(jaeyuak)

        salaryRepository.save(octSalary)
    }

    @Test
    fun `근로자와 월급의 연관관계 테스트`() {
        val jaeyuak = em.createQuery("select E from Employee E where E.empNo =:empNo", Employee::class.javaObjectType)
            .setParameter("empNo", 7L)
            .singleResult

        val payInfo = em.createQuery("select S from Salary S where S.employee.empNo =:empNo", Salary::class.javaObjectType)
            .setParameter("empNo", 7L)
            .singleResult

        Assertions.assertThat(jaeyuak.salaries).isNotEmpty
        Assertions.assertThat(jaeyuak.salaries[0].salary).isEqualTo(payInfo.salary)
    }

}
