package com.deokju.jpa.entity

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name="dept_emp")
class DeptEmp(
        @EmbeddedId
        val deptEmpId: DeptEmpId,
        @Column(name="from_Date")
        var from_Date: LocalDate,
        @Column(name="to_Date")
        var toDate: LocalDate,
){
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="emp_no", insertable = false, updatable = false)
        var employee: Employee? = null

}