package com.resumeroaster.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record CategoryScores(

        @JsonPropertyDescription("Score for how well skills are presented and organized (0-100)")
        int skillsPresentation,

        @JsonPropertyDescription("Score for clarity of experience descriptions (0-100)")
        int experienceClarity,

        @JsonPropertyDescription("Score for overall formatting and visual structure (0-100)")
        int formatting,

        @JsonPropertyDescription("Score for presence of industry keywords and ATS optimization (0-100)")
        int keywordStrength,

        @JsonPropertyDescription("Score for use of strong action verbs and quantified achievements (0-100)")
        int impactLanguage
) {
}