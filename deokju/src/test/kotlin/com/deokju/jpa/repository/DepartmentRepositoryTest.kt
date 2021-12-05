package com.deokju.jpa.repository

import com.deokju.jpa.entity.*
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
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
        val department = Department(deptNo = "DE01", deptName = "웹개발부서")
        dr.save(department)
    }

    @Test
    fun `부서연관관계 저장 테스트`() {
        val employee = em.createQuery("select E from Employee E", Employee::class.javaObjectType)
            .singleResult

        val department =em.createQuery("select D from Department D", Department::class.javaObjectType)
            .singleResult

        val deptEmp = DeptEmp(
            deptEmpId = DeptEmpId(employee.empNo, department.deptNo),
            fromDate = LocalDate.now(),
            toDate = LocalDate.now()
        )

        em.persist(deptEmp)

        em.flush()
        em.clear()

        val getDeptEmp = em.createQuery(
            "select D from DeptEmp D where D.deptEmpId.empNo = :empNo" +
                    " and D.deptEmpId.deptNo = :deptNo", DeptEmp::class.javaObjectType
        ).setParameter("empNo", employee.empNo)
         .setParameter("deptNo", department.deptNo)
            .singleResult

        //비교
        assertThat(getDeptEmp.employee).isNotNull
        assertThat(getDeptEmp.employee?.empNo).isEqualTo(employee.empNo)
        assertThat(getDeptEmp.employee?.gender).isEqualTo(employee.gender)
        assertThat(getDeptEmp.employee?.firstName).isEqualTo(employee.firstName)
        assertThat(getDeptEmp.employee?.lastName).isEqualTo(employee.lastName)
    }

    @Test
    fun `부서 및 직원 연관관계 조회 테스트`() {
        val deptEmp = em.createQuery("select d from DeptEmp d WHERE d.deptEmpId.empNo = :empNo AND" +
                " d.deptEmpId.deptNo = :deptNo ", DeptEmp::class.javaObjectType)
            .setParameter("empNo", 2L)
            .setParameter("deptNo", "DE01")
            .singleResult

        println(deptEmp)

        val employee = em.createQuery("select e from Employee  e WHERE e.empNo = :empNo"
            , Employee::class.javaObjectType)
            .setParameter("empNo", 2L)
            .singleResult

        assertThat(deptEmp.employee).isEqualTo(employee)
        assertThat(deptEmp.employee).isNotNull
        assertThat(deptEmp.employee?.empNo).isEqualTo(employee.empNo)
        assertThat(deptEmp.employee?.gender).isEqualTo(employee.gender)
        assertThat(deptEmp.employee?.firstName).isEqualTo(employee.firstName)
        assertThat(deptEmp.employee?.lastName).isEqualTo(employee.lastName)
    }



}
