package main.java.template.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.template.accessor.QuestionTemplateAccessor;
import main.java.template.dto.AnswerDataDto;
import main.java.template.dto.SingleAnswerDto;
import main.java.template.dto.UserDataDto;
import main.java.template.model.QuestionDataModel;

@Service
public class QuestionTemplateServiceImpl implements QuestionTemplateService{

	@Autowired
	QuestionTemplateAccessor questionTemplateAccessor;
	
	@Override
	public List<QuestionDataModel> getQuestionForUser() {
		return questionTemplateAccessor.getQuestionForUsers();
	}

	@Override
	public Map<String, String> verifyUserAnswers(AnswerDataDto answerDataDto) {
		return questionTemplateAccessor.verifyUserAnswers(answerDataDto);
	}

	@Override
	public boolean verifySingleAnswer(SingleAnswerDto singleAnswerDto) {
		return questionTemplateAccessor.verifySingleAnswer(singleAnswerDto);
	}

	@Override
	public boolean registerNewUser(UserDataDto userDataDto) {
		return questionTemplateAccessor.registerNewUser(userDataDto);
	}

}
