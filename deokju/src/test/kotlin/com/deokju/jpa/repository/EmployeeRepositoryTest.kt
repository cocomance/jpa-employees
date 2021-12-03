package com.deokju.jpa.repository

import com.deokju.jpa.entity.Employee
import com.deokju.jpa.entity.Gender
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import java.time.LocalDate
import javax.transaction.Transactional

@SpringBootTest
@Transactional
@Rollback(false)
class EmployeeRepositoryTest(
        @Autowired
        val employeeRepository: EmployeeRepository
) {

    @Test
    fun saveEmployee() {
        val m1 = Employee(
                birthDate = LocalDate.of(1986,5,15),
                firstName = "김",
                lastName = "덕주",
                gender = Gender.M,
                hireDate = LocalDate.now()
        )

        employeeRepository.save(m1);
    }

}
