package com.deokju.jpa.repository

import com.deokju.jpa.entity.*
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
class DepartmentRepositoryTest(
        @Autowired
        val dr: DepartmentRepository,
        @Autowired
        val em:EntityManager

) {

    @Test
    fun `부서저장 테스트`() {

        val department = Department(deptNo = "DE01", dept_name = "웹개발부서")
        dr.save(department)
    }

    @Test
    fun `부서 및 직원 연관관계 조회 테스트`() {
        val deptEmp = em.createQuery("select d from DeptEmp d WHERE d.deptEmpId.empNo = :empNo AND" +
                " d.deptEmpId.deptNo = :deptNo ", DeptEmp::class.javaObjectType)
            .setParameter("empNo", 2L)
            .setParameter("deptNo", "DE01")
            .singleResult

        println(deptEmp)



    }



}
