package com.deokju.jpa.repository

import com.deokju.jpa.entity.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import java.time.LocalDate
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@SpringBootTest
@Transactional
@Rollback(false)
class TitleRepositoryTest(
        @Autowired
        val em:EntityManager
) {

    @Test
    fun `타이틀 저장 테스트`() {

        //직원정보 가져오기
        val deokju = em.createQuery("select E from Employee E where E.empNo =:empNo", Employee::class.javaObjectType)
                .setParameter("empNo", 2L)
                .singleResult

        //타이틀이 뭐지
        val titles = Title(
                titleId = TitleId("타이틀", LocalDate.now()),
                toDate = LocalDate.now()
                 )

            titles.addEmployee(deokju)

        em.persist(titles);
    }

}
