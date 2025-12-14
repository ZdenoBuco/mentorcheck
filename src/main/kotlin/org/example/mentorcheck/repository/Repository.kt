package org.example.mentorcheck.repository

import org.example.mentorcheck.domain.Author
import org.example.mentorcheck.domain.Book
import org.example.mentorcheck.domain.Category
import org.example.mentorcheck.domain.Loan
import org.example.mentorcheck.domain.Member
import org.example.mentorcheck.domain.Publisher
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : MongoRepository<Book, String>

@Repository
interface AuthorRepository : MongoRepository<Author, String>

@Repository
interface PublisherRepository : MongoRepository<Publisher, String>

@Repository
interface CategoryRepository : MongoRepository<Category, String>

@Repository
interface MemberRepository : MongoRepository<Member, String>

@Repository
interface LoanRepository: MongoRepository<Loan, String>