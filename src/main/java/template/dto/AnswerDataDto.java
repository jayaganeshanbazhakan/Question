package main.java.template.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class AnswerDataDto {

	Map<String,String> answerDataMap;

    @JsonCreator
    AnswerDataDto(@JsonProperty("dataMap")Map<String,String> dataMap){
    	this.answerDataMap = dataMap;
    	
    }
    
	public Map<String, String> getAnswerDataMap() {
		return answerDataMap;
	}

	public void setAnswerDataMap(Map<String, String> answerDataMap) {
		this.answerDataMap = answerDataMap;
	}
}
