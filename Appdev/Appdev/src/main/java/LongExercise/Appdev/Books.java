package LongExercise.Appdev;

public class Books {

    private int ID;
    private String title;
    private int year;
    private int numberOfPages;


    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Books(int ID, String title, int year, int numberOfPages){
        this.ID = ID;
        this.title = title;
        this.year = year;
        this.numberOfPages = numberOfPages;

    }

    public boolean isValid(){
        return ID >= 1 && title != null && (!title.equals(""));
    }
}
