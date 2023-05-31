package models.dto;

public class UpdateUserDto {
    private String email;
    private String salt;
    private String saltedPassword;

    public UpdateUserDto(String email, String salt, String saltedPassword) {
        this.email = email;
        this.salt=salt;
        this.saltedPassword = saltedPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getSalt() {
        return salt;
    }

    public String getSaltedPassword() {
        return saltedPassword;
    }
}