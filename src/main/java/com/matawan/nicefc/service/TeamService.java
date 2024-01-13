package com.matawan.nicefc.service;

import com.matawan.nicefc.dto.TeamDto;
import com.matawan.nicefc.entity.Team;
import org.springframework.stereotype.Service;

/**
 * The {@code TeamService} interface defines the contract for handling operations related to teams.
 */
public interface TeamService {

    /**
     * Adds a new team using the provided team data transfer object (DTO).
     *
     * @param teamDto The data transfer object representing the team to be added.
     */
    public void addTeam(TeamDto teamDto);

    /**
     * Checks whether a team with the specified name already exists.
     *
     * @param TeamDtoName The name of the team to check for existence.
     * @return {@code true} if a team with the specified name exists; otherwise, {@code false}.
     */
    public boolean existsByName(String TeamDtoName);
}
