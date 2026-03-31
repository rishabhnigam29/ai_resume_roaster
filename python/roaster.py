import json
from pathlib import Path

import litellm
from models import ResumeRoast

PROMPT_FILE = Path(__file__).resolve().parent.parent / "system_prompt.txt"
SYSTEM_PROMPT = PROMPT_FILE.read_text() + "\n\nYou MUST respond with valid JSON matching this schema:\n" + json.dumps(ResumeRoast.model_json_schema(), indent=2)


def roast_resume(resume_text: str, model: str = "gpt-4o-mini") -> ResumeRoast:
    """Send resume text to any LLM via LiteLLM and get back a structured roast."""
    response = litellm.completion(
        model=model,
        messages=[
            {"role": "system", "content": SYSTEM_PROMPT},
            {"role": "user", "content": f"Roast this resume:\n\n{resume_text}"},
        ],
        response_format={"type": "json_object"},
    )

    raw = response.choices[0].message.content
    return ResumeRoast.model_validate_json(raw)
