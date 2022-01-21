package LongExercise.Appdev;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@RestController
public class BookController {
    List<Books> bookList = new LinkedList<>();
    public BookController(){

        initializeData();
    }

    public List<Books> initializeData(){
        bookList.add(new Books(1, "November rain", 1999,200));
        bookList.add(new Books(2, "Paranoid", 1990,150));
        return bookList;
    }

    @GetMapping("/books")
    public List<Books>getBooks(){
        return bookList;
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Books> getID(@PathVariable int id){
        ResponseEntity<Books>response;
        Books book = findBookByID(id);
        if(book != null){
            response = new ResponseEntity<>(book, HttpStatus.OK);
        }else{
            response = new ResponseEntity<>(book, HttpStatus.NOT_FOUND);
        }
        return response;
    }
    @PostMapping("/books")
    public ResponseEntity<String> add(@RequestBody Books book){
        ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(book!= null && book.isValid()) {
            Books existing = findBookByID(book.getID());
            if (existing == null) {
                bookList.add(book);
                response = new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return response;

    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        ResponseEntity<String> response;
        Books book = findBookByID(id);
        if(book !=null){
            bookList.remove(book);
            response = new ResponseEntity<>(HttpStatus.OK);
        }else{
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Books book){
        ResponseEntity<String> response;
        String errorMessage = null;
        Books existingBook = findBookByID(id);
        if(existingBook == null){
            errorMessage = "No book with id " + id +" found";
        }
        if(book == null || !book.isValid()){
            errorMessage = "Wrong data in request body";
        }else if(book.getID() !=id){
            errorMessage = "ID in the URL does not match the ID in JSON data (response)";
        }
        if(errorMessage == null){
            bookList.remove(existingBook);
            bookList.add(book);
            response = new ResponseEntity<>(HttpStatus.OK);
        }else{
            response = new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        return response;
    }




    private Books findBookByID(int id) {
        Books book = null;
        Iterator<Books> it=  bookList.iterator();
        while(it.hasNext() && book == null){
            Books b = it.next();
            if(b.getID() == id){
                book = b;
            }
        }
        return book;
    }



}
