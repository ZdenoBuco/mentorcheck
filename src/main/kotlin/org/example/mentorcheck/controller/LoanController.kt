package org.example.mentorcheck.controller

import org.example.mentorcheck.domain.Loan
import org.example.mentorcheck.service.LoanService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/loans")
class LoanController(private val loanService: LoanService) {

    @GetMapping
    fun all(): List<Loan> = loanService.findAll()

    @GetMapping("/{id}")
    fun get(@PathVariable id: String): ResponseEntity<Loan> =
        loanService.findById(id).map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())

    @PostMapping
    fun create(@RequestBody loan: Loan): ResponseEntity<Loan> =
        ResponseEntity.ok(loanService.create(loan))

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody loan: Loan): ResponseEntity<Loan> =
        loanService.update(id, loan)
            .map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<Void> {
        loanService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
