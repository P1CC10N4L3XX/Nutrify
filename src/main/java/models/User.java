package models;

public class User {
    private String email;
    private String dateOfBirth;
    private String description;

    public User(String email, String dateOfBirth, String description){
        this.email=email;
        this.dateOfBirth=dateOfBirth;
        this.description=description;
    }

    public String getEmail() {
        return this.email;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getDescription() {
        return this.description;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
