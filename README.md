# Resume Roaster

An AI-powered resume critic that gives brutally honest, structured feedback on your resume. No sugarcoating — just scores, roasts, and actionable rewrites.

Built in both **Python** (LiteLLM + Pydantic) and **Java** (Spring Boot + Spring AI) to compare how each language handles the same AI agent task.

## How It Works

```
Resume PDF → Text Extraction → LLM + Output Schema → Structured Roast
```

A single LLM call with structured output. No chains, no memory, no RAG. The agent:

1. Extracts text from a resume PDF
2. Sends it to an LLM with a strict output schema and a system prompt that enforces brutal honesty
3. Returns a structured result with scores, feedback, and rewrites

### Output Structure

| Field | Description |
|-------|-------------|
| `overall_score` | 0–100 rating |
| `category_scores` | Skills presentation, experience clarity, formatting, keyword strength, impact language |
| `roast` | The brutal, no-holds-barred feedback |
| `weakest_sections` | Specific callouts of the worst parts |
| `rewrites` | Your weakest bullet points, rewritten properly |
| `verdict` | One devastating sentence summing it up |

## Quick Start

### Python

```bash
cd python

# Create .env with your API key
echo "OPENAI_API_KEY=sk-your-key-here" > .env

# Install dependencies
uv sync

# Run
uv run python main.py /path/to/resume.pdf
```

### Java

```bash
cd java

# Set your API key
export OPENAI_API_KEY=sk-your-key-here

# Build and run
mvn clean package -DskipTests
mvn spring-boot:run
```

Then send a resume:

```bash
curl -X POST http://localhost:8080/api/roast \
  -F "file=@/path/to/resume.pdf"
```

## Python vs Java

| | Python | Java |
|---|---|---|
| **Files** | 4 | 8 |
| **Lines of code** | ~129 | ~214 |
| **Structured output** | Manual (schema in prompt + Pydantic) | Built-in (`.entity()`) |
| **Provider flexibility** | Any provider via LiteLLM | Spring AI starters per provider |
| **Output format** | CLI | REST API |
| **Type safety** | Runtime (Pydantic) | Compile-time + runtime |
| **Production path** | Needs FastAPI/Flask wrapper | REST API out of the box |

Both use the **same system prompt** (`system_prompt.txt`) and the **same data model**, so the LLM gets identical instructions regardless of implementation.

## Swap LLM Providers

**Python** — change the model string in `roaster.py`:

```python
roast_resume(text, model="gpt-4o-mini")       # OpenAI
roast_resume(text, model="ollama/llama3")      # Local
roast_resume(text, model="anthropic/claude-sonnet-4-6")  # Anthropic
```

**Java** — edit `application.properties`:

```properties
spring.ai.openai.chat.options.model=gpt-4o-mini
```

## Tech Stack

**Python:** LiteLLM, Pydantic, PyMuPDF, python-dotenv
**Java:** Spring Boot 3.4, Spring AI 1.0, Apache PDFBox 3.0, Jackson

## License

MIT
