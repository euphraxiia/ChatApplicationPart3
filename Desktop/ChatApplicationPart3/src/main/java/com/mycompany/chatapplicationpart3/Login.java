/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.chatapplicationpart3;

public class Login {
    private String username;
    private String password;
    private String cellNumber;

    public Login(String username, String password, String cellNumber) {
        this.username = username;
        this.password = password;
        this.cellNumber = cellNumber;
    }

    public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity() {
        return password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+=-]).{8,}$");
    }

    public boolean checkCellPhoneNumber() {
        String cleaned = cellNumber.replaceAll("[^\\x00-\\x7F]", "").trim();
        return cleaned.matches("^\\+27\\d{9}$");
    }

    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        return "User successfully registered.";
    }

    public boolean loginUser(String loginUsername, String loginPassword) {
        return this.username.equals(loginUsername) && this.password.equals(loginPassword);
    }

    public String returnLoginStatus(String firstName, String lastName, boolean loggedIn) {
        return loggedIn ?
            "Welcome " + firstName + ", " + lastName + " it is great to see you again." :
            "Username or password incorrect, please try again.";
    }
}

// Trigger GitHub Actions workflow
