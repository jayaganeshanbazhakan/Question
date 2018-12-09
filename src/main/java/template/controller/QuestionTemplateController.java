package main.java.template.controller;

import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.java.template.dto.AnswerDataDto;
import main.java.template.dto.SingleAnswerDto;
import main.java.template.dto.UserDataDto;
import main.java.template.model.QuestionDataModel;
import main.java.template.service.QuestionTemplateServiceImpl;


/**
 * QuestionTemplateController class is used to handle request and sent back response to the 
 * respective request
 * @author jayaganeshanbazhakan
 *
 */
@RestController
public class QuestionTemplateController {

	@Autowired
	QuestionTemplateServiceImpl questionTemplateService;
	
	/**
	 * getQuestionsForUser is used to return questions for the user
	 * @return questionDataModelList
	 */
	@RequestMapping("v1/rest/questions")
	public List<QuestionDataModel> getQuestionsForUser(){
		return questionTemplateService.getQuestionForUser();
	}
	
	/**
	 * verifyUserAnswers method is used to check all the answers written by the users
	 * @param answerDataDto
	 * @return answerDataMap
	 */
	@PostMapping("v1/rest/questions/verifyAll")
	public Map<String, String> verifyUserAnswers(@RequestBody AnswerDataDto answerDataDto){
		return questionTemplateService.verifyUserAnswers(answerDataDto);
	}
	
	/**
	 * verifySingleAnswer is used to check whether the answer selected for the single question is correct or not
	 * @param singleAnswerDto
	 * @return result
	 */
	@PostMapping("v1/rest/questions/verify")
	public boolean verifySingleAnswer(@RequestBody SingleAnswerDto singleAnswerDto){
		return questionTemplateService.verifySingleAnswer(singleAnswerDto);
	}
	
	/**
	 * registerNewUser method is used to save user data and to send confirmation mail
	 * @param userDataDto
	 * @return true/false
	 */
	@PostMapping("v1/rest/register")
	public boolean registerNewUser(@RequestBody UserDataDto userDataDto) {
		return questionTemplateService.registerNewUser(userDataDto);
	}
	
	/**
	 * confirmNewUser is used to get user confirmation received by clicking the
	 * confirmation mail
	 * @param key
	 * @return true/false
	 */
	@GetMapping("confirm/{key}")
	public boolean confirmNewUser(@PathParam("key") String key) {
		return true;
	}
	
	
}
