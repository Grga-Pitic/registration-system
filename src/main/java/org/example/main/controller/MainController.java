package org.example.main.controller;

import org.example.main.model.Role;
import org.example.main.model.User;
import org.example.main.model.dto.UserDTO;
import org.example.main.model.dto.UserMapper;
import org.example.main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.beans.PropertyEditor;
import java.security.Principal;
import java.text.ParseException;
import java.util.Collections;
import java.util.Date;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    @Qualifier("passwordEditorBean")
    private PropertyEditor passwordEditor;

    @Autowired
    @Qualifier("dateEditorBean")
    private PropertyEditor dateEditor;

    @GetMapping("/profile")
    public String profile(Principal user, Model model) throws ParseException {
        User userFromDB = userRepository.findByLogin(user.getName());

        model.addAttribute("user", userFromDB);
        return "profile";
    }

    @PostMapping("/profile")
    public String saveProfile(UserDTO userFromForm, Principal principal, Model model) {
        User userFromDB = userRepository.findByLogin(principal.getName());

        userMapper.updateDtoFromUser(userFromDB, userFromForm);

        User savedUser = userRepository.save(userFromDB);

        model.addAttribute("user", savedUser);
        return "profile";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addNewUser(User user, Model model) {
        User userFromDB = userRepository.findByLogin(user.getLogin());

        if (userFromDB != null) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/login";
    }

    @ExceptionHandler(TransactionSystemException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleConstraintViolationException(TransactionSystemException e, Model model) {

        Throwable cause = ((TransactionSystemException) e).getRootCause();
        if (cause instanceof ConstraintViolationException) {
            model.addAttribute("message", "All fields are required! ");
            return "registration";
        }
        throw e;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, dateEditor);
        binder.registerCustomEditor(String.class, "password", passwordEditor);
    }
}
