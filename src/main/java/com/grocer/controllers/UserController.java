package com.grocer.controllers;

import com.grocer.models.dto.FileUpload;
import com.grocer.models.dto.UserDTO;
import com.grocer.models.User;
import com.grocer.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.findAll()
                .stream()
                .map(UserDTO::map)
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable("id") Integer id) {
       return UserDTO.map(userService.findById(id));
    }

    @PostMapping("/add")
    public void save(@RequestBody UserDTO userDTO) {
        userService.save(userDTO);
    }
    @PostMapping("/upload")
        public void upload(FileUpload userUpload) throws IOException {
        userService.upload(userUpload);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody UserDTO userDTO){
        userService.update(id,userDTO);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        userService.delete(id);
    }
}