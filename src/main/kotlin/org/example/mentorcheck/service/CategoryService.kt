package org.example.mentorcheck.service

import java.util.*
import org.example.mentorcheck.domain.Category
import org.example.mentorcheck.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(private val categoryRepository: CategoryRepository) {

    fun findAll(): List<Category> = categoryRepository.findAll()

    fun findById(id: String): Optional<Category> = categoryRepository.findById(id)

    fun create(category: Category): Category = categoryRepository.save(category)

    fun update(id: String, updated: Category): Optional<Category> {
        return categoryRepository.findById(id).map {
            val toSave = it.copy(name = updated.name)
            categoryRepository.save(toSave)
        }
    }

    fun delete(id: String) = categoryRepository.deleteById(id)
}