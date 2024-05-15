package ww.shubham.ipldashboard.controller;

import org.springframework.web.bind.annotation.RestController;

import ww.shubham.ipldashboard.model.Team;
import ww.shubham.ipldashboard.repository.MatchRepository;
import ww.shubham.ipldashboard.repository.TeamRepository;
import ww.shubham.ipldashboard.service.TeamService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class TeamController {

    private TeamRepository teamRepository;
    private TeamService teamService;
    private MatchRepository matchRepository;

    public TeamController(TeamRepository teamRepository, TeamService teamService, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.teamService = teamService;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/team/addall")
    public void addTeams() {
        System.out.println("Adding teams");
        teamService.addTeams();
        System.out.println("Done Adding teams");
    }

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
        Team team = teamRepository.findByTeamName(teamName);
        team.setMatches(this.matchRepository.findLatestMatchesByTeam(teamName, 4));
        return team;
    }
    

}
