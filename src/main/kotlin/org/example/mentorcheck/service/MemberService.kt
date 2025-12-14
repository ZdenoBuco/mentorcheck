package org.example.mentorcheck.service

import java.util.*
import org.example.mentorcheck.domain.Member
import org.example.mentorcheck.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(private val memberRepository: MemberRepository) {

    fun findAll(): List<Member> = memberRepository.findAll()

    fun findById(id: String): Optional<Member> = memberRepository.findById(id)

    fun create(member: Member): Member = memberRepository.save(member)

    fun update(id: String, updated: Member): Optional<Member> {
        return memberRepository.findById(id).map {
            val toSave = it.copy(name = updated.name, email = updated.email, joined = updated.joined)
            memberRepository.save(toSave)
        }
    }

    fun delete(id: String) = memberRepository.deleteById(id)
}