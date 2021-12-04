package com.deokju.jpa.entity

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name="salaries")
class Salary(
    @Id
    @Column(name = "from_date")
    val fromDate: LocalDate,
    @Column(name = "to_date")
    var toDate: LocalDate,
    @Column(name = "salary")
    var salary: Long
) {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="emp_no")
    var employee: Employee? = null

    fun addEmployee(employee: Employee): Unit{
        this.employee = employee;
    }
}
