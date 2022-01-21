package LongExercise.Appdev;

public class Authors {
    private int ID;
    private String firstName;
    private String lastName;
    private int birthYear;

    public int getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public Authors(int ID, String firstName, String lastName, int birthYear){
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = ID;
        this.birthYear = birthYear;
    }

    public boolean isValid(){
        return ID > 0 && firstName != null && (!firstName.equals(""));
    }


}
