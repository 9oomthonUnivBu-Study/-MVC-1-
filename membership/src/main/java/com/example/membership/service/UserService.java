package com.example.membership.service;

import com.example.membership.dto.LoginReqquestDto;
import com.example.membership.dto.UserDto;
import com.example.membership.entity.UserEntity;
import com.example.membership.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long saveUser(UserDto userDto) {
        if(userRepository.findByUserId(userDto.getUserId()) != null) {
            throw new IllegalArgumentException("이미 존재하는 아이디 입니다");
        }
        UserEntity user = new UserEntity();
        user.setUserId(userDto.getUserId());
        user.setUserPw(userDto.getUserPw());
        user.setUserEmail(userDto.getUserEmail());
        return userRepository.save(user).getId();
    }

    public String loginUser(LoginReqquestDto loginDto){
        UserEntity user = userRepository.findByUserId(loginDto.getUserId());

        if(user == null) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }


        if(!user.getUserPw().equals(loginDto.getUserPw())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return "로그인 성공";
    }

}
