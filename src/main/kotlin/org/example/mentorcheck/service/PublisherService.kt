package org.example.mentorcheck.service

import java.util.*
import org.example.mentorcheck.domain.Publisher
import org.example.mentorcheck.repository.PublisherRepository
import org.springframework.stereotype.Service

@Service
class PublisherService(private val publisherRepository: PublisherRepository) {

    fun findAll(): List<Publisher> = publisherRepository.findAll()

    fun findById(id: String): Optional<Publisher> = publisherRepository.findById(id)

    fun create(publisher: Publisher): Publisher = publisherRepository.save(publisher)

    fun update(id: String, updated: Publisher): Optional<Publisher> {
        return publisherRepository.findById(id).map {
            val toSave = it.copy(name = updated.name, country = updated.country)
            publisherRepository.save(toSave)
        }
    }

    fun delete(id: String) = publisherRepository.deleteById(id)
}