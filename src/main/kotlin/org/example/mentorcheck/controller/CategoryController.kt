package org.example.mentorcheck.controller

import org.example.mentorcheck.domain.Category
import org.example.mentorcheck.service.CategoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/categories")
class CategoryController(private val categoryService: CategoryService) {

    @GetMapping
    fun all(): List<Category> = categoryService.findAll()

    @GetMapping("/{id}")
    fun get(@PathVariable id: String): ResponseEntity<Category> =
        categoryService.findById(id).map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())

    @PostMapping
    fun create(@RequestBody category: Category): ResponseEntity<Category> =
        ResponseEntity.ok(categoryService.create(category))

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody category: Category): ResponseEntity<Category> =
        categoryService.update(id, category)
            .map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<Void> {
        categoryService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
