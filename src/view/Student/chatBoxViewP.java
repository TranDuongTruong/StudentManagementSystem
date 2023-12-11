package view.Student;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class chatBoxViewP extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField userInputField;
    private JTextArea chatArea;

    /**
     * Create the panel.
     */
    public chatBoxViewP() {
        setLayout(null);
        this.setBounds(162, 0, 835, 640);

        userInputField = new JTextField();
        userInputField.setBounds(60, 39, 630, 20);
        add(userInputField);
        userInputField.setColumns(10);

        JButton sendButton = new JButton("Send");
        sendButton.setBounds(710, 38, 89, 23);
        add(sendButton);

        // Wrap the chatArea within a JScrollPane
        JScrollPane chatScrollPane = new JScrollPane();
        chatScrollPane.setBounds(60, 137, 739, 444);
        add(chatScrollPane);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatScrollPane.setViewportView(chatArea);

        // Set up action listener for the send button
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
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
}
