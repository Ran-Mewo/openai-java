package com.theokanning.openai.utils;

import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageContent;
import com.theokanning.openai.completion.chat.ImageUrl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Vision tool class
 *
 * @author cong
 * @since 2023/11/17
 */
public class VisionUtil {

    private static final Pattern pattern = Pattern.compile("(https?://\\S+)");

    public static ChatMessage convertForVision(ChatMessage msg) {
        List<ChatMessageContent> content = new ArrayList<>();
        String sourceText = msg.getStringContent();
        // Regular expression to match image URLs
        Matcher matcher = pattern.matcher(sourceText);
        // Find image URLs and split the string
        int lastIndex = 0;
        while (matcher.find()) {
            // Add the text before the image URL
            if (matcher.start() > lastIndex) {
                content.add(new ChatMessageContent(sourceText.substring(lastIndex, matcher.start()).trim()));
            }
            // Add the image URL
            ImageUrl imageUrl = new ImageUrl();
            imageUrl.setUrl(matcher.group());
            content.add(new ChatMessageContent(imageUrl));
            lastIndex = matcher.end();
        }
        // Add the remaining text
        if (lastIndex < sourceText.length()) {
            content.add(new ChatMessageContent(sourceText.substring(lastIndex).trim()));
        }
        return new ChatMessage(msg.getRole(), content, msg.getName());
    }
}