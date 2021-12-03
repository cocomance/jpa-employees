package com.deokju.jpa.entity

import java.io.Serializable
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class TitleId(
        var title: String,
        @Column(name="from_date")
        var fromDate: LocalDate,
): Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TitleId

        if (title != other.title) return false
        if (fromDate != other.fromDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + fromDate.hashCode()
        return result
    }
}