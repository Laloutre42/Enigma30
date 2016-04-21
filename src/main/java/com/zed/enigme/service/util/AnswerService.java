package com.zed.enigme.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.Normalizer;

/**
 * Utility class for answer
 */
public final class AnswerService {

    private static final Logger log = LoggerFactory.getLogger(AnswerService.class);

    private AnswerService() {
    }

    public static String refactAnswer(String answer) {
        String result = answer;
        result.trim().replaceAll("/\\s/", "");
        result.replaceAll("-", " ");
        result = removeAccent(result);
        return result.toUpperCase();
    }

    /**
     * Return true is answer has been found.
     * Each word in the real answer should be in the answer to check.
     *
     * @param answerToCheck
     * @param realAnswer
     * @return
     */
    public static boolean hasBeenFound(String answerToCheck, String realAnswer) {

        answerToCheck = refactAnswer(answerToCheck);
        realAnswer = refactAnswer(realAnswer);

        log.info("[hasBeenFound] refactAnswer, answerToCheck : -{}-", answerToCheck);
        log.info("[hasBeenFound] refactAnswer, realAnswer : -{}-", realAnswer);

        for (String word : realAnswer.split("\\s")) {

            if (!answerToCheck.contains(word)) {
                return false;
            }
        }
        return true;
    }

    public static String removeAccent(String source) {
        return Normalizer.normalize(source, Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]", "");
    }

}
