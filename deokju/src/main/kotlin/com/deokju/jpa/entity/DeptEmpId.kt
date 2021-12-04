package com.deokju.jpa.entity

import java.io.Serializable
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class DeptEmpId(
        @Column(name="emp_no")
        var empNo: Long,
        @Column(name="dept_no")
        var deptNo: String
): Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DeptEmpId

        if (empNo != other.empNo) return false
        if (deptNo != other.deptNo) return false

        return true
    }

    override fun hashCode(): Int {
        var result = empNo.hashCode()
        result = 31 * result + deptNo.hashCode()
        return result
    }
}