package main.java.template.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SingleAnswerDto {

	private String user_answer;
	
	private String question_no;

	
	public String getUser_answer() {
		return user_answer;
	}

	public void setUser_answer(String user_answer) {
		this.user_answer = user_answer;
	}
	
	public String getQuestion_no() {
		return question_no;
	}

	public void setQuestion_no(String question_no) {
		this.question_no = question_no;
	}

	@JsonCreator
	SingleAnswerDto(@JsonProperty("uanswer")String answer,@JsonProperty("qstnno")String qstnno){
    	this.user_answer = answer;
    	this.question_no = qstnno;
    	
    }
}
