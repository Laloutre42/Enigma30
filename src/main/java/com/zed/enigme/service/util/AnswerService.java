package com.zed.enigme.service.util;

/**
 * Utility class for answer
 */
public final class AnswerService {

    private AnswerService() {
    }

    public static String refactAnswer(String answer) {
        String result = answer;
        result.trim().replaceAll("/\\s/", "");
        result.replaceAll("-", " ");
        return result;
    }

}
