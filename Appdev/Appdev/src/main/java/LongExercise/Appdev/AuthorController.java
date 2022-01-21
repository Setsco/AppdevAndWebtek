package LongExercise.Appdev;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@RestController
public class AuthorController {
    List<Authors> authorList = new LinkedList<>();



    public AuthorController(){
        initializeData();
    }

    public List<Authors> initializeData(){

        authorList.add(new Authors(1, "November rain", "1999",200));
        authorList.add(new Authors(2, "Paranoid", "1990",150));
        return authorList;
    }

    @GetMapping("/authors")
    public List<Authors>getAuthors(){return authorList; }

    @GetMapping("authors/{id}")
    public ResponseEntity<Authors> getAuthorID(@PathVariable int id){
        ResponseEntity<Authors> response;
        Authors author = findAuthorByID(id);
        if(author != null){
            response = new ResponseEntity<>(author, HttpStatus.OK);
        }else {
            response = new ResponseEntity<>(author, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PostMapping("/authors")
    public ResponseEntity<String> add(@RequestBody Authors author){
        ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(author != null & author.isValid()){
            Authors existingAuthor = findAuthorByID(author.getID());
            if(existingAuthor == null){
                authorList.add(author);
                response = new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return response;
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        ResponseEntity<String>response;
        Authors author = findAuthorByID(id);
        if(author != null){
            authorList.remove(author);
            response = new ResponseEntity<>(HttpStatus.OK);
        }else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Authors authors){
        ResponseEntity<String> response;
        String errorMessage = null;
        Authors existingAuthor = findAuthorByID(id);
        if(existingAuthor == null){
            errorMessage = "no book with id" + id + " found";
        }
        if(authors == null || !authors.isValid()){
            errorMessage = "Wrong data in request body";
        }else if (authors.getID() != id){
            errorMessage = "ID in the URL does not match with the ID in JSON data (response)";
        }
        if(errorMessage == null){
            authorList.remove(existingAuthor);
            authorList.add(authors);
            response = new ResponseEntity<>(HttpStatus.OK);
        }else{
            response = new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        return response;
    }


    private Authors findAuthorByID(int id) {
        Authors author = null;
        Iterator<Authors> it = authorList.iterator();
        while(it.hasNext() && author == null){
            Authors a = it.next();
            if(a.getID() == id){
                author = a;
            }
        }
        return author;
    }

}

