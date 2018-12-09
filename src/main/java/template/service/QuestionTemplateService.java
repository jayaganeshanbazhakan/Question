package main.java.template.service;

import java.util.List;
import java.util.Map;

import main.java.template.dto.AnswerDataDto;
import main.java.template.dto.SingleAnswerDto;
import main.java.template.dto.UserDataDto;
import main.java.template.model.QuestionDataModel;

public interface QuestionTemplateService {
	
	public List<QuestionDataModel> getQuestionForUser();
	
	public Map<String,String> verifyUserAnswers(AnswerDataDto answerDataDto);
	
	public boolean verifySingleAnswer(SingleAnswerDto singleAnswerDto);
	
	public boolean registerNewUser(UserDataDto userDataDto);

}
