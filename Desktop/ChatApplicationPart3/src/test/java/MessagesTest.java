/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.mycompany.chatapplicationpart3.Messages; 

/**
 *
 * @author ST10433587 
 */
public class MessagesTest {
    
    public MessagesTest() {
    }
    
    // test for 1st task, 1st message 
    @Test
    public void testMessage1_SendMessage() {
        Messages msg1 = new Messages("+27718693002", "Hi Mike, can you join us for dinner tonight");
        
        // check num msg sent, msg #, msg ID, recipient num, msg, msg send option & total num sent
        assertTrue(msg1.checkRecipientCell());
        assertTrue(msg1.checkMessageID());
        assertNotNull(msg1.createMessageHash());
        String sendResult = msg1.sendMessageOption(1); 
        assertEquals("Message successfully sent.", sendResult);
        int total = Messages.returnTotalMessages();
        assertTrue(total >= 1);
          
    }
    
    // test for 2nd task, 2nd message 
    @Test 
    public void testMessage2_DiscardMessage() {
        Messages msg2 = new Messages("08575975889", "Hi Keegan, did you receive the payment?");
        
        // check recipient num format, msg ID 10, msg # not null, discard msg option, msg count increased
        assertFalse(msg2.checkRecipientCell());
        assertTrue(msg2.checkMessageID()); 
        assertNotNull(msg2.createMessageHash());
        String discardResult = msg2.sendMessageOption(2);
        assertEquals("Press 0 to delete message.", discardResult);
        int total = Messages.returnTotalMessages();
        assertTrue(total >= 2);
        
    }
    
    // test msg not > 250 char
    @Test
    public void testMessageLength_Success() {
        String msgText = "Short message";
        assertTrue(msgText.length() <=250);
        String systemMsg = (msgText.length() <=250) ? "Message ready to send." : "Message exceeds 250 characters by " + (msgText.length() - 250) + "please reduce size.";
        assertEquals("Message ready to send.", systemMsg);
        
    }
    
    // if length test fails 
    @Test
    public void testMessageLength_Failure() {
        StringBuilder longMsg = new StringBuilder();
        for (int i = 0; i < 260; i++) longMsg.append("a");
        String msgText = longMsg.toString();
        assertTrue(msgText.length() > 250);
        String systemMsg = (msgText.length() <= 250) ? "Message ready to send." : "Message exceeds 250 characters by " + (msgText.length() - 250) + ", please reduce size.";
        assertEquals("Message exceeds 250 characters by 10, please reduce size.", systemMsg);
      
    }
    
    // test recipient num formatted correctly 
    @Test
    public void testRecipientNumber_Success() {
        Messages msg = new Messages("+27821234567", "Test");
        String systemMsg = msg.checkRecipientCell() ? "Cell phone number successfully captured." : "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        assertEquals("Cell phone number successfully captured.", systemMsg);
        
    }
    
    // if num format incorrect 
    @Test
    public void testRecipientNumber_Failure() {
        Messages msg = new Messages("0821234567", "Test");
        String systemMsg = msg.checkRecipientCell() ? "Cell phone number successfully captured." : "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", systemMsg);
        
    }
    
    // test msg # is correct 1st msg 
    @Test
    public void testMessageHash_Correct() {
        Messages msg = new Messages("+27718693002", "Hi Mike, can you join us for dinner tonight");
        String[] words = "Hi Mike, can you join us for dinner tonight".trim().split(" ");
        String expectedHash = msg.getMessageID().substring(0, 2) + ":1:HITONIGHT";
        assertEquals(expectedHash, msg.getMessageHash());
        
    }
    
    // test msg ID has 10 digits 
    @Test
    public void testMessageID_Created() {
        Messages msg = new Messages("+27821234567", "Test");
        String id = msg.getMessageID();
        assertNotNull(id);
        assertEquals(10, id.length());
        // Simulate system output
        String systemMsg = "Message ID generated: " + id;
        assertTrue(systemMsg.startsWith("Message ID generated:"));
        
    }
    
    // test if msg is sent, disarded & stored 
    @Test 
    public void testSendMessageOptions() {
        Messages msg = new Messages("+27821234567", "Test");
        assertEquals("Message successfully sent.", msg.sendMessageOption(1));
        assertEquals("Press 0 to delete message.", msg.sendMessageOption(2));
        assertEquals("Message successfully stored.", msg.sendMessageOption(3));
        
    }
    
}
