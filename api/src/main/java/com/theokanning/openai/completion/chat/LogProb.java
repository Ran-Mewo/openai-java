package com.theokanning.openai.completion.chat;

import java.util.List;

public class LogProb {
    private String token;
    private double logprob;
    private List<Integer> bytes;
    private List<LogProb> top_logprobs;
}
