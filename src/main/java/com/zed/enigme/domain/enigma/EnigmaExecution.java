package com.zed.enigme.domain.enigma;

import java.io.Serializable;
import java.time.Duration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.zed.enigme.domain.User;

/**
 * A enigma execution
 */
@Entity
@Table(name = "enigma_execution")
public class EnigmaExecution implements Serializable {
	
	private static final long serialVersionUID = -3332080265129685731L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@ManyToOne
	private User user;

	@NotNull
	@ManyToOne
	private Enigma enigma;

	@NotNull
	@Column
	private Long duration;

	@NotNull
	@Column
	private String answer;

	@NotNull
	@Column
	private boolean isOk;

	public EnigmaExecution() {
	}

	public EnigmaExecution(User user,
			Enigma enigma,
			Long duration,
			String answer,
			boolean isOk) {
		this.user = user;
		this.enigma = enigma;
		this.duration = duration;
		this.answer = answer;
		this.isOk = isOk;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Enigma getEnigma() {
		return enigma;
	}

	public void setEnigma(Enigma enigma) {
		this.enigma = enigma;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

}
