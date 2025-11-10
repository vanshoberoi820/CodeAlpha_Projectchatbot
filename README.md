# CodeAlpha_Projectchatbot
# Java Chatbot with GUI

A simple intelligent chatbot built with Java Swing that uses NLP techniques and rule-based responses for interactive communication.

## Features

- Pattern-based Natural Language Processing
- Rule-based FAQ responses
- Sentiment analysis (positive/negative detection)
- Modern Swing GUI interface
- Real-time chat with timestamps

## Requirements

- Java JDK 8 or higher
- No external libraries needed

## How to Run

### Using Command Line

```bash
# Compile
javac ChatbotGUI.java

# Run
java ChatbotGUI
```

### Using IDE

1. Open your IDE (IntelliJ IDEA, Eclipse, VS Code)
2. Create a new Java project
3. Add `ChatbotGUI.java` to the project
4. Run the main method

## Sample Queries

Try asking:
- "Hello" or "Hi"
- "What are your hours?"
- "Where are you located?"
- "What's the pricing?"
- "Tell me about features"
- "How can I contact you?"
- "What payment methods do you accept?"
- "How does shipping work?"

## Customization

To add new responses, edit the `trainBot()` method in `ChatbotEngine` class:

```java
// Add new response
responses.put("category", Arrays.asList("Response 1", "Response 2"));

// Add matching pattern
patterns.put(Pattern.compile(".*\\b(keyword)\\b.*", Pattern.CASE_INSENSITIVE), "category");
```

## Project Structure

```
ChatbotGUI.java
├── ChatbotEngine - NLP logic and responses
└── ChatbotGUI - Swing interface
```

## Troubleshooting

**Won't compile?** Check Java version with `java -version`

**GUI not showing?** Ensure you're in a graphical environment

**Emojis not displaying?** Normal on some systems, functionality still works

## Author

Created for educational purposes demonstrating Java GUI and basic NLP techniques.
