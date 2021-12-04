package com.deokju.jpa.repository

import com.deokju.jpa.entity.Department
import com.deokju.jpa.entity.Employee
import com.deokju.jpa.entity.Salary
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
class DepartmentRepository(
        @Autowired
        val em: EntityManager
) {

    fun save(department: Department): Unit{
        em.persist(department);
    }
}