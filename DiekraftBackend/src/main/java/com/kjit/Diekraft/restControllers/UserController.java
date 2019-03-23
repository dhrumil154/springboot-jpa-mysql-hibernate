package com.kjit.Diekraft.restControllers;

import com.kjit.Diekraft.dto.ChangePasswordDTO;
import com.kjit.Diekraft.dto.ForgetPasswordDTO;
import com.kjit.Diekraft.dto.UserDTO;
import com.kjit.Diekraft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
		return new ResponseEntity<>(userService.createOrUpdateUser(userDTO), HttpStatus.CREATED);
	}
	
	@PostMapping("/changePassword")
	public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO){
		userService.changePassword(changePasswordDTO);
		return new ResponseEntity<>(HttpStatus.OK);	
	}

	@PostMapping("/forgetPassword")
	public ResponseEntity<Void> forgetPassword(@RequestBody ForgetPasswordDTO forgetPasswordDTO){
		userService.forgetPassword(forgetPasswordDTO);
		return new ResponseEntity<>(HttpStatus.OK);	
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> userById(@PathVariable Long id){
		return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO){
		return new ResponseEntity<>(userService.createOrUpdateUser(userDTO), HttpStatus.CREATED);
	}

    @GetMapping("/getAllUser")
    public ResponseEntity<List<UserDTO>> updateUser(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.CREATED);
    }

}