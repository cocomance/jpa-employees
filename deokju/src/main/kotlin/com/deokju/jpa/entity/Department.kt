package com.deokju.jpa.entity

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name="departments")
class Department(
    @Column(name = "dept_name")
    val dept_name: String ="",
    @Id
    @Column(name = "dept_no")
    val deptNo: String =""
)
