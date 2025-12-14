package org.example.mentorcheck.controller

import org.example.mentorcheck.domain.Author
import org.example.mentorcheck.service.AuthorService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/authors")
class AuthorController(private val authorService: AuthorService) {

    @GetMapping
    fun all(): List<Author> = authorService.findAll()

    @GetMapping("/{id}")
    fun get(@PathVariable id: String): ResponseEntity<Author> =
        authorService.findById(id).map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())

    @PostMapping
    fun create(@RequestBody author: Author): ResponseEntity<Author> =
        ResponseEntity.ok(authorService.create(author))

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody author: Author): ResponseEntity<Author> =
        authorService.update(id, author)
            .map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<Void> {
        authorService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
