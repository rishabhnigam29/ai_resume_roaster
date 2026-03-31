# Resume Roaster — Java (Spring AI)

AI-powered resume roaster using Spring Boot + Spring AI.

## Prerequisites

- Java 17+
- Maven
- OpenAI API key

## Setup

Set your API key in `src/main/resources/application.properties`:

```properties
spring.ai.openai.api-key=sk-your-key-here
```

Or use an environment variable:

```bash
export OPENAI_API_KEY=sk-your-key-here
```

## Build

```bash
cd 01_resume_roaster/java
mvn clean package -DskipTests
```

## Run

```bash
mvn spring-boot:run
```

Server starts on `http://localhost:8080`.

## Test

```bash
curl -X POST http://localhost:8080/api/roast \
  -F "file=@/path/to/resume.pdf"
```

## Change Model

Edit `src/main/resources/application.properties`:

```properties
spring.ai.openai.chat.options.model=gpt-4o-mini
```
