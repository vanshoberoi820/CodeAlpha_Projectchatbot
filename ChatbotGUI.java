// ChatbotEngine.java
import java.util.*;
import java.util.List;
import java.util.Timer;
import java.util.regex.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

class ChatbotEngine {
    private Map<String, List<String>> responses;
    private Map<Pattern, String> patterns;
    private List<String> greetings;
    private Random random;
    
    public ChatbotEngine() {
        this.responses = new HashMap<>();
        this.patterns = new LinkedHashMap<>();
        this.greetings = new ArrayList<>();
        this.random = new Random();
        trainBot();
    }
    
    private void trainBot() {
        // Greetings
        greetings.addAll(Arrays.asList(
            "Hello! How can I assist you today?",
            "Hi there! What can I help you with?",
            "Welcome! I'm here to help you.",
            "Hey! How can I make your day better?"
        ));
        
        responses = new HashMap<>();
        // FAQ Responses - Rule-based
        responses.put("hours", Arrays.asList(
            "We are open Monday to Friday, 9 AM to 6 PM.",
            "Our business hours are 9:00 AM - 6:00 PM on weekdays."
        ));
        responses.put("location", Arrays.asList(
            "We are located at 123 Main Street, Downtown.",
            "You can find us at 123 Main Street in the city center."
        ));
        
        responses.put("contact", Arrays.asList(
            "You can reach us at support@company.com or call (555) 123-4567.",
            "Contact us via email: support@company.com or phone: (555) 123-4567."
        ));
        
        responses.put("price", Arrays.asList(
            "Our pricing starts at $99/month for basic plans. Would you like detailed pricing?",
            "We offer flexible pricing from $99 to $499/month depending on features."
        ));
        
        responses.put("features", Arrays.asList(
            "Our platform includes real-time analytics, 24/7 support, and cloud storage.",
            "Key features: Analytics dashboard, Customer support, Cloud backup, API access."
        ));
        
        responses.put("support", Arrays.asList(
            "Our support team is available 24/7 via chat, email, and phone.",
            "We provide round-the-clock support through multiple channels."
        ));
        
        responses.put("payment", Arrays.asList(
            "We accept credit cards, PayPal, and bank transfers.",
            "Payment methods: Visa, MasterCard, PayPal, Wire Transfer."
        ));
        
        responses.put("shipping", Arrays.asList(
            "We offer free shipping on orders over $50. Standard delivery takes 3-5 days.",
            "Shipping is free for orders above $50, delivered within 3-5 business days."
        ));
        
        responses.put("return", Arrays.asList(
            "We have a 30-day return policy with full refund guarantee.",
            "You can return products within 30 days for a complete refund."
        ));
        
        responses.put("thanks", Arrays.asList(
            "You're welcome! Happy to help!",
            "My pleasure! Feel free to ask anything else.",
            "Glad I could assist you!"
        ));
        
        // Pattern-based responses (NLP-like matching)
        patterns.put(Pattern.compile(".*\\b(hello|hi|hey|greetings)\\b.*", Pattern.CASE_INSENSITIVE), "greeting");
        patterns.put(Pattern.compile(".*\\b(hours|time|open|close)\\b.*", Pattern.CASE_INSENSITIVE), "hours");
        patterns.put(Pattern.compile(".*\\b(where|location|address|find)\\b.*", Pattern.CASE_INSENSITIVE), "location");
        patterns.put(Pattern.compile(".*\\b(contact|email|phone|call|reach)\\b.*", Pattern.CASE_INSENSITIVE), "contact");
        patterns.put(Pattern.compile(".*\\b(price|cost|pricing|how much)\\b.*", Pattern.CASE_INSENSITIVE), "price");
        patterns.put(Pattern.compile(".*\\b(feature|capability|what do|what can)\\b.*", Pattern.CASE_INSENSITIVE), "features");
        patterns.put(Pattern.compile(".*\\b(help|support|assist|problem)\\b.*", Pattern.CASE_INSENSITIVE), "support");
        patterns.put(Pattern.compile(".*\\b(pay|payment|credit card)\\b.*", Pattern.CASE_INSENSITIVE), "payment");
        patterns.put(Pattern.compile(".*\\b(ship|delivery|shipping)\\b.*", Pattern.CASE_INSENSITIVE), "shipping");
        patterns.put(Pattern.compile(".*\\b(return|refund|money back)\\b.*", Pattern.CASE_INSENSITIVE), "return");
        patterns.put(Pattern.compile(".*\\b(thank|thanks|appreciate)\\b.*", Pattern.CASE_INSENSITIVE), "thanks");
        patterns.put(Pattern.compile(".*\\b(bye|goodbye|see you)\\b.*", Pattern.CASE_INSENSITIVE), "goodbye");
        patterns.put(Pattern.compile(".*\\b(name|who are you|what are you)\\b.*", Pattern.CASE_INSENSITIVE), "identity");
    }
    
