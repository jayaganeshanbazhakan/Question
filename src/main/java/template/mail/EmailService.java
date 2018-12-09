package main.java.template.mail;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

@Service
public class EmailService {

	public Response sendEmail(String from, String to, String subject, Content content) {
		SendGrid sendgrid = new SendGrid("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");
		Mail mail = new Mail(new Email(from), subject, new Email(to), content);

		mail.setReplyTo(new Email("jayaganes29@gmail.com"));

		Request request = new Request();

		Response response = null;

		try {

			request.setMethod(Method.POST);

			request.setEndpoint("mail/send");

			request.setBody(mail.build());

			sendgrid.api(request);

		} catch (IOException IoException) {

			System.out.println(IoException.getMessage());

		}

		return response;

	}

}
