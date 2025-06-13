/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.mycompany.chatapplicationpart3.Login;

/**
 *
 * @author ST10433587 
 */
public class LoginTest {
    
    public LoginTest() {
    }
    
    // test username with _, max 5 chars, returns true 
    @Test
    public void testCheckUserNameTrue() {
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.checkUserName());
    }

    // if false 
    @Test
    public void testCheckUserNameFalse() {
        Login user = new Login("kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(user.checkUserName());
    }

    // test passwords has correct complexity 
    @Test
    public void testCheckPasswordComplexityTrue() {
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.checkPasswordComplexity());
    }

    // if false 
    @Test
    public void testCheckPasswordComplexityFalse() {
        Login user = new Login("kyl_1", "password", "+27838968976");
        assertFalse(user.checkPasswordComplexity());
    }

    // test cell num is SAn with country code & correct format 
    @Test
    public void testCheckCellPhoneNumberTrue() {
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.checkCellPhoneNumber());
    }

    // if false 
    @Test
    public void testCheckCellPhoneNumberFalse() {
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "08966553");
        assertFalse(user.checkCellPhoneNumber());
    }

    // test successful login, correct username & password returns true
    @Test
    public void testLoginUserSuccess() {
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    // if failed & false 
    @Test
    public void testLoginUserFailure() {
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(user.loginUser("wrongUser", "wrongPassword"));
    }
    
}