    public String getResponse(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "I didn't catch that. Could you please say something?";
        }
        
        input = input.trim().toLowerCase();
        
        // Pattern matching (NLP-like behavior)
        for (Map.Entry<Pattern, String> entry : patterns.entrySet()) {
            Matcher matcher = entry.getKey().matcher(input);
            if (matcher.matches()) {
                String category = entry.getValue();
                
                if (category.equals("greeting")) {
                    return greetings.get(random.nextInt(greetings.size()));
                } else if (category.equals("goodbye")) {
                    return "Goodbye! Have a great day!";
                } else if (category.equals("identity")) {
                    return "I'm your intelligent assistant bot, here to help answer your questions!";
                } else if (responses.containsKey(category)) {
                    List<String> responseList = responses.get(category);
                    return responseList.get(random.nextInt(responseList.size()));
                }
            }
        }
        
        // Sentiment analysis (simple)
        if (containsPositiveWords(input)) {
            return "I'm glad you're having a positive experience! How else can I help?";
        }
        
        if (containsNegativeWords(input)) {
            return "I'm sorry you're experiencing issues. Let me connect you with our support team.";
        }
        
        // Default response
        return "I'm not sure I understand. Could you rephrase that? You can ask about hours, location, pricing, features, or support.";
    }
    
    private boolean containsPositiveWords(String input) {
        String[] positive = {"good", "great", "excellent", "awesome", "wonderful", "fantastic", "love", "perfect"};
        for (String word : positive) {
            if (input.contains(word)) return true;
        }
        return false;
    }
    
    private boolean containsNegativeWords(String input) {
        String[] negative = {"bad", "terrible", "awful", "hate", "worst", "horrible", "poor", "disappointing"};
        for (String word : negative) {
            if (input.contains(word)) return true;
        }
        return false;
    }
}

// ChatbotGUI.java


public class ChatbotGUI extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private ChatbotEngine Chatbot;
    private ChatbotEngine chatbot;
    private SimpleDateFormat timeFormat;
    
    public ChatbotGUI() {
        chatbot = new ChatbotEngine();
        timeFormat = new SimpleDateFormat("HH:mm");
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("Intelligent Chatbot Assistant");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Main panel with gradient background
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(240, 242, 245));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(66, 133, 244));
        headerPanel.setPreferredSize(new Dimension(600, 70));
        headerPanel.setLayout(new BorderLayout());
        
        JLabel titleLabel = new JLabel("💬 Smart Assistant");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        
        JLabel statusLabel = new JLabel("Online ●");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        statusLabel.setForeground(new Color(200, 255, 200));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));
        
        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(statusLabel, BorderLayout.EAST);
        
        // Chat display area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        chatArea.setBackground(Color.WHITE);
        chatArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        
        // Input panel
        JPanel inputPanel = new JPanel(new BorderLayout(10, 0));
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        inputField = new JTextField();
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        
        sendButton = new JButton("Send");
        sendButton.setFont(new Font("Arial", Font.BOLD, 14));
        sendButton.setBackground(new Color(66, 133, 244));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        sendButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        sendButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        
        // Add components to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        // Welcome message
        appendBotMessage("Hello! I'm your intelligent assistant. I can help you with:\n" +
                        "• Business hours and location\n" +
                        "• Pricing and features\n" +
                        "• Contact information\n" +
                        "• Support and shipping details\n\n" +
                        "Feel free to ask me anything!");
        
        // Event listeners
        sendButton.addActionListener(e -> sendMessage());
        
        inputField.addActionListener(e -> sendMessage());
        
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }
        });
    }
    
    private void sendMessage() {
        String userInput = inputField.getText().trim();
        
        if (!userInput.isEmpty()) {
            appendUserMessage(userInput);
            inputField.setText("");
            
            // Simulate thinking delay
            javax.swing.Timer timer = new javax.swing.Timer(500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String response = chatbot.getResponse(userInput);
                    appendBotMessage(response);
                    ((javax.swing.Timer)e.getSource()).stop();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
        }
    
    private void appendUserMessage(String message) {
        String time = timeFormat.format(new Date());
        chatArea.append("\n[" + time + "] You: " + message + "\n");
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }
    
    private void appendBotMessage(String message) {
        String time = timeFormat.format(new Date());
        chatArea.append("\n[" + time + "] Bot: " + message + "\n");
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            ChatbotGUI gui = new ChatbotGUI();
            gui.setVisible(true);
        });
    }
}