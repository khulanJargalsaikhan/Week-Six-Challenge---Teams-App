package com.example.springboot_exercise_401;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CloudinaryConfig cloudc;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;


    @RequestMapping("/")
    public String homepage(Model model){
        model.addAttribute("teams", teamRepository.findAll());
        return "index";
    }


    @GetMapping("/addTeam")
    public String addTeam(Model model, Principal principal){
        String username = principal.getName();
        model.addAttribute("username", username);

        model.addAttribute("team", new Team());
        return "teamForm";
    }

    @RequestMapping("/updateTeam/{id}")
    public String updateTeam(@PathVariable("id") long id, Model model){
        model.addAttribute("team", teamRepository.findById(id).get());
        return "teamForm";
    }

    @RequestMapping("/deleteTeam/{id}")
    public String deleteTeam(@PathVariable("id") long id){
        teamRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/processTeam")
    public String processTeam(@ModelAttribute Team team){
        teamRepository.save(team);
        return "redirect:/";
    }

    @RequestMapping("/allTeams")
    public String showAllTeams(Model model){
        model.addAttribute("teams", teamRepository.findAll());
        return "allTeams";
    }

    @RequestMapping("/team/{id}")
    public String showTeamPage(@PathVariable("id") long id, Model model){
        model.addAttribute("teams", teamRepository.findById(id).get());
        return "team";
    }



    @GetMapping("/addPlayer")
    public String addPlayer(Model model, Principal principal){
        String username = principal.getName();
        model.addAttribute("username", username);

        model.addAttribute("player", new Player());
        model.addAttribute("teams", teamRepository.findAll());
        return "playerForm";
    }


    @RequestMapping("/updatePlayer/{id}")
    public String updatePlayer(@PathVariable("id") long id, Model model){
        model.addAttribute("player", playerRepository.findById(id).get());
        model.addAttribute("teams", teamRepository.findAll());
        return "playerForm";
    }

    @RequestMapping("/deletePlayer/{id}")
    public String deletePlayer(@PathVariable("id") long id){
        playerRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/processPlayer")
    public String processPlayer(@ModelAttribute Player player, @RequestParam("file") MultipartFile file){
        if(file.isEmpty() && (player.getPhoto() == null || player.getPhoto().isEmpty())){
            player.setPhoto("https://res.cloudinary.com/dqejdpdau/image/upload/v1628789991/d20vhr0ywftubc4dcex7.jpg");   //set up default photo

        }else if(!file.isEmpty()) {
            try {
                Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
                player.setPhoto(uploadResult.get("url").toString());

            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/processPlayer";
            }
        }
        playerRepository.save(player);
        return "redirect:/";
    }

    @RequestMapping("/profile/{id}")
    public String showProfile(@PathVariable("id") long id, Model model){
        model.addAttribute("players", playerRepository.findById(id).get());
        return "profile";
    }


    @RequestMapping("/allPlayers")
    public String showAllPlayers(Model model){
        model.addAttribute("players", playerRepository.findAll());
        model.addAttribute("teams", teamRepository.findAll());
        return "allPlayers";
    }






    @GetMapping("/register")
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/processregister")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
        if(result.hasErrors()){
            user.clearPassword();
            model.addAttribute("user", user);
            return "register";
        }else{
            model.addAttribute("user", user);
            model.addAttribute("message", "New user account created.");
            user.setEnabled(true);
            userRepository.save(user);

            Role role = new Role(user.getUsername(), "ROLE_USER");
            roleRepository.save(role);
        }
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    @RequestMapping("/logout")
    public String logout(){
        return "redirect:/login?logout=true";
    }

    //principal is currently logged in
    @RequestMapping("/secure")
    public String secure(Principal principal, Model model){
        String username = principal.getName();
        model.addAttribute("user", userRepository.findByUsername(username));
        return "secure";
    }





}