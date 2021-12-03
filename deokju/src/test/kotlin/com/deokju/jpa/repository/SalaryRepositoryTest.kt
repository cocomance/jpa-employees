package com.deokju.jpa.repository

import com.deokju.jpa.entity.Employee
import com.deokju.jpa.entity.Gender
import com.deokju.jpa.entity.Salary
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
        val deokju = em.createQuery("select E from Employee E where E.empNo =:empNo", Employee::class.javaObjectType)
                .setParameter("empNo", 2L)
                .singleResult

        //월급정보 만들기
        val octSalary = Salary(
                LocalDate.of(2019,11,4),
                LocalDate.of(2019,12,5),
                3140000)

        octSalary.addEmployee(deokju)

        salaryRepository.save(octSalary)
    }

}
