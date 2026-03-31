# Resume Roaster — Python

AI-powered resume roaster using LiteLLM + Pydantic.

## Prerequisites

- [uv](https://docs.astral.sh/uv/) (Python package manager)
- OpenAI API key

## Setup

```bash
cd ai_resume_roaster/python

# Create .env file with your API key
echo "OPENAI_API_KEY=sk-your-key-here" > .env

# Install dependencies
uv sync
```

## Run

```bash
uv run python main.py /path/to/resume.pdf
```

## Switch LLM Provider

Edit the model string in `roaster.py`:

```python
# OpenAI
roast_resume(text, model="gpt-4o-mini")

# Ollama (local)
roast_resume(text, model="ollama/llama3")

# Anthropic
roast_resume(text, model="anthropic/claude-sonnet-4-6")
```