/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapplicationpart3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

public class ChatApplicationPart3 {

    public static void main(String[] args) {
        String username = JOptionPane.showInputDialog("Enter Username:");
        String password = JOptionPane.showInputDialog("Enter Password:");
        String cell = JOptionPane.showInputDialog("Enter Cell Phone Number (+27xxxxxxxxx):");

        Login user = new Login(username, password, cell);
        String regMessage = user.registerUser();
        JOptionPane.showMessageDialog(null, regMessage);
        if (!regMessage.equals("User successfully registered.")) return;

        String loginUser = JOptionPane.showInputDialog("Enter Login Username:");
        String loginPass = JOptionPane.showInputDialog("Enter Login Password:");
        boolean isLoggedIn = user.loginUser(loginUser, loginPass);

        String firstName = JOptionPane.showInputDialog("Enter First Name:");
        String lastName = JOptionPane.showInputDialog("Enter Last Name:");
        String loginMsg = user.returnLoginStatus(firstName, lastName, isLoggedIn);
        JOptionPane.showMessageDialog(null, loginMsg);
        if (!isLoggedIn) return;

        JOptionPane.showMessageDialog(null, "Welcome to QuickChat!");

        int totalMsgs = Integer.parseInt(JOptionPane.showInputDialog("How many messages would you like to send?"));

        List<Messages> sentMessages = new ArrayList<>();
        List<Messages> storedMessages = new ArrayList<>();
        List<Messages> discardedMessages = new ArrayList<>();

        for (int i = 0; i < totalMsgs; i++) {
            int choice = Integer.parseInt(JOptionPane.showInputDialog("""
                Choose an option:
                1. Send Message
                2. Show Recently Sent Messages
                3. Quit
            """));

            if (choice == 1) {
                String recipient = JOptionPane.showInputDialog("Enter Recipient Cell Number:");
                String msgText = JOptionPane.showInputDialog("Enter your message (max 250 chars):");
                if (msgText.length() > 250) {
                    JOptionPane.showMessageDialog(null, "Message exceeds 250 characters by " + (msgText.length() - 250));
                    continue;
                }
                Messages msg = new Messages(recipient, msgText);

                int sendChoice = Integer.parseInt(JOptionPane.showInputDialog("""
                    1. Send Message
                    2. Disregard Message
                    3. Store Message
                """));

                String result = msg.sendMessageOption(sendChoice);
                JOptionPane.showMessageDialog(null, result);

                switch (sendChoice) {
                    case 1 -> sentMessages.add(msg);
                    case 2 -> discardedMessages.add(msg);
                    case 3 -> {
                        storedMessages.add(msg);
                        MessageStorage.saveToJson(msg);
                    }
                }
                JOptionPane.showMessageDialog(null, msg.toString());

            } else if (choice == 2) {
                if (sentMessages.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No messages sent yet.");
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (Messages m : sentMessages) {
                        sb.append(m.toString()).append("\n----------------\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                }
            } else if (choice == 3) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid option.");
            }
        }

        JOptionPane.showMessageDialog(null, "Total Messages Created: " + Messages.returnTotalMessages());

        // Part 3: Advanced Features
        StringBuilder allSent = new StringBuilder("Sent Messages:\n");
        for (Messages m : sentMessages) {
            allSent.append("To: ").append(m.getRecipientCell()).append("\n")
                   .append("Message: ").append(m.getMessageText()).append("\n---\n");
        }
        JOptionPane.showMessageDialog(null, allSent.toString());

        Messages longestMsg = sentMessages.stream()
                .max((m1, m2) -> Integer.compare(m1.getMessageText().length(), m2.getMessageText().length()))
                .orElse(null);
        if (longestMsg != null) {
            JOptionPane.showMessageDialog(null, "Longest message: " + longestMsg.getMessageText());
        }

        String searchID = JOptionPane.showInputDialog("Enter a Message ID to search:");
        boolean found = false;
        for (Messages m : sentMessages) {
            if (m.getMessageID().equals(searchID)) {
                JOptionPane.showMessageDialog(null, "Found message:\nRecipient: " + m.getRecipientCell() + "\nMessage: " + m.getMessageText());
                found = true;
                break;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(null, "Message ID not found.");
        }

        String recipientSearch = JOptionPane.showInputDialog("Enter recipient number to search for:");
        List<String> messagesToRecipient = sentMessages.stream()
                .filter(m -> m.getRecipientCell().equals(recipientSearch))
                .map(Messages::getMessageText)
                .collect(Collectors.toList());

        if (messagesToRecipient.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No messages found for that recipient.");
        } else {
            StringBuilder sb = new StringBuilder("Messages to " + recipientSearch + ":\n");
            messagesToRecipient.forEach(msg -> sb.append("- ").append(msg).append("\n"));
            JOptionPane.showMessageDialog(null, sb.toString());
        }

        String delHash = JOptionPane.showInputDialog("Enter a message hash to delete:");
        Messages toDelete = null;
        for (Messages m : sentMessages) {
            if (m.getMessageHash().equals(delHash)) {
                toDelete = m;
                break;
            }
        }
        if (toDelete != null) {
            sentMessages.remove(toDelete);
            JOptionPane.showMessageDialog(null, "Message deleted: " + toDelete.getMessageText());
        } else {
            JOptionPane.showMessageDialog(null, "No message found with that hash.");
        }

        StringBuilder finalReport = new StringBuilder("Final Message Report:\n\n");
        for (Messages m : sentMessages) {
            finalReport.append("Hash: ").append(m.getMessageHash()).append("\n")
                       .append("Recipient: ").append(m.getRecipientCell()).append("\n")
                       .append("Message: ").append(m.getMessageText()).append("\n----------------\n");
        }
        JOptionPane.showMessageDialog(null, finalReport.toString());
    }
}
