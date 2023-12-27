package com.theokanning.openai.completion.chat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * <p>Each object has a role (either "system", "user", or "assistant") and content (the content of the message). Conversations can be as short as 1 message or fill many pages.</p>
 * <p>Typically, a conversation is formatted with a system message first, followed by alternating user and assistant messages.</p>
 * <p>The system message helps set the behavior of the assistant. In the example above, the assistant was instructed with "You are a helpful assistant."<br>
 * The user messages help instruct the assistant. They can be generated by the end users of an application, or set by a developer as an instruction.<br>
 * The assistant messages help store prior responses. They can also be written by a developer to help give examples of desired behavior.
 * </p>
 *
 * see <a href="https://platform.openai.com/docs/guides/chat/introduction">OpenAi documentation</a>
 */
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class ChatMessage {

	/**
	 * Must be either 'system', 'user', 'assistant' or 'function'.<br>
	 * If it's set to null, it will be set to 'assistant' by default.<br>
	 * You may use {@link ChatMessageRole} enum.
	 */
	String role;
	@JsonInclude() // content should always exist in the call, even if it is null
	String content;
	//name is optional, The name of the author of this message. May contain a-z, A-Z, 0-9, and underscores, with a maximum length of 64 characters.
	String name;

	@JsonProperty("tool_calls")
	List<ChatToolCalls> toolCalls;

	@JsonProperty("function_call")
	ChatFunctionCall functionCall;

	public ChatMessage(String role, String content) {
		this.role = role == null ? "assistant" : role;
		this.content = content;
	}

	public ChatMessage(String role, String content, String name) {
		this.role = role == null ? "assistant" : role;
		this.content = content;
		this.name = name;
	}

}
