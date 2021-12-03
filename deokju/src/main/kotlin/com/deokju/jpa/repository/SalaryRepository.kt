package com.deokju.jpa.repository

import com.deokju.jpa.entity.Employee
import com.deokju.jpa.entity.Salary
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
class SalaryRepository(
        @Autowired
        val em: EntityManager
) {

    fun save(salary: Salary): Unit{
        em.persist(salary);
    }
}