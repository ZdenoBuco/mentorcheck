package org.example.mentorcheck.service

import java.util.*
import org.example.mentorcheck.domain.Loan
import org.example.mentorcheck.repository.LoanRepository
import org.springframework.stereotype.Service

@Service
class LoanService(private val loanRepository: LoanRepository) {

    fun findAll(): List<Loan> = loanRepository.findAll()

    fun findById(id: String): Optional<Loan> = loanRepository.findById(id)

    fun create(loan: Loan): Loan = loanRepository.save(loan)

    fun update(id: String, updated: Loan): Optional<Loan> {
        return loanRepository.findById(id).map {
            val toSave = it.copy(
                bookId = updated.bookId,
                memberId = updated.memberId,
                loanedOn = updated.loanedOn,
                dueOn = updated.dueOn
            )
            loanRepository.save(toSave)
        }
    }

    fun delete(id: String) = loanRepository.deleteById(id)
}