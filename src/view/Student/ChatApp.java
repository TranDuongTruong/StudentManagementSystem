package view.Student;

import com.google.gson.Gson;


import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ChatApp extends JFrame {

    private final JTextField userInputField;
    private final JTextArea chatArea;
    private final JButton sendButton;

    public ChatApp() {
        // Initialize JFrame
        setTitle("Chat with GPT-3.5");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize GUI components
        userInputField = new JTextField();
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        sendButton = new JButton("Send");

        // Set layout manager
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components to the frame using GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(10, 10, 0, 10);
        add(userInputField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(10, 0, 0, 10);
        add(sendButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 10, 10, 10);
        add(chatScrollPane, gbc);

        // Set up action listener for the send button
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        // Display the frame
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void sendMessage() {
        // Get user input
        String userMessage = userInputField.getText().trim();

        if (!userMessage.isEmpty()) {
            // Display user message in the chat area
            appendToChatArea("You: " + userMessage);

            // Send user message to GPT-3.5 API
            String gptResponse = getGptResponse(userMessage);

            // Parse JSON response
            Gson gson = new Gson();
            JsonObject jsonResponse = gson.fromJson(gptResponse, JsonObject.class);
            String gptResult = jsonResponse.get("result").getAsString();

            // Display GPT-3.5 response in the chat area
            appendToChatArea("GPT-3.5: " + gptResult);

            // Clear the user input field
            userInputField.setText("");
        }
    }

    private void appendToChatArea(String message) {
        chatArea.append(message + "\n");
    }

    private String getGptResponse(String userMessage) {
        try {
            // Construct the HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://open-ai21.p.rapidapi.com/conversationgpt35"))
                    .header("content-type", "application/json")
                    .header("X-RapidAPI-Key", "8db8d4c875msh55b2ab30998b98ap118417jsnd66c078c7608")
                    .header("X-RapidAPI-Host", "open-ai21.p.rapidapi.com")
                    .method("POST", HttpRequest.BodyPublishers.ofString("""
                            {
                                "messages": [
                                    {
                                        "role": "user",
                                        "content": "%s"
                                    }
                                ],
                                "web_access": false,
                                "system_prompt": "",
                                "temperature": 0.9,
                                "top_k": 5,
                                "top_p": 0.9,
                                "max_tokens": 256
                            }""".formatted(userMessage)))
                    .build();

            // Send the HTTP request and get the response
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // Return the GPT-3.5 response
            return response.body();
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            return "Error occurred: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChatApp();
            }
        });
    }
}