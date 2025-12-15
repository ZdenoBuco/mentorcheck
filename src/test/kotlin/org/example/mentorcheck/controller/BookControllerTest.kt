package org.example.mentorcheck.controller

import java.util.stream.*
import org.example.mentorcheck.MentorcheckApplication
import org.example.mentorcheck.domain.Book
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(
    classes = [MentorcheckApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookControllerTest {

    companion object {
        @JvmStatic
        @Container
        val mongo = MongoDBContainer("mongo:6.0").apply { this.start() }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.mongodb.uri") { mongo.replicaSetUrl }
        }
    }

    @LocalServerPort
    var port: Int = 0

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    private fun baseUrl() = "http://localhost:$port/api/books"

    @ParameterizedTest
    @MethodSource("provideBooks")
    fun `create and get book should pass`(book: Book) {
        val response = restTemplate.postForEntity(baseUrl(), book, Book::class.java)
        assertEquals(HttpStatus.OK, response.statusCode)

        val created = response.body!!
        val get = restTemplate.getForEntity("${baseUrl()}/${created.id}", Book::class.java)

        assertEquals(HttpStatus.OK, get.statusCode)
        assertEquals(created.title, get.body!!.title)
    }

    @ParameterizedTest
    @MethodSource("provideBooksForUpdate")
    fun `update book should pass`(initial: Book, update: Book) {
        val created = restTemplate.postForEntity(baseUrl(), initial, Book::class.java).body!!
        val entity = HttpEntity(update)

        val exchange = restTemplate.exchange(
            "${baseUrl()}/${created.id}",
            HttpMethod.PUT,
            entity,
            Book::class.java
        )

        assertEquals(HttpStatus.OK, exchange.statusCode)
        assertEquals(update.title, exchange.body!!.title)
        throw RuntimeException("test failed")
    }

    fun provideBooks(): Stream<Book> = Stream.of(
        Book(title = "Kotlin in Action", authorId = "a1", publisherId = "p1", categoryId = "c1", availableCopies = 3),
        Book(title = "Spring Boot Cookbook", authorId = "a2", publisherId = "p2", categoryId = "c2", availableCopies = 2)
    )

    fun provideBooksForUpdate(): Stream<Array<Book>> = Stream.of(
        arrayOf(
            Book(title = "Old Title", authorId = "a1", publisherId = "p1", categoryId = "c1", availableCopies = 1),
            Book(title = "New Title", authorId = "a1", publisherId = "p1", categoryId = "c1", availableCopies = 5)
        )
    )
}
