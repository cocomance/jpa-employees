package com.deokju.jpa.entity

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name="titles")
class Title(
        @EmbeddedId
        val titleId: TitleId,
        @Column(name="to_Date")
        var toDate: LocalDate,
) {

    @ManyToOne
    @JoinColumn(name="emp_no")
    var employee: Employee? = null

    fun addEmployee(employee: Employee): Unit{
        this.employee = employee;
    }
}