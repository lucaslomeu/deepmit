# DeepMit 🤖

Welcome to DeepMit! This project is a simple chat application that interacts with an AI model to provide responses to user inputs. The application also maintains a history of the chat interactions.

## Features ✨

- **Start Chat**: Initiate a chat session with the AI.
- **Show History**: Display the history of chat interactions.
- **Export History**: Export the chat history in text, JSON, or CSV format.
- **Clear History**: Clear all history.
- **Time**: Print current time.
- **Help**: See all commands.
- **Exit**: Exit the application.

## Getting Started 🚀

### Prerequisites

- Java 11 or higher
- Maven

### Installation

1. Clone the repository:

   ```sh
   git clone https://github.com/lucaslomeu/deepmit.git
   cd deepmit
   ```

2. Set up your API key:
   - Register on [openrouter.ai](https://openrouter.ai) and create an account.
   - Generate an API key from your account dashboard.
   - Create a `.env` file in the root directory of the project and add your API key:
     ```sh
     API_KEY=your_api_key_here
     ```

### Running the Application

1. Compile the project using Maven:

   ```sh
   mvn clean install
   ```

2. Run the application:
   ```sh
   mvn exec:java -Dexec.mainClass="com.deepmit.application.DeepMit"
   ```

### Usage

When you run the application, you will see the following menu:

## Contributing 🤝

Contributions are welcome! Please fork the repository and submit a pull request.

## License 📄

This project is licensed under the MIT License - see the LICENSE file for details.

---

Happy chatting! 💬
