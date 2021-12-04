package com.deokju.jpa.entity

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name="Employees")
class Employee(
    @Column(name = "birth_date")
    val birthDate: LocalDate,
    @Column(name = "first_name")
    var firstName: String = "",

    //@Enumerated(EnumType.STRING)
    @Column(name = "gender")
    var gender: Gender,
    @Column(name = "last_name")
    val lastName: String = "",
    @Column(name = "hire_date")
    val hireDate: LocalDate,
) {

    @TableGenerator(name = "id_generator", table = "id_gen", pkColumnName = "gen_name", valueColumnName = "gen_value",
            pkColumnValue="emp_no", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "id_generator")
    @Column(name = "emp_no")
    val empNo: Long = 0L
}
