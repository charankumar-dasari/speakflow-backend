# SpeakFlow – Backend

This repository contains the backend API for SpeakFlow, a speech-to-English translation application.

The backend is responsible for receiving the speech text from the React frontend, sending it to Gemini for translation, saving the translation in MySQL, and providing APIs for translation history.

## Backend Architecture

The application follows a simple layered structure:

```text
React Frontend
      │
      │ REST API
      ▼
Spring Boot Backend
      │
      ├── Controller
      │
      ├── Service
      │      │
      │      └── Gemini Translation API
      │
      ├── Repository
      │
      └── MySQL Database
```

## Tech Stack

* Java
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* MySQL
* Google Gemini API
* Maven
* Docker
* Render

## Main Features

* REST API for speech translation
* Gemini-based English translation
* Automatic language understanding
* Translation history
* Delete translation history
* MySQL database persistence
* CORS configuration for the React frontend
* Docker-based deployment

## API Endpoints

### Translate Speech

```http
POST /api/speech/translate
```

Request:

```json
{
  "detectedLanguage": "auto",
  "originalText": "Hello how are you"
}
```

Response:

```json
{
  "id": 1,
  "detectedLanguage": "auto",
  "originalText": "Hello how are you",
  "englishText": "Hello, how are you?",
  "createdAt": "2026-08-09T13:30:00"
}
```

### Get Translation History

```http
GET /api/speech/history
```

Returns the translations stored in the database.

### Delete Translation

```http
DELETE /api/speech/history/{id}
```

Deletes a translation using its database ID.

## Project Structure

```text
speakflow-backend/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── speakflow/
│       │           └── backend/
│       │               │
│       │               ├── config/
│       │               │   └── WebConfig.java
│       │               │
│       │               ├── controller/
│       │               │   └── SpeechTranslationController.java
│       │               │
│       │               ├── dto/
│       │               │   ├── TranslationRequest.java
│       │               │   ├── TranslationResponse.java
│       │               │   ├── TranslationApiRequest.java
│       │               │   └── TranslationApiResponse.java
│       │               │
│       │               ├── entity/
│       │               │   └── SpeechTranslation.java
│       │               │
│       │               ├── repository/
│       │               │   └── SpeechTranslationRepository.java
│       │               │
│       │               └── service/
│       │                   ├── SpeechTranslationService.java
│       │                   ├── GeminiTranslationService.java
│       │                   └── GeminiService.java
│       │
│       └── resources/
│           └── application.properties
│
├── Dockerfile
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .gitignore
└── README.md
```

## Gemini Integration

SpeakFlow uses the Gemini API to convert the recognized speech into natural English.

The backend sends the recognized speech along with instructions to:

* Detect the input language
* Understand mixed-language speech
* Translate non-English speech into English
* Preserve the original meaning
* Return only the translated sentence

The API key is not stored in the source code.

For local development, the application reads the Gemini API key from an environment variable:

```text
GEMINI_API_KEY
```

For deployment, the same variable should be configured in the hosting platform's environment settings.

## Database

SpeakFlow uses MySQL to store translation history.

The main stored information includes:

* Translation ID
* Detected language
* Original speech text
* English translation
* Creation time

The application uses Spring Data JPA and Hibernate for database operations.

## Running Locally

Clone the repository:

```bash
git clone https://github.com/charankumar-dasari/speakflow-backend.git
```

Go into the project:

```bash
cd speakflow-backend
```

Build the project:

```bash
./mvnw clean package
```

On Windows:

```bash
mvnw.cmd clean package
```

Run the application:

```bash
./mvnw spring-boot:run
```

Or run the generated JAR file:

```bash
java -jar target/backend-0.0.1-SNAPSHOT.jar
```

## Environment Variables

The following values should be configured locally or in the deployment environment:

```text
GEMINI_API_KEY
DB_URL
DB_USERNAME
DB_PASSWORD
```

Do not commit real API keys or database passwords to GitHub.

For local development, these values can be provided through environment variables or a local configuration file that is excluded from Git.

## Deployment

The backend is deployed as a Docker-based Spring Boot service on Render.

Live backend:

https://speakflow-backend-8bs6.onrender.com

The backend connects to a MySQL database hosted on Aiven.

Deployment flow:

```text
GitHub
   │
   ▼
Render
   │
   ▼
Docker Container
   │
   ▼
Spring Boot
   │
   ├── Gemini API
   │
   └── Aiven MySQL
```

## CORS

The backend allows requests from the local React development server and the deployed SpeakFlow frontend.

Local frontend:

```text
http://localhost:5173
```

Production frontend:

```text
https://speakflow-frontend.vercel.app
```

## Error Handling

The backend returns an error when:

* The translation request is invalid
* Gemini does not return a translation
* A requested history record does not exist
* An external translation request fails

## Author

**Charan Kumar Dasari**

B.Tech – Computer Science & Engineering
2025 Graduate
