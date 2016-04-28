package com.zed.enigme.domain.enigma;

import com.zed.enigme.enumeration.EnigmaExecutionResult;

/**
 * Created by Arnaud on 28/04/2016.
 */
public class EnigmaExecutionAnswerResult {

    private EnigmaExecutionResult enigmaExecutionResult;
    private AnswerResult answerResult;

    public EnigmaExecutionAnswerResult(EnigmaExecutionResult enigmaExecutionResult, AnswerResult answerResult) {
        this.enigmaExecutionResult = enigmaExecutionResult;
        this.answerResult = answerResult;
    }

    public EnigmaExecutionResult getEnigmaExecutionResult() {
        return enigmaExecutionResult;
    }

    public void setEnigmaExecutionResult(EnigmaExecutionResult enigmaExecutionResult) {
        this.enigmaExecutionResult = enigmaExecutionResult;
    }

    public AnswerResult getAnswerResult() {
        return answerResult;
    }

    public void setAnswerResult(AnswerResult answerResult) {
        this.answerResult = answerResult;
    }
}
