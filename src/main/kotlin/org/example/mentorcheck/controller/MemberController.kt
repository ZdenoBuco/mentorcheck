package org.example.mentorcheck.controller

import org.example.mentorcheck.domain.Member
import org.example.mentorcheck.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/members")
class MemberController(private val memberService: MemberService) {

    @GetMapping
    fun all(): List<Member> = memberService.findAll()

    @GetMapping("/{id}")
    fun get(@PathVariable id: String): ResponseEntity<Member> =
        memberService.findById(id).map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())

    @PostMapping
    fun create(@RequestBody member: Member): ResponseEntity<Member> =
        ResponseEntity.ok(memberService.create(member))

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody member: Member): ResponseEntity<Member> =
        memberService.update(id, member)
            .map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<Void> {
        memberService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
