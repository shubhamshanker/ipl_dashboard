package ww.shubham.ipldashboard.repository;

import org.springframework.data.repository.CrudRepository;

import ww.shubham.ipldashboard.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {

    Team findByTeamName(String teamName);
}
