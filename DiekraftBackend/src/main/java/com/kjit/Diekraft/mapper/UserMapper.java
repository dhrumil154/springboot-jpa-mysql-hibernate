package com.kjit.Diekraft.mapper;

import com.kjit.Diekraft.exceptions.EmailAlreadyExist;
import com.kjit.Diekraft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.kjit.Diekraft.dto.UserDTO;
import com.kjit.Diekraft.entity.User;
import com.kjit.Diekraft.repository.StateRepository;

import java.util.stream.Collectors;

@Component
public class UserMapper {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
    private UserRepository userRepository;

    public User mapToUser(UserDTO userDTO,User user) {
        if(userDTO.getId()!= null){
            user.setPassword(user.getPassword());
        }else{
            user = new User();
            if(userRepository.findOneByEmail(userDTO.getEmail()).isPresent()){
                throw new EmailAlreadyExist("Email Already Exist", HttpStatus.BAD_REQUEST.value());
            }
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setPassword(user.getPassword());
        user.setAddress(userDTO.getAddress());
        user.setCity(userDTO.getCity());
        user.setZipcode(userDTO.getZipcode());
        user.setUserName(userDTO.getUserName());
        user.setMobileNo(userDTO.getMobileNo());
        user.setState(stateRepository.getOne(userDTO.getState()));
        user.setDateOfBirth(userDTO.getBirthDate());
        return user;
    }

    public UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setAddress(user.getAddress());
        userDTO.setCity(user.getCity());
        userDTO.setState(user.getState().getName());
        userDTO.setZipcode(user.getZipcode());
        userDTO.setUserName(user.getUserName());
        userDTO.setMobileNo(user.getMobileNo());
        userDTO.setBirthDate(user.getDateOfBirth());
        userDTO.setAuthorities(user.getAuthorities().stream().map(authority-> authority.getName()).collect(Collectors.toSet()));
        return userDTO;
    }

}
