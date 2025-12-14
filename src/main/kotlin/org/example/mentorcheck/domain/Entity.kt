package org.example.mentorcheck.domain


import java.time.LocalDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "books")
data class Book(
    @Id
    val id: String? = null,
    var title: String,
    var authorId: String,
    var publisherId: String,
    var categoryId: String,
    var availableCopies: Int = 1
)

@Document(collection = "authors")
data class Author(
    @Id
    val id: String? = null,
    var name: String,
    var bio: String? = null
)

@Document(collection = "publishers")
data class Publisher(
    @Id
    val id: String? = null,
    var name: String,
    var country: String? = null
)

@Document(collection = "categories")
data class Category(
    @Id
    val id: String? = null,
    var name: String
)

@Document(collection = "members")
data class Member(
    @Id
    val id: String? = null,
    var name: String,
    var email: String,
    var joined: LocalDate = LocalDate.now()
)

@Document(collection = "loans")
data class Loan(
    @Id
    val id: String? = null,
    var bookId: String,
    var memberId: String,
    var loanedOn: LocalDate = LocalDate.now(),
    var dueOn: LocalDate
)