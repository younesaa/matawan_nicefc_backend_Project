package com.matawan.nicefc.service.unitTest;

import com.matawan.nicefc.dto.TeamDto;
import com.matawan.nicefc.entity.Team;
import com.matawan.nicefc.repository.TeamRepository;
import com.matawan.nicefc.service.Impl.TeamServiceImpl;
import com.matawan.nicefc.service.TeamService;
import com.matawan.nicefc.utils.mapper.TeamMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@code TeamService} class.
 */
@ExtendWith(MockitoExtension.class)
public class teamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamServiceImpl teamService;

    /**
     * Test for adding a team and verifying the save operation.
     */
    @Test
    void testAddTeam() {
        TeamDto teamDto = new TeamDto();
        Team team = new Team();
        mockStatic(TeamMapper.class);
        when(TeamMapper.mapTeamDtoToTeam(teamDto)).thenReturn(team);
        teamService.addTeam(teamDto);

        verify(teamRepository, times(1)).save(team);
    }

    /**
     * Test for checking if a team with a given name exists (expected result: true).
     */
    @Test
    void testExistsByName_True() {
        when(teamRepository.existsByName("TeamName")).thenReturn(true);

        boolean result = teamService.existsByName("TeamName");

        assertTrue(result);
    }

    /**
     * Test for checking if a team with a given name exists (expected result: false).
     */
    @Test
    void testExistsByName_False() {
        when(teamRepository.existsByName("TeamName")).thenReturn(false);

        boolean result = teamService.existsByName("TeamName");

        assertFalse(result);
    }
}
