package controller;

import model.User;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import service.IUserService;

@Controller

public class UserController {
    @Autowired
    private IUserService userService;
    @GetMapping("/create-user")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }
    @PostMapping("/validate-user")
    public ModelAndView saveCustomer(@Validated @ModelAttribute("user") User user, BindingResult bindingResult) {
        new User().validate(user,bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("/index");
        }
        userService.save(user);
        return new ModelAndView("/result");
    }
}
