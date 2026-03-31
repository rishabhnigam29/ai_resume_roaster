package com.resumeroaster.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record WeakSection(

        @JsonPropertyDescription("Name of the weak section in the resume")
        String sectionName,

        @JsonPropertyDescription("Specific callout explaining what is wrong with this section")
        String callout
) {
}