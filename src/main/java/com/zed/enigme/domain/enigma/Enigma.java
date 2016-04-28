package com.zed.enigme.domain.enigma;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A enigma.
 */
@Entity
@Table(name = "enigma")
public class Enigma implements Serializable {

    private static final long serialVersionUID = -1390989831026277694L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "number")
    private Integer number;

    @NotNull
    @Column(name = "question", length = 255, unique = false, nullable = false)
    private String question;

    @NotNull
    @Column(name = "answer", length = 255, unique = false, nullable = false)
    private String answer;

    @Column(name = "indice", length = 255, unique = false, nullable = false)
    private String indice;


    public Long getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getIndice() {
        return indice;
    }

    public void setIndice(String indice) {
        this.indice = indice;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Enigma{");
        sb.append("id=").append(id);
        sb.append(", number=").append(number);
        sb.append(", question='").append(question).append('\'');
        sb.append(", answer='").append(answer).append('\'');
        sb.append(", indice='").append(indice).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
