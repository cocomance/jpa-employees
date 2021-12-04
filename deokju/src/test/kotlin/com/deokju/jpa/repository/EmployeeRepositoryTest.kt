package com.deokju.jpa.repository

import com.deokju.jpa.entity.Employee
import com.deokju.jpa.entity.Gender
import org.assertj.core.api.Assertions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import java.time.LocalDate
import javax.persistence.EntityManager
import javax.transaction.Transactional

@SpringBootTest
@Transactional
@Rollback(false)
class EmployeeRepositoryTest(
        @Autowired
        val employeeRepository: EmployeeRepository,
        @Autowired
        val em: EntityManager

) {

    @Test
    fun saveEmployee() {
        val m1 = Employee(
                birthDate = LocalDate.of(1972,8,14),
                firstName = "유",
                lastName = "재석",
                gender = Gender.MALE,
                hireDate = LocalDate.now()
        )
        employeeRepository.save(m1);

        em.flush();
        em.clear();

        val e = em.createQuery("SELECT e FROM Employee e WHERE e.empNo = :empNo", Employee::class.javaObjectType)
            .setParameter("empNo", m1.empNo)
            .singleResult

        Assertions.assertThat(m1.empNo).isEqualTo(e.empNo)
        Assertions.assertThat(m1.gender).isEqualTo(e.gender)
        Assertions.assertThat(m1.firstName).isEqualTo(e.firstName)
        Assertions.assertThat(m1.lastName).isEqualTo(e.lastName)
        Assertions.assertThat(m1.birthDate).isEqualTo(e.birthDate)
        Assertions.assertThat(m1.hireDate).isEqualTo(e.hireDate)
    }

}
