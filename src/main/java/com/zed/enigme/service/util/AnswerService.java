package com.zed.enigme.service.util;

import com.zed.enigme.domain.enigma.AnswerResult;
import info.debatty.java.stringsimilarity.NormalizedLevenshtein;
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
    public static AnswerResult hasBeenFound(String answerToCheck, String realAnswer) {

        AnswerResult answerResult = new AnswerResult(true, 0, 0);

        answerToCheck = refactAnswer(answerToCheck);
        realAnswer = refactAnswer(realAnswer);

        log.info("[hasBeenFound] refactAnswer, answerToCheck : -{}-", answerToCheck);
        log.info("[hasBeenFound] refactAnswer, realAnswer : -{}-", realAnswer);

        NormalizedLevenshtein normalizedLevenshtein = new NormalizedLevenshtein();
        double distance = normalizedLevenshtein.distance(answerToCheck, realAnswer);

        answerResult.setDistanceInPercentage(Math.round((1d - distance) * 100d));
        log.info("[hasBeenFound] normalizedLevenshtein is : -{}%-", answerResult.getDistanceInPercentage());

        for (String word : realAnswer.split("\\s")) {

            if (!answerToCheck.contains(word)) {
                answerResult.setHasBeenFound(false);
            } else {
                answerResult.setNbWordsFound(answerResult.getNbWordsFound() + 1);
            }
        }
        return answerResult;
    }

    public static String removeAccent(String source) {
        return Normalizer.normalize(source, Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]", "");
    }

}
