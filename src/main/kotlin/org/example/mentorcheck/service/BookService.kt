package org.example.mentorcheck.service

import java.util.*
import org.example.mentorcheck.domain.Book
import org.example.mentorcheck.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(private val bookRepository: BookRepository) {

    fun findAll(): List<Book> = bookRepository.findAll()

    fun findById(id: String): Optional<Book> = bookRepository.findById(id)

    fun create(book: Book): Book = bookRepository.save(book)

    fun update(id: String, updated: Book): Optional<Book> {
        return bookRepository.findById(id).map {
            val toSave = it.copy(
                title = updated.title,
                authorId = updated.authorId,
                publisherId = updated.publisherId,
                categoryId = updated.categoryId,
                availableCopies = updated.availableCopies
            )
            bookRepository.save(toSave)
        }
    }

    fun delete(id: String) {
        bookRepository.deleteById(id)
    }
}
