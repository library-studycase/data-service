package librarydemo

import com.tsystems.dfmg.studies.library.datasource.domain.Book
import com.tsystems.dfmg.studies.library.datasource.domain.BookDetailed
import com.tsystems.dfmg.studies.library.datasource.domain.BookListPage
import com.tsystems.dfmg.studies.library.datasource.domain.CreateRequest
import com.tsystems.dfmg.studies.library.datasource.domain.CreateResponse
import com.tsystems.dfmg.studies.library.datasource.domain.DeleteRequest
import com.tsystems.dfmg.studies.library.datasource.domain.DeleteResponse
import com.tsystems.dfmg.studies.library.datasource.domain.LibraryPort
import com.tsystems.dfmg.studies.library.datasource.domain.LibraryPortService
import com.tsystems.dfmg.studies.library.datasource.domain.ReadDetailedRequest
import com.tsystems.dfmg.studies.library.datasource.domain.ReadDetailedResponse
import com.tsystems.dfmg.studies.library.datasource.domain.ReadListPageRequest
import com.tsystems.dfmg.studies.library.datasource.domain.ReadListPageResponse
import com.tsystems.dfmg.studies.library.datasource.domain.UpdateRequest
import com.tsystems.dfmg.studies.library.datasource.domain.UpdateResponse
import grails.transaction.Transactional
import org.springframework.http.HttpStatus

import javax.xml.ws.BindingProvider
import javax.xml.ws.handler.MessageContext

@Transactional
class BookService {

    def getBooks(Integer offset, Integer limit, String titleFilter) {
        ReadListPageRequest request = new ReadListPageRequest(offset: offset, limit: limit, titleFilter: titleFilter);
        ReadListPageResponse response = getPort().readListPage(request);

        response.page
    }

    def getBook(long id) {
        ReadDetailedRequest request = new ReadDetailedRequest()
        request.id = id
        ReadDetailedResponse response = getPort().readDetailed(request)

        response.book
    }

    def saveBook(BookDetailed book) {
        CreateRequest request = new CreateRequest(title: book.title, description: book.description, author: book.author, pages: book.pages)
        CreateResponse response = getPort().create(request)

        return response.book
    }

    def updateBook(BookDetailed book) {
        UpdateRequest request = new UpdateRequest(title: book.title, description: book.description, author: book.author, pages: book.pages, id: book.id)
        UpdateResponse response = getPort().update(request)

        return response.book
    }

    def deleteBook(long id) {
        DeleteRequest request = new DeleteRequest(id: id)
        DeleteResponse response = getPort().delete(request)

        return HttpStatus.OK
    }

    private static LibraryPort getPort() {
        new LibraryPortService().getLibraryPortSoap11();
    }

}
