package com.matawan.nicefc.utils.mapper;

import com.matawan.nicefc.dto.PlayerDto;
import com.matawan.nicefc.dto.TeamDto;
import com.matawan.nicefc.entity.Player;
import com.matawan.nicefc.entity.Team;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The {@code TeamMapperTest} class contains unit tests for the methods in the {@link TeamMapper} class.
 * It ensures that the mapping functions between {@link TeamDto}, {@link Team}, and {@link PlayerDto} work as expected.
 */
public class TeamMapperTest {

    /**
     * Tests the mapping of {@link TeamDto} to {@link Team}.
     */
    @Test
    void mapTeamToTeamDto() {
        // Arrange
        Team team = new Team();
        team.setName("TeamA");
        team.setAcronym("TA");
        team.setBudget(new BigDecimal("1000000"));

        Player player1 = new Player();
        player1.setName("Player1");
        player1.setPosition("ST");
        player1.setTeam(team);

        Player player2 = new Player();
        player2.setName("Player2");
        player2.setPosition("CM");
        player2.setTeam(team);

        team.setPlayers(Arrays.asList(player1, player2));

        // Act
        TeamDto teamDto = TeamMapper.mapTeamToTeamDto(team);

        // Assert
        assertEquals("TeamA", teamDto.getName());
        assertEquals("TA", teamDto.getAcronym());
        assertEquals(new BigDecimal("1000000"), teamDto.getBudget());
        assertEquals(2, teamDto.getPlayers().size());
        assertEquals("Player1", teamDto.getPlayers().get(0).getName());
        assertEquals("ST", teamDto.getPlayers().get(0).getPosition());
        assertEquals("Player2", teamDto.getPlayers().get(1).getName());
        assertEquals("CM", teamDto.getPlayers().get(1).getPosition());
    }

    /**
     * Tests the mapping of {@link Team} to {@link TeamDto}.
     */
    @Test
    void mapTeamDtoToTeam() {
        // Arrange
        TeamDto teamDto = new TeamDto();
        teamDto.setName("TeamB");
        teamDto.setAcronym("TB");
        teamDto.setBudget(new BigDecimal("1500000"));

        PlayerDto playerDto1 = new PlayerDto();
        playerDto1.setName("Player3");
        playerDto1.setPosition("CB");

        PlayerDto playerDto2 = new PlayerDto();
        playerDto2.setName("Player4");
        playerDto2.setPosition("GK");

        teamDto.setPlayers(Arrays.asList(playerDto1, playerDto2));

        // Act
        Team team = TeamMapper.mapTeamDtoToTeam(teamDto);

        // Assert
        assertEquals("TeamB", team.getName());
        assertEquals("TB", team.getAcronym());
        assertEquals(new BigDecimal("1500000"), team.getBudget());
        assertEquals(2, team.getPlayers().size());
        assertEquals("Player3", team.getPlayers().get(0).getName());
        assertEquals("CB", team.getPlayers().get(0).getPosition());
        assertEquals("Player4", team.getPlayers().get(1).getName());
        assertEquals("GK", team.getPlayers().get(1).getPosition());
    }

    @Test
    void mapTeamsToTeamDtos() {
        // Arrange
        Team team1 = new Team();
        team1.setName("TeamA");
        team1.setAcronym("TA");
        team1.setBudget(new BigDecimal("1000000"));

        Team team2 = new Team();
        team2.setName("TeamB");
        team2.setAcronym("TB");
        team2.setBudget(new BigDecimal("1500000"));

        List<Team> teams = Arrays.asList(team1, team2);

        // Act
        List<TeamDto> teamDtos = TeamMapper.mapTeamsToTeamDtos(teams);

        // Assert
        assertEquals(2, teamDtos.size());
        assertEquals("TeamA", teamDtos.get(0).getName());
        assertEquals("TA", teamDtos.get(0).getAcronym());
        assertEquals(new BigDecimal("1000000"), teamDtos.get(0).getBudget());
        assertEquals("TeamB", teamDtos.get(1).getName());
        assertEquals("TB", teamDtos.get(1).getAcronym());
        assertEquals(new BigDecimal("1500000"), teamDtos.get(1).getBudget());
    }

    @Test
    void mapTeamDtosToTeams() {
        // Arrange
        TeamDto teamDto1 = new TeamDto();
        teamDto1.setName("TeamC");
        teamDto1.setAcronym("TC");
        teamDto1.setBudget(new BigDecimal("2000000"));

        TeamDto teamDto2 = new TeamDto();
        teamDto2.setName("TeamD");
        teamDto2.setAcronym("TD");
        teamDto2.setBudget(new BigDecimal("2500000"));

        List<TeamDto> teamDtos = Arrays.asList(teamDto1, teamDto2);

        // Act
        List<Team> teams = TeamMapper.mapTeamDtosToTeams(teamDtos);

        // Assert
        assertEquals(2, teams.size());
        assertEquals("TeamC", teams.get(0).getName());
        assertEquals("TC", teams.get(0).getAcronym());
        assertEquals(new BigDecimal("2000000"), teams.get(0).getBudget());
        assertEquals("TeamD", teams.get(1).getName());
        assertEquals("TD", teams.get(1).getAcronym());
        assertEquals(new BigDecimal("2500000"), teams.get(1).getBudget());
    }
}
