package com.deokju.jpa.entity

import java.util.stream.Stream
import javax.persistence.AttributeConverter
import javax.persistence.Converter


@Converter(autoApply = true)
class GenderConverter : AttributeConverter<Gender, String>{
    override fun convertToDatabaseColumn(gender: Gender?): String {
        if(gender == null){
            throw Exception("Gender 값이 없음")
        }
        return gender.sex

    }

    override fun convertToEntityAttribute(dbData: String?): Gender {
        if(dbData == null) {
            throw Exception("DB값이 비어 있음")
        }
        val genders = Gender.values()

        for(i in genders.indices)
            if(genders[i].sex == dbData) return genders[i]

        throw Exception("DB값에 맞는 젠더가 없음");

    }
}