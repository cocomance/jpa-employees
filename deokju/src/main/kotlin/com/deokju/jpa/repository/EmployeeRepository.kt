package com.deokju.jpa.repository

import com.deokju.jpa.entity.Employee
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
class EmployeeRepository(
        @Autowired
        val em: EntityManager
) {

    fun save(employee: Employee): Employee {
        em.persist(employee);
        return employee;
    }
}