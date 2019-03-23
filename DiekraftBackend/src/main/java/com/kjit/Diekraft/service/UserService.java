package com.kjit.Diekraft.service;

import com.kjit.Diekraft.Util.EmailSender;
import com.kjit.Diekraft.config.security.SecurityUtils;
import com.kjit.Diekraft.config.security.jwt.TokenProvider;
import com.kjit.Diekraft.dto.ChangePasswordDTO;
import com.kjit.Diekraft.dto.ForgetPasswordDTO;
import com.kjit.Diekraft.dto.UserDTO;
import com.kjit.Diekraft.entity.Authorities;
import com.kjit.Diekraft.entity.User;
import com.kjit.Diekraft.exceptions.UnauthorizeUserException;
import com.kjit.Diekraft.mapper.UserMapper;
import com.kjit.Diekraft.repository.AuthorityRepository;
import com.kjit.Diekraft.repository.UserRepository;
import org.apache.catalina.security.SecurityUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void forgetPassword(ForgetPasswordDTO forgetPasswordDTO) {
        userRepository.findOneByEmail(forgetPasswordDTO.getEmail())
                .ifPresent(user -> {
                    String tempPassword = createRandomPassword();
                    EmailSender.sendEmail(user.getEmail(),"Temporary Password","your new password is :"+tempPassword);
                    user.setPassword(passwordEncoder.encode(tempPassword));
                    userRepository.save(user);
                });
    }

    private String createRandomPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        return RandomStringUtils.random(8, characters);
    }

    public UserDTO createOrUpdateUser(UserDTO userDTO) {
        User user = (userDTO.getId() == null) ? userMapper.mapToUser(userDTO, null) :
                userMapper.mapToUser(userDTO, userRepository.getOne(userDTO.getId()));
        if (userDTO.getAuthorities() != null) {
            Set<Authorities> authorities = userDTO.getAuthorities().stream()
                    .map(authorityRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
            user.setAuthorities(authorities);
        }
        user = userRepository.save(user);
        return userMapper.toUserDTO(user);
    }


    public void changePassword(ChangePasswordDTO changePasswordDTO) {
        String email = SecurityUtils.getCurrentUserLogin().get();
        if (email != null) {
            userRepository.findOneByEmail(email).ifPresent(user -> {
                String encryptedPassword = passwordEncoder.encode(changePasswordDTO.getNewPassword());
                user.setPassword(encryptedPassword);
                userRepository.save(user);
            });
        } else {
            throw new UnauthorizeUserException("You are unauthorized user.", HttpStatus.UNAUTHORIZED.value());
        }

    }

    public UserDTO getUserById(Long id) {
        Optional<User> users = userRepository.findById(id);
        if (users.isPresent()) {
            return userMapper.toUserDTO(users.get());
        }
        return null;
    }

    public List<UserDTO> getAllUser() {
        return userRepository.findAll().stream().map(user -> userMapper.toUserDTO(user)).collect(Collectors.toList());
    }


	/*public void forgetPassword(ForgetPasswordDTO forgetPasswordDTO) {
		Optional<User> users = userRepository.findOneByEmail(forgetPasswordDTO.getEmail());
		if(users.isPresent()) {
			  String tempPassword = "dffkfw";
			  String to = users.get().getEmail();//change accordingly
		      String from = "sonoojaiswal1987@gmail.com";//change accordingly
		      String host = "localhost";//or IP address

		     //Get the session object
		      Properties properties = System.getProperties();
		      properties.setProperty("mail.smtp.host", host);
		      Session session = Session.getDefaultInstance(properties);

		     //compose the message
		      try{
		         MimeMessage message = new MimeMessage(session);
		         message.setFrom(new InternetAddress(from));
		         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
		         message.setSubject("Forget Password Request...");
		         message.setText("Hello, this is example of sending email  ");

		         // Send message
		         Transport.send(message);
		         System.out.println("message sent successfully....");

		      }catch (MessagingException mex) {mex.printStackTrace();}
		   }
		}*/


    /*public ProfilePicDTO getProfilePic(Long id) {
        return userRepository.findImageById(id);
    }

    public ProfilePicDTO updateProfilePic(ProfilePicDTO profilePicDTO) {
        User user1 = userRepository.findById(profilePicDTO.getUserId())
                .map(user -> {
                    user.setImageFormat(profilePicDTO.getImageType());
                    user.setProfilePic(profilePicDTO.getProfilePic());
                    userRepository.save(user);
                    return user;
                }).get();

        return profilePicDTO;
    }*/

}
