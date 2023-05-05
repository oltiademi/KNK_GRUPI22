package models.dto;

public class CreateUserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String salt;
    private String saltedPassword;

    public CreateUserDto(String firstName, String lastName,String email,String username,String salt, String saltedPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.saltedPassword = saltedPassword;
        this.salt = salt;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getSalt() {
        return salt;
    }

    public String getSaltedPassword() {
        return saltedPassword;
    }
}