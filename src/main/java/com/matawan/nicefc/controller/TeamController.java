package com.matawan.nicefc.controller;

import com.matawan.nicefc.config.postGreSQLConfig;
import com.matawan.nicefc.dto.PlayerDto;
import com.matawan.nicefc.dto.TeamDto;
import com.matawan.nicefc.entity.Player;
import com.matawan.nicefc.entity.Team;
import com.matawan.nicefc.exception.ValidationException;
import com.matawan.nicefc.exception.teamAlreadyExistsException;
import com.matawan.nicefc.service.TeamService;
import jakarta.validation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Controller class for handling operations related to teams.
 *
 * This controller provides endpoints for managing teams, such as adding a new team.
 */
@RestController()
@RequestMapping(value = "/api/teams",consumes = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class TeamController {

    private static final Logger logger = LoggerFactory.getLogger(TeamController.class);
    private final TeamService teamService;

    /**
     * Constructor for TeamController.
     *
     * @param teamService The service for handling team-related operations.
     */
    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    /**
     * Endpoint for adding a new team.
     *
     * @param teamDto The data transfer object (DTO) representing the team to be added.
     * @param result  The result of the validation.
     * @return ResponseEntity containing the added team DTO if successful.
     * @throws ValidationException       If there are validation errors in the input data.
     * @throws teamAlreadyExistsException If a team with the same name already exists.
     */
    @PostMapping
    public ResponseEntity<TeamDto> addTeam(@Valid @RequestBody TeamDto teamDto, BindingResult result) {

        // check for constraints violation
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getField)
                    .toList();
            logger.error("fields violation constraints on Entities");
            throw new ValidationException("validation error" , errors);
        }

        // check for Team existence in DB
        if(teamService.existsByName(teamDto.getName())){
            logger.warn("team already exists on DB");
            throw new teamAlreadyExistsException("team already registered");
        }

        // store Team on DB
        teamService.addTeam(teamDto);
        logger.error("team added successfully");
        return new ResponseEntity<>(teamDto, HttpStatus.CREATED);
    }
}
