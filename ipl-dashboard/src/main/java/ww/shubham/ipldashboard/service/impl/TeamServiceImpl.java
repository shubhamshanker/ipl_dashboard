package ww.shubham.ipldashboard.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.persistence.EntityManager;
import ww.shubham.ipldashboard.model.Team;
import ww.shubham.ipldashboard.model.Test;
import ww.shubham.ipldashboard.repository.TeamRepository;
import ww.shubham.ipldashboard.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

    private static final Logger log = LoggerFactory.getLogger(TeamServiceImpl.class);

    private TeamRepository teamRepository;
    
    private final EntityManager em;

    public TeamServiceImpl(TeamRepository teamRepository, EntityManager em) {
        this.teamRepository = teamRepository;
        this.em = em;
    }
    
    @Override
    @Transactional
    public void addTeams() {
        log.info("Adding Teams");

        log.info("Checking Match data count");
        long matchCount = em.createQuery("select count(m) from Match m", Long.class).getSingleResult();
        log.info("Match table count: {}", matchCount);

        Map<String, Team> teamData = new HashMap<>();

        List<Object[]> team1Results = em.createQuery("select m.team1, count(*) from Match m group by m.team1", Object[].class).getResultList();
        log.info("Team 1 query results: {}", team1Results);
        team1Results.stream()
            .map(e -> new Team((String) e[0], (long) e[1]))
            .forEach(team -> teamData.put(team.getTeamName(), team));

        List<Object[]> team2Results = em.createQuery("select m.team2, count(*) from Match m group by m.team2", Object[].class).getResultList();
        log.info("Team 2 query results: {}", team2Results);
        team2Results.stream()
            .forEach(e -> {
                Team team = teamData.get((String) e[0]);
                team.setTotalMatches(team.getTotalMatches() + (long) e[1]);
            });

        List<Object[]> matchWinnerResults = em.createQuery("select m.matchWinner, count(*) from Match m group by m.matchWinner", Object[].class).getResultList();
        log.info("Match winner query results: {}", matchWinnerResults);
        matchWinnerResults.stream()
            .forEach(e -> {
                Team team = teamData.get((String) e[0]);
                if (team != null) team.setTotalWins((long) e[1]);
            });

        teamData.values().forEach(team -> {
            log.info("Saving team: {}", team);
            teamRepository.save(team);
        });
        Test t = new Test("test1");
        em.persist(t);
        log.info("Teams saved successfully");
        teamData.values().forEach(team -> System.out.println(team));
    }
}
