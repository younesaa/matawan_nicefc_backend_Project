package com.matawan.nicefc.service;

import com.matawan.nicefc.dto.TeamDto;
import com.matawan.nicefc.entity.Team;
import org.springframework.data.domain.Page;
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

    /**
     * Retrieves a paginated list of teams Dto.
     *
     * @param page   The page number. Defaults to 0 if not provided.
     * @param size   The number of items per page. Defaults to 10 if not provided.
     * @param sortBy The field to sort the teams by. Supported values: "name", "acronym", "budget".
     *               Defaults to "name" if an unsupported or null value is provided.
     * @return A {@link Page} containing {@link TeamDto} objects representing the teams.
     * @throws IllegalArgumentException If the page or size is less than 0.
     */
    public Page<TeamDto> getTeams(int page, int size, String sortBy);
}
