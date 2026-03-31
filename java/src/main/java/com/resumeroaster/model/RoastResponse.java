package com.resumeroaster.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.util.List;

public record RoastResponse(

        @JsonPropertyDescription("Overall resume score from 0 to 100")
        int overallScore,

        @JsonPropertyDescription("Breakdown of scores by category")
        CategoryScores categoryScores,

        @JsonPropertyDescription("Brutally honest, no-holds-barred feedback on the resume")
        String roast,

        @JsonPropertyDescription("List of the weakest sections with specific callouts")
        List<WeakSection> weakestSections,

        @JsonPropertyDescription("List of rewritten bullet points that improve on the originals")
        List<String> rewrites,

        @JsonPropertyDescription("One-line savage summary verdict of the resume")
        String verdict
) {
}