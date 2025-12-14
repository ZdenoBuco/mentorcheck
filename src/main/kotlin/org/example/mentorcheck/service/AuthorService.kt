package org.example.mentorcheck.service

import org.example.mentorcheck.domain.Author
import org.example.mentorcheck.repository.*
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthorService(private val authorRepository: AuthorRepository) {

    fun findAll(): List<Author> = authorRepository.findAll()

    fun findById(id: String): Optional<Author> = authorRepository.findById(id)

    fun create(author: Author): Author = authorRepository.save(author)

    fun update(id: String, updated: Author): Optional<Author> {
        return authorRepository.findById(id).map {
            val toSave = it.copy(name = updated.name, bio = updated.bio)
            authorRepository.save(toSave)
        }
    }

    fun delete(id: String) = authorRepository.deleteById(id)
}