/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.chatapplicationpart3;

import java.util.Random;

public class Messages {
    private static int messageCount = 0;
    private int messageNumber;
    private String messageID;
    private String messageText;
    private String recipientCell;
    private String messageHash;
    private boolean sent;
    private boolean received;
    private boolean read;

    public Messages(String recipientCell, String messageText) {
        this.recipientCell = recipientCell;
        this.messageText = messageText;
        messageCount++;
        this.messageNumber = messageCount;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
        this.sent = false;
        this.received = false;
        this.read = false;
    }

    private String generateMessageID() {
        Random rand = new Random();
        return String.valueOf(1000000000 + rand.nextInt(900000000));
    }

    public boolean checkRecipientCell() {
        return recipientCell.matches("^\\+27\\d{9}$");
    }

    public boolean checkMessageID() {
        return messageID.length() == 10;
    }

    public String createMessageHash() {
        String[] words = messageText.trim().split(" ");
        String first = words.length > 0 ? words[0] : "";
        String last = words.length > 1 ? words[words.length - 1] : "";
        return messageID.substring(0, 2) + ":" + messageNumber + ":" + (first + last).toUpperCase();
    }

    public String sendMessageOption(int choice) {
        return switch (choice) {
            case 1 -> {
                this.sent = true;
                yield "Message successfully sent.";
            }
            case 2 -> "Press 0 to delete message.";
            case 3 -> "Message successfully stored.";
            default -> "Invalid option.";
        };
    }

    public static int returnTotalMessages() {
        return messageCount;
    }

    public String getMessageID() {
        return messageID;
    }

    public String getRecipientCell() {
        return recipientCell;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getMessageHash() {
        return messageHash;
    }

    @Override
    public String toString() {
        return "MessageID: " + messageID +
                "\nHash: " + messageHash +
                "\nTo: " + recipientCell +
                "\nMessage: " + messageText +
                "\nSent: " + sent +
                "\nReceived: " + received +
                "\nRead: " + read;
    }
}

