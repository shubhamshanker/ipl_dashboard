package ww.shubham.ipldashboard.data;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import ww.shubham.ipldashboard.model.Match;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

  private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

  @Override
  public Match process(final MatchInput matchInput) {
        
    Match match = new Match();
    match.setId(Long.parseLong(matchInput.getId()));
    match.setCity(matchInput.getCity());
    match.setDate(LocalDate.parse(matchInput.getDate()));
    match.setPlayerOfMatch(matchInput.getPlayer_of_match());
    match.setVenue(matchInput.getVenue());

    String firstInningsTeam, secondInningsTeam;
    
    if("bat".equals(matchInput.getToss_decision()))
    {
        firstInningsTeam = matchInput.getToss_winner();
        secondInningsTeam =  matchInput.getToss_winner().equals(matchInput.getTeam1()) 
        ? matchInput.getTeam2() : matchInput.getTeam1();
    }
    else
    {
        secondInningsTeam = matchInput.getToss_winner();
        firstInningsTeam =  matchInput.getToss_winner().equals(matchInput.getTeam1()) 
        ? matchInput.getTeam2() : matchInput.getTeam1();
    }

    match.setTeam1(firstInningsTeam);
    match.setTeam2(secondInningsTeam);

    match.setTossWinner(matchInput.getToss_winner());
    match.setTossDecision(matchInput.getToss_decision());
    match.setMatchWinner(matchInput.getWinner());
    match.setResult(matchInput.getResult());
    match.setResultMargin(matchInput.getResult_margin());
    match.setUmpire(matchInput.getUmpire());
    match.setUmpire1(matchInput.getUmpire1());
    
    return match;
    
  }

}

