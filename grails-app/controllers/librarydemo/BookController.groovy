package librarydemo

import com.tsystems.dfmg.studies.library.datasource.domain.BookDetailed
import grails.transaction.Transactional

class BookController {

    BookService bookService

    static responseFormats = ['json']

    static allowedMethods = [index: 'GET', show: 'GET', save: 'POST', update: 'PUT', delete: 'DELETE']


    def index(Integer offset, Integer limit, String titleFilter) {
        respond bookService.getBooks(offset, limit, titleFilter ?: "")
    }

    def show(long id) {
        if (id == null) {
            throw new MissingPropertyException("id")
        }
        respond bookService.getBook(id)
    }

    @Transactional
    def save(BookDetailed book) {
        respond bookService.saveBook(book)
    }

    @Transactional
    def update(BookDetailed book) {
        book.id = Long.parseLong(params["id"].toString())
        respond bookService.updateBook(book)
    }

    @Transactional
    def delete(long id) {
        respond bookService.deleteBook(id)
    }

}
