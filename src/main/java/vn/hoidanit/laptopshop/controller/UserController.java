package vn.hoidanit.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomepage(Model model) {
        List<User> users = this.userService.getAllUsers();
        System.out.println("UserController.getHomepage" + users);
        // User usersByEmail = this.userService.getAllUsersByEmail("kato@gmail.com");
        // System.out.println("UserController.getHomepage" + usersByEmail);
        model.addAttribute("users", new User());
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/table-user";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String getCreateUser(Model model, @ModelAttribute("newUser") User hoidanit) {
        System.out.println("UserController.getCreateUser" + hoidanit);
        this.userService.hanldeSaveUser(hoidanit);
        return "redirect:/admin/user";
    }

    @RequestMapping(value = "/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/show/{id}", method = RequestMethod.GET)
    public String geUserPage(Model model, @PathVariable ("id") long id) {
        User user = this.userService.getuserById(id).orElse(null);
        model.addAttribute("user", user);
        return "admin/user/show";
    }
}
