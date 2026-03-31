from pydantic import BaseModel, Field


class WeakSection(BaseModel):
    section_name: str = Field(description="Name of the weak section in the resume")
    callout: str = Field(description="Specific criticism of what's wrong with this section")


class ResumeRoast(BaseModel):
    overall_score: int = Field(ge=0, le=100, description="Overall resume score from 0 to 100")
    category_scores: dict[str, int] = Field(
        description=(
            "Scores (0-100) for each category: skills_presentation, "
            "experience_clarity, formatting, keyword_strength, impact_language"
        )
    )
    roast: str = Field(description="Brutally honest feedback on the resume")
    weakest_sections: list[WeakSection] = Field(
        description="The weakest sections of the resume with specific callouts"
    )
    rewrites: list[str] = Field(
        description="Improved bullet points to replace the worst ones in the resume"
    )
    verdict: str = Field(description="One-line savage summary of the resume")