package learn.app.models.user;

import java.util.Objects;

public class User {

    // Fields
    private int userId;
    private String username;
    private String email;
    private String password;
    private double weight;
    private int heightFt;
    private int heightIn;

    // Constructor

    public User() { // Empty Constructor

    }

    public User(int userId, String username, String email, String password, double weight, int heightFt, int heightIn) {

        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.weight = weight;
        this.heightFt = heightFt;
        this.heightIn = heightIn;
    }

    // Getters & Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getHeightFt() {
        return heightFt;
    }

    public void setHeightFt(int heightFt) {
        this.heightFt = heightFt;
    }

    public int getHeightIn() {
        return heightIn;
    }

    public void setHeightIn(int heightIn) {
        this.heightIn = heightIn;
    }

}
