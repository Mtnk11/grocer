package com.grocer.services;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.grocer.exception.CustomRestException;
import com.grocer.exception.RestErrorCode;
import com.grocer.models.QUser;
import com.grocer.models.dto.FileUpload;
import com.grocer.models.dto.UserDTO;
import com.grocer.models.User;
import com.grocer.repositories.UserRepository;
import com.grocer.repositories.UserRepositoryDSL;
import com.opencsv.CSVReader;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Query;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserRepositoryDSL userRepositoryDSL;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomRestException("User not found", RestErrorCode.user_not_found));

    }

    public Integer save(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new RuntimeException("User with email '" + userDTO.getEmail() + "' is already exists");
        }

        User user = UserDTO.map(userDTO);
        return userRepository.save(user).getId();
    }

    public UserDTO update(int id, UserDTO userDTO) {
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));

        userToUpdate.setName(userDTO.getName());
        userToUpdate.setEmail(userDTO.getEmail());
        userToUpdate.setPassword(userDTO.getPassword());
        userToUpdate.setTimestamp(userDTO.getTimestamp());

        userRepository.save(userToUpdate);
        return UserDTO.map(userToUpdate);
    }

    public void delete(int id) {
        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
        userRepository.delete(userToDelete);
    }

    public void upload(FileUpload userUpload) throws IOException {
        List<User> users = new ArrayList<>();

        try (Reader inputReader = new InputStreamReader(userUpload.getFile().getInputStream())) {
            CsvParserSettings settings = new CsvParserSettings();
            settings.setNumberOfRowsToSkip(1);
            CsvParser parser = new CsvParser(settings);

            List<String[]> parsedRows = parser.parseAll(inputReader);

            for (String[] rec : parsedRows) {
                String value = rec[0];
                String email = rec[1];
                User user = User.builder()
                        .name(value)
                        .email(email)
                        .build();
                users.add(user);
            }
        }
        userRepository.saveAll(users);
    }

    public List<String[]> readAllLines(Path filePath) throws IOException {
        try (Reader reader = Files.newBufferedReader(filePath)){
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
            }
        }
    }
    public User getByIdWithDSL(int id,String name) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QUser.user.id.eq(id)).or(QUser.user.name.eq(name));
        userRepositoryDSL.findOne(builder);
        userRepositoryDSL.findOne(QUser.user.id.eq(id).or(QUser.user.name.eq(name)));
//        if (Objects.nonNull(id)) {
//            builder.and(QUser.user.id.eq(id));
//        }
//        Optional<User> byId = userRepositoryDSL.findOne(builder);
//        return byId.orElseThrow();

        return userRepositoryDSL.findOne(QUser.user.id.eq(id)).orElseThrow();
    }
    public List<User> getUsersByName(String name) {
//        return Lists.newArrayList(userRepositoryDSL.findAll(QUser.user.name.contains(name)));

        return (List<User>) userRepositoryDSL.findAll(QUser.user.name.in(name));
    }
    public User getUserByEmail(String email) {
        return userRepositoryDSL.findOne(QUser.user.email.eq(email)).orElseThrow();
    }
    public List<User> getUsers() {
        return null;
    }
}
