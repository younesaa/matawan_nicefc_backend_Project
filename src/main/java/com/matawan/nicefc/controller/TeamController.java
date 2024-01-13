package com.matawan.nicefc.controller;


import com.matawan.nicefc.dto.TeamDto;
import com.matawan.nicefc.exception.ValidationException;
import com.matawan.nicefc.exception.teamAlreadyExistsException;
import com.matawan.nicefc.service.TeamService;
import jakarta.validation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.temporal.Temporal;
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
            throw new ValidationException("validation error" , errors);
        }

        // check for Team existence in DB
        if(teamService.existsByName(teamDto.getName())){
            logger.warn("team already exists on DB");
            throw new teamAlreadyExistsException("team already registered");
        }

        // store Team on DB
        teamService.addTeam(teamDto);
        logger.info("team added successfully");
        return new ResponseEntity<>(teamDto, HttpStatus.CREATED);
    }

    /**
     * Endpoint for adding a new team.
     *
     * @param page page number to retrieve.
     * @param size  size of element in each page.
     * @param sortBy  field to sort with
     * @return ResponseEntity containing the added the page of team DTO if successful.
     */
    @GetMapping
    public ResponseEntity<?> getTeams(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy){
        if(!sortBy.equals("name") && !sortBy.equals("acronym") && !sortBy.equals("budget") ){
            logger.error("fields violation constraints on Entities");
            return new ResponseEntity<>("field to sort with is invalid ",HttpStatus.BAD_REQUEST);
        }
        Page<TeamDto> teamsDto = teamService.getTeams(page,size,sortBy);
        logger.info("List of  teams retrieved successfully");
        return new ResponseEntity<>(teamsDto,HttpStatus.OK);
    }
}
