package com.example.webservice.web;

import com.example.webservice.data.Email;
import com.example.webservice.data.EmailSender;
import com.example.webservice.data.Users;
import com.example.webservice.data.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;
import  java.sql.*;


@Controller
public class EmailController {
	@Autowired
	private EmailSender emailSender;
	@Autowired
	UsersRepository usersRepository;
    private static Connection con;
    private  static Statement stmt;

	@RequestMapping("/send")
	public String sendEmailAction(HttpServletRequest request) throws Exception{
		Email email = new Email();
		String receiver = request.getParameter("email");
		String subject = "임시 비밀번호 for withus";
		if (usersRepository.existsById(receiver) == false){
		    return "0";
        }

		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < 6; i++) {
			int rIndex = rnd.nextInt(3);
			switch (rIndex) {
				case 0:
					// a-z
					temp.append((char) ((int) (rnd.nextInt(26)) + 97));
					break;
				case 1:
					// A-Z
					temp.append((char) ((int) (rnd.nextInt(26)) + 65));
					break;
				case 2:
					// 0-9
					temp.append((rnd.nextInt(10)));
					break;
			}
		}

		String content = "회원님의 임시 비밀번호는 " + temp.toString() + "입니다.";

        usersRepository.findById(receiver).get().setPassword(temp.toString());
		usersRepository.save(usersRepository.findById(receiver).get());

		email.setReceiver (receiver);
		email.setSubject(subject);
		email.setContent(content);
		emailSender.SendEmail(email);
		
		return "1";
	}
}
