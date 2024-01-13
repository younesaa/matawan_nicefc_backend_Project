package com.matawan.nicefc.controller.unitTest;

import com.matawan.nicefc.controller.TeamController;
import com.matawan.nicefc.dto.PlayerDto;
import com.matawan.nicefc.dto.TeamDto;
import com.matawan.nicefc.exception.ValidationException;
import com.matawan.nicefc.repository.TeamRepository;
import com.matawan.nicefc.service.TeamService;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import javax.annotation.meta.When;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@code TeamController} class.
 */
@ExtendWith(MockitoExtension.class)
public class TeamControllerTest {

    @Mock
    private TeamService teamService;

    @InjectMocks
    private TeamController teamController;


    private BindingResult bindingResult;


    /**
     * Sets up the {@code Validator} instance before each test method.
     */
    @BeforeEach
    void setUp(){
        bindingResult = mock(BindingResult.class);;
    }

    /**
     * Test for adding a team successfully.
     */
    @Test
    public void testAddTeam_Success(){

        PlayerDto playerDto1 = new PlayerDto("player 1","ST");
        PlayerDto playerDto2 = new PlayerDto("player 2","CM");
        PlayerDto playerDto3 = new PlayerDto("player 3","CB");

        TeamDto teamDto = new TeamDto();
        teamDto.setName("nice FC");
        teamDto.setAcronym("NFC");
        teamDto.setBudget(BigDecimal.valueOf(10000));
        teamDto.setPlayers(List.of(playerDto1,playerDto2,playerDto3));

        when(bindingResult.hasErrors()).thenReturn(false);

        when(teamService.existsByName("nice FC")).thenReturn(false);

        ResponseEntity<TeamDto> responseEntity = teamController.addTeam(teamDto, bindingResult);

        verify(teamService,times(1)).existsByName(teamDto.getName());
        verify(teamService,times(1)).addTeam(teamDto);

        assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
    }

    /**
     * Test for handling validation errors when adding a team.
     */
    @Test
    void testAddTeam_ValidationErrors() {

        PlayerDto playerDto1 = new PlayerDto("player 1","ST");
        PlayerDto playerDto2 = new PlayerDto("player 2","CM");
        PlayerDto playerDto3 = new PlayerDto("player 3","CB");

        TeamDto teamDto = new TeamDto();
        teamDto.setName("N");
        teamDto.setAcronym("NFC");
        teamDto.setBudget(BigDecimal.valueOf(10000));
        teamDto.setPlayers(List.of(playerDto1,playerDto2,playerDto3));

        when(bindingResult.hasErrors()).thenReturn(true);
        try {
            teamController.addTeam(teamDto, bindingResult);
        } catch (ValidationException e) {
            assertEquals("validation error", e.getMessage());
        }
    }

    /**
     * Test for handling the case where the team already exists.
     */
    @Test
    void testAddTeam_TeamAlreadyExisting() {

        PlayerDto playerDto1 = new PlayerDto("player 1","ST");
        PlayerDto playerDto2 = new PlayerDto("player 2","CM");
        PlayerDto playerDto3 = new PlayerDto("player 3","CB");

        TeamDto teamDto = new TeamDto();
        teamDto.setName("Nice FC");
        teamDto.setAcronym("NFC");
        teamDto.setBudget(BigDecimal.valueOf(10000));
        teamDto.setPlayers(List.of(playerDto1,playerDto2,playerDto3));

        when(bindingResult.hasErrors()).thenReturn(false);

        teamController.addTeam(teamDto, bindingResult);
        try {
            teamController.addTeam(teamDto, bindingResult);
        } catch (ValidationException e) {
            assertEquals("team already registered", e.getMessage());
        }
    }

    /**
     * Test for getting teams with valid parameters.
     */
    @Test
    void testGetTeamsSortedByAcronym_Success() {
        int page = 0;
        int size = 10;
        String sortBy = "acronym";

        Page<TeamDto> teamsDto = mock(Page.class);
        when(teamService.getTeams(page, size, sortBy)).thenReturn(teamsDto);

        ResponseEntity<?> responseEntity = teamController.getTeams(page, size, sortBy);

        verify(teamService, times(1)).getTeams(page, size, sortBy);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    /**
     * Test for getting teams with non-valid parameters.
     */
    @Test
    void testGetTeamsSortedByPlayers_failed() {
        int page = 0;
        int size = 10;
        String sortBy = "players";

        Page<TeamDto> teamsDto = mock(Page.class);

        ResponseEntity<?> responseEntity = teamController.getTeams(page, size, sortBy);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

}
