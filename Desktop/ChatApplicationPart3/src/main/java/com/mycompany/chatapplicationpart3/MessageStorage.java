/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapplicationpart3;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MessageStorage {
    private static final String FILE_PATH = "messages.json";

    public static void saveToJson(Messages message) {
        JSONArray allMessages = readMessagesFromJson();

        JSONObject obj = new JSONObject();
        obj.put("messageID", message.getMessageID());
        obj.put("recipientCell", message.getRecipientCell());
        obj.put("messageText", message.getMessageText());
        obj.put("messageHash", message.getMessageHash());

        allMessages.put(obj);

        try (FileWriter file = new FileWriter(FILE_PATH)) {
            file.write(allMessages.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONArray readMessagesFromJson() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new JSONArray();
        }
        try (FileReader reader = new FileReader(file)) {
            JSONTokener tokener = new JSONTokener(reader);
            return new JSONArray(tokener);
        } catch (IOException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }

    public static ArrayList<Messages> getMessagesFromFile() {
        JSONArray array = readMessagesFromJson();
        ArrayList<Messages> messages = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            String recipient = obj.getString("recipientCell");
            String text = obj.getString("messageText");
            Messages msg = new Messages(recipient, text);
            messages.add(msg);
        }

        return messages;
    }
}

/**
 * Reference list:
 * ChatGPT, 2025. How To Implement JSON on Chat Application . [Online]. Available at: chatgpt.com. [Accessed on 13 June 2025].
 * 
 */