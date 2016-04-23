package librarydemo

import com.tsystems.dfmg.studies.library.datasource.domain.*
import grails.transaction.Transactional
import library.demo.HeaderHandlerResolver
import library.demo.UserContext
import org.springframework.http.HttpStatus

import javax.annotation.PostConstruct

@Transactional
class BookService {

    UserContext userContext

    private LibraryPort port;

    def getBooks(Integer offset, Integer limit, String titleFilter) {
        ReadListPageRequest request = new ReadListPageRequest(offset: offset, limit: limit, titleFilter: titleFilter);
//        ReadListPageResponse response = getPort().readListPage(request);
        ReadListPageResponse response = port.readListPage(request);

        response.page
    }

    def getBook(long id) {
        ReadDetailedRequest request = new ReadDetailedRequest()
        request.id = id
//        ReadDetailedResponse response = getPort().readDetailed(request)
        ReadDetailedResponse response = port.readDetailed(request)

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

    @PostConstruct
    private void onConstructed() {
        LibraryPortService service = new LibraryPortService()
        HeaderHandlerResolver resolver = new HeaderHandlerResolver(userContext);
        service.setHandlerResolver(resolver)
        port = service.getLibraryPortSoap11();
    }

}
