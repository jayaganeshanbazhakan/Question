package main.java.template.accessor;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
	
import com.sendgrid.Content;
import com.sendgrid.Response;

import main.java.template.dto.AnswerDataDto;
import main.java.template.dto.SingleAnswerDto;
import main.java.template.dto.UserDataDto;
import main.java.template.mail.EmailService;
import main.java.template.model.QuestionDataModel;

@Service
public class QuestionTemplateAccessor {


	@Autowired
	EmailService emailService;

	public List<QuestionDataModel> getQuestionForUsers(){

		QuestionDataModel questionDataModel = new QuestionDataModel();
		questionDataModel.setQuestion_no(1);
		questionDataModel.setQuestion("Capital of India");
		questionDataModel.setOptions(Arrays.asList("Delhi","Mumbai"));
		questionDataModel.setCorrect_answer("Delhi");

		QuestionDataModel questionDataModel1 = new QuestionDataModel();
		questionDataModel1.setQuestion_no(2);
		questionDataModel1.setQuestion("Capital of USA");
		questionDataModel1.setOptions(Arrays.asList("Washington","California"));
		questionDataModel1.setCorrect_answer("Washington");

		List<QuestionDataModel> questionDataModelList = Arrays.asList(questionDataModel,questionDataModel1);
		Collections.shuffle(questionDataModelList);
		return questionDataModelList;

	}

	public Map<String,String> verifyUserAnswers(AnswerDataDto answerDataDto){
		Map<String,String> answerDataMap = new HashMap<>();
		Map<String,String> userAnswerMap = answerDataDto.getAnswerDataMap();

		List<QuestionDataModel> questionDataModelList = getQuestionForUsers();
		AtomicInteger correctAnswer = new AtomicInteger(0);
		AtomicInteger wrongAnswer = new AtomicInteger(0);

		questionDataModelList.stream().forEach(questionDataModel->{
			String correct_answer = questionDataModel.getCorrect_answer();
			String user_answer = userAnswerMap.get(String.valueOf(questionDataModel.getQuestion_no()));
			if(user_answer.equals(correct_answer)) {
				correctAnswer.getAndIncrement();
			}else {
				wrongAnswer.getAndIncrement();
			}
		});

		answerDataMap.put("cans", String.valueOf(correctAnswer.get()));
		answerDataMap.put("wans", String.valueOf(wrongAnswer));

		return answerDataMap;

	}


	public boolean verifySingleAnswer(SingleAnswerDto singleAnswerDto) {
		AtomicBoolean result = new AtomicBoolean(false);
		List<QuestionDataModel> questionDataModelList = getQuestionForUsers();
		questionDataModelList.stream().filter(predicate->{
			return predicate.getQuestion_no() == Integer.parseInt(singleAnswerDto.getQuestion_no());
		}).collect(Collectors.toList()).stream().forEach(action->{
			if(action.getCorrect_answer().equals(singleAnswerDto.getUser_answer()))
				result.set(true);;
		});
		return result.get();
	}

	public boolean registerNewUser(UserDataDto userDataDto) {

		if(StringUtils.isEmpty(userDataDto.getFirst_name()) ||
				StringUtils.isEmpty(userDataDto.getEmail_id()) ||
				StringUtils.isEmpty(userDataDto.getPassword()))
			return false;

		if(!checkEmailIdIsValid(userDataDto.getEmail_id()))
			return false;

		Response response = emailService.sendEmail("jayaganes29@gmail.com", userDataDto.getEmail_id(), "User Registration Confirmation", new Content("text/html", " <a href=\"http://localhost:8090/confirm/"
				+ userDataDto.getUser_id()
				+ "\">Click Me to confirm</a> "));

		if(Objects.nonNull(response)&&response.getStatusCode()==200)
			return true;

		return false;
	}

	private boolean checkEmailIdIsValid(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
				"[a-zA-Z0-9_+&*-]+)*@" + 
				"(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
				"A-Z]{2,7}$"; 

		Pattern pattern = Pattern.compile(emailRegex); 
		return pattern.matcher(email).matches(); 
	}
}
