package com.matawan.nicefc.utils.mapper;

import com.matawan.nicefc.dto.PlayerDto;
import com.matawan.nicefc.dto.TeamDto;
import com.matawan.nicefc.entity.Player;
import com.matawan.nicefc.entity.Team;
import com.matawan.nicefc.utils.mapper.PlayerMapper;

import java.util.ArrayList;
import java.util.List;

import static com.matawan.nicefc.utils.mapper.PlayerMapper.mapPlayerToPlayerDto;

/**
 * The {@code TeamMapper} class contains static methods for mapping between {@link Team} and {@link TeamDto}.
 * It provides functionality to convert entities to DTOs and vice versa.
 */
public class TeamMapper {

    private TeamMapper(){}

    /**
     * Maps a {@link Team} entity to a {@link TeamDto} data transfer object.
     *
     * @param team The {@link Team} entity to be mapped.
     * @return The mapped {@link TeamDto}.
     */
    public static TeamDto mapTeamToTeamDto(Team team) {
        TeamDto teamDto = new TeamDto();
        teamDto.setName(team.getName());
        teamDto.setAcronym(team.getAcronym());
        teamDto.setBudget(team.getBudget());
        teamDto.setPlayers(PlayerMapper.mapPlayersToPlayerDtos(team.getPlayers()));
        return teamDto;
    }

    /**
     * Maps a {@link TeamDto} data transfer object to a {@link Team} entity.
     *
     * @param teamDto The {@link TeamDto} to be mapped.
     * @return The mapped {@link Team} entity.
     */
    public static Team mapTeamDtoToTeam(TeamDto teamDto) {
        Team team = new Team();
        team.setName(teamDto.getName());
        team.setAcronym(teamDto.getAcronym());
        team.setBudget(teamDto.getBudget());
        List<Player> players = PlayerMapper.mapPlayersDtoToPlayers(teamDto.getPlayers(), team);
        team.setPlayers(players);
        return team;
    }

    /**
     * Maps a list of {@link Team} entities to a list of {@link TeamDto}.
     *
     * @param teams The list of players to be mapped.
     * @return The list of mapped team DTOs.
     */
    public static List<TeamDto> mapTeamsToTeamDtos(List<Team> teams) {
        List<TeamDto> teamssDtoList = new ArrayList<>();
        for(Team team : teams){
            teamssDtoList.add(mapTeamToTeamDto(team));
        }
        return teamssDtoList;
    }

    /**
     * Maps a list of {@link PlayerDto} to a list of {@link Player} entities.
     *
     * @param teamsDto The list of player DTOs to be mapped.
     * @return The list of mapped player entities.
     */
    public static List<Team> mapTeamDtosToTeams(List<TeamDto> teamsDto) {

        List<Team> teamsList = new ArrayList<>();
        for(TeamDto teamDto : teamsDto){
            teamsList.add(mapTeamDtoToTeam(teamDto));
        }
        return teamsList;
    }
}

