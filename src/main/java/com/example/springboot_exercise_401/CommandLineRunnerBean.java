package com.example.springboot_exercise_401;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CommandLineRunnerBean implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TeamRepository teamRepository;

    public void run(String...args){
        User user = new User("bart", "bart@domain.com", "bart", "Bart", "Simpson", true);
        Role userRole = new Role("bart", "ROLE_USER");

        userRepository.save(user);
        roleRepository.save(userRole);

        User admin = new User("admin", "admin@domain.com", "admin", "Admin", "Admin", true);
        Role adminRole1 = new Role("admin", "ROLE_ADMIN");
        Role adminRole2 = new Role("admin", "ROLE_USER");

        userRepository.save(admin);
        roleRepository.save(adminRole1);
        roleRepository.save(adminRole2);


        //Players
        Set<Player> playersCicadas = new HashSet<>();
        Set<Player> playersBlueCrabs = new HashSet<>();
        Set<Player> playersWoodpeckers = new HashSet<>();

        //Teams 1
        Team team1 = new Team();
        team1.setName("The Cicadas");
        team1.setCity("Bethesda");
        team1.setPlayers(playersCicadas);

        //Teams 2
        Team team2 = new Team();
        team2.setName("The Blue Crabs");
        team2.setCity("Rockville");
        team2.setPlayers(playersBlueCrabs);

        //Teams 3
        Team team3 = new Team();
        team3.setName("The Woodpeckers");
        team3.setCity("Germantown");
        team3.setPlayers(playersWoodpeckers);




        //need 6 players
        Player player1 = new Player();
        player1.setFname("Michael");
        player1.setLname("Federer");
        player1.setAge(20);
        player1.setPhoto("https://media1.popsugar-assets.com/files/thumbor/6mpJjEmkqZM0agP7qfDtuIfwx-Q/fit-in/728xorig/filters:format_auto-!!-:strip_icc-!!-/2019/05/10/857/n/1922729/c30208f45cd5d254e85824.88499174_/i/Best-Soccer-Players-Women-World-Cup-2019.jpg");
        player1.setTeam(team1);
        playersCicadas.add(player1);

        Player player2 = new Player();
        player2.setFname("Diego");
        player2.setLname("Coe");
        player2.setAge(19);
        player2.setPhoto("https://www.telegraph.co.uk/content/dam/football/2021/03/31/TELEMMGLPICT000249415514_trans_NvBQzQNjv4BqKKHRb-3XEVINq-o4Gl_L0WfRT-mJycak5Ay-CsLIA5s.jpeg");
        player2.setTeam(team1);
        playersCicadas.add(player2);

        Player player3 = new Player();
        player3.setFname("Manny");
        player3.setLname("Brady");
        player3.setAge(19);
        player3.setPhoto("https://img.bleacherreport.net/img/images/photos/003/851/571/hi-res-2867724030f7f0efb814a34e9b4f58c5_crop_north.jpg?1581152950&w=3072&h=2048");
        player3.setTeam(team2);
        playersBlueCrabs.add(player3);

        Player player4 = new Player();
        player4.setFname("Saquon");
        player4.setLname("Gurley");
        player4.setAge(18);
        player4.setPhoto("https://thumbor.forbes.com/thumbor/711x476/https://specials-images.forbesimg.com/dam/imageserve/e1555717e3dd4e858f39c9fcf2396b83/960x0.jpg?fit=scale");
        player4.setTeam(team2);
        playersBlueCrabs.add(player4);

        Player player5 = new Player();
        player5.setFname("Aaron");
        player5.setLname("Brees");
        player5.setAge(20);
        player5.setPhoto("https://media.istockphoto.com/photos/soccer-player-in-action-picture-id466169394?k=6&m=466169394&s=612x612&w=0&h=E3Jt_DA4egi0srBoj5g6RAeTZ5LireW8m2P07ygUIiM=");
        player5.setTeam(team3);
        playersWoodpeckers.add(player5);

        Player player6 = new Player();
        player6.setFname("Tom");
        player6.setLname("Mahomes");
        player6.setAge(19);
        player6.setPhoto("https://media.npr.org/assets/img/2019/01/02/gettyimages-1058306908-0b38ff8a90d7bf88fea3133d8b72498665f63e12.jpg");
        player6.setTeam(team3);
        playersWoodpeckers.add(player6);


        //save the teams to the DB
        teamRepository.save(team1);
        teamRepository.save(team2);
        teamRepository.save(team3);














    }

}
