package com.example.webservice.web;


import com.example.webservice.data.Users;
import com.example.webservice.data.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    UsersRepository usersRepository;


    public String IogIn(String id, String pw){ //로그인 메소드
       if (usersRepository.existsById(id) == true){
           if (usersRepository.findById(id).get().getPassword().equals(pw)){
               return "1"; }
           else { return "0"; }
       }
       else { return "0"; }
    }
    public String signUp(String id, String pw){ //사인업 메소드
        if (usersRepository.existsById(id) == false){
            Users users = new Users();
            users.setId(id);
            users.setPassword(pw);
            usersRepository.save(users);
            return "1";
        }
        else{ return "0"; }
    }

    public String changePw(String id, String cur_pw, String new_pw){
        if (usersRepository.existsById(id) == true && usersRepository.findById(id).get().getPassword().equals(cur_pw)){
            usersRepository.findById(id).get().setPassword(new_pw);
            usersRepository.save(usersRepository.findById(id).get());
            return "1";
        }
        else { return "0"; }
    }
    public String cancelId(String id, String pw){
        if(usersRepository.existsById(id) && usersRepository.findById(id).get().getPassword().equals(pw)){
            usersRepository.delete(usersRepository.findById(id).get());
            return "1";
        }
        else{ return "0"; }
    }
}