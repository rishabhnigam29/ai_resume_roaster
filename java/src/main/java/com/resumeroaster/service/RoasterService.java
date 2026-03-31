package com.resumeroaster.service;

import com.resumeroaster.model.RoastResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class RoasterService {

    private static final Logger log = LoggerFactory.getLogger(RoasterService.class);

    private final ChatClient chatClient;

    public RoasterService(ChatClient.Builder chatClientBuilder) throws IOException {
        String systemPrompt = new ClassPathResource("system_prompt.txt")
                .getContentAsString(StandardCharsets.UTF_8);

        this.chatClient = chatClientBuilder
                .defaultSystem(systemPrompt)
                .build();
    }

    public RoastResponse roast(String resumeText) {
        log.info("Sending resume text ({} chars) to LLM for roasting", resumeText.length());

        RoastResponse response = chatClient.prompt()
                .user(u -> u.text("""
                        Roast the following resume. Be brutal, be specific, be helpful.

                        --- RESUME START ---
                        {resumeText}
                        --- RESUME END ---
                        """)
                        .param("resumeText", resumeText))
                .call()
                .entity(RoastResponse.class);

        log.info("Roast complete. Overall score: {}/100", response.overallScore());
        return response;
    }
}