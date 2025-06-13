/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.json.JSONArray;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

import com.mycompany.chatapplicationpart3.MessageStorage;
import com.mycompany.chatapplicationpart3.Messages;



public class MessageStorageTest {

    @BeforeEach
    @AfterEach
    public void cleanUp() {
        File file = new File("messages.json");
        if (file.exists()) file.delete();
    }

    @Test
    public void testSaveToJsonCreatesFile() {
        Messages msg = new Messages("+27812345678", "Test message to save");
        MessageStorage.saveToJson(msg);

        File file = new File("messages.json");
        assertTrue(file.exists(), "JSON file should be created after saving a message.");
    }

    @Test
    public void testReadMessagesFromJsonNotEmpty() {
        Messages msg = new Messages("+27812345678", "Test message to save");
        MessageStorage.saveToJson(msg);

        JSONArray messages = MessageStorage.readMessagesFromJson();
        assertTrue(messages.length() > 0, "Stored messages should be read from JSON.");
    }

    @Test
    public void testMessageContentInJson() {
        Messages msg = new Messages("+27812345678", "Test message to save");
        MessageStorage.saveToJson(msg);

        JSONArray messages = MessageStorage.readMessagesFromJson();
        boolean found = false;
        for (int i = 0; i < messages.length(); i++) {
            if (messages.getJSONObject(i).getString("messageText").equals("Test message to save")) {
                found = true;
                break;
            }
        }
        assertTrue(found, "Saved message should be present in JSON file.");
    }
}
