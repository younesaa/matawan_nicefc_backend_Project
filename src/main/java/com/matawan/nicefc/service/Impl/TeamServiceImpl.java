package com.matawan.nicefc.service.Impl;

import com.matawan.nicefc.dto.TeamDto;
import com.matawan.nicefc.entity.Team;
import com.matawan.nicefc.repository.TeamRepository;
import com.matawan.nicefc.service.TeamService;
import com.matawan.nicefc.utils.mapper.TeamMapper;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The {@code TeamServiceImpl} class implements the {@link TeamService} interface and provides
 * functionality for handling operations related to teams.
 */
@Service
public class TeamServiceImpl implements TeamService {


    private final TeamRepository teamRepository;

    /**
     * Constructs a new TeamServiceImpl with the specified TeamRepository.
     *
     * @param teamRepository The repository for managing Team entities in the data store.
     */
    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTeam(TeamDto teamDto) {
        Team team = TeamMapper.mapTeamDtoToTeam(teamDto);
        teamRepository.save(team);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsByName(String teamDtoName) {
        return teamRepository.existsByName(teamDtoName);
    }
}
