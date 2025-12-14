package org.example.mentorcheck.controller

import org.example.mentorcheck.domain.Publisher
import org.example.mentorcheck.service.PublisherService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/publishers")
class PublisherController(private val publisherService: PublisherService) {

    @GetMapping
    fun all(): List<Publisher> = publisherService.findAll()

    @GetMapping("/{id}")
    fun get(@PathVariable id: String): ResponseEntity<Publisher> =
        publisherService.findById(id).map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())

    @PostMapping
    fun create(@RequestBody publisher: Publisher): ResponseEntity<Publisher> =
        ResponseEntity.ok(publisherService.create(publisher))

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody publisher: Publisher): ResponseEntity<Publisher> =
        publisherService.update(id, publisher)
            .map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<Void> {
        publisherService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
