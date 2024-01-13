package com.matawan.nicefc.utils.mapper;

import com.matawan.nicefc.dto.PlayerDto;
import com.matawan.nicefc.entity.Player;
import com.matawan.nicefc.entity.Team;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The {@code PlayerMapperTest} class contains unit tests for the methods in the {@link PlayerMapper} class.
 * It ensures that the mapping functions between {@link PlayerDto}, {@link Player}, and {@link Team} work as expected.
 */
public class playerMapperTest {

    /**
     * Tests the mapping of {@link PlayerDto} to {@link Player}.
     */
    @Test
    void mapPlayerDtoToPlayer() {
        // Arrange
        PlayerDto playerDto = new PlayerDto();
        playerDto.setName("younes");
        playerDto.setPosition("ST");

        Team team = new Team();
        team.setName("TeamA");

        // Act
        Player player = PlayerMapper.mapPlayerDtoToPlayer(playerDto, team);

        // Assert
        assertEquals("younes", player.getName());
        assertEquals("ST", player.getPosition());
        assertEquals("TeamA", player.getTeam().getName());
    }

    /**
     * Tests the mapping of {@link Player} to {@link PlayerDto}.
     */
    @Test
    void mapPlayerToPlayerDto() {
        // Arrange
        Player player = new Player();
        player.setName("younes");
        player.setPosition("ST");

        Team team = new Team();
        team.setName("TeamA");
        player.setTeam(team);

        // Act
        PlayerDto playerDto = PlayerMapper.mapPlayerToPlayerDto(player);

        // Assert
        assertEquals("younes", playerDto.getName());
        assertEquals("ST", playerDto.getPosition());
    }

    /**
     * Tests the mapping of list of {@link Player} to list {@link PlayerDto}.
     */
    @Test
    void mapPlayersToPlayerDtos() {
        // Arrange
        Player player1 = new Player();
        player1.setName("younes");
        player1.setPosition("ST");
        Team team = new Team();
        team.setName("TeamA");
        player1.setTeam(team);

        Player player2 = new Player();
        player2.setName("lakhnichy");
        player2.setPosition("CM");
        Team team2 = new Team();
        team.setName("TeamB");
        player2.setTeam(team2);

        List<Player> players = Arrays.asList(player1, player2);

        // Act
        List<PlayerDto> playerDtos = PlayerMapper.mapPlayersToPlayerDtos(players);

        // Assert
        assertEquals(2, playerDtos.size());
        assertEquals("younes", playerDtos.get(0).getName());
        assertEquals("ST", playerDtos.get(0).getPosition());
        assertEquals("lakhnichy", playerDtos.get(1).getName());
        assertEquals("CM", playerDtos.get(1).getPosition());
    }

    /**
     * Tests the mapping of list of {@link PlayerDto} to list {@link Player}.
     */
    @Test
    void mapPlayersDtoToPlayers() {
        // Arrange
        PlayerDto playerDto1 = new PlayerDto();
        playerDto1.setName("younes");
        playerDto1.setPosition("ST");

        PlayerDto playerDto2 = new PlayerDto();
        playerDto2.setName("lakhnichy");
        playerDto2.setPosition("CM");

        List<PlayerDto> playerDtos = Arrays.asList(playerDto1, playerDto2);

        Team team = new Team();
        team.setName("TeamA");

        // Act
        List<Player> players = PlayerMapper.mapPlayersDtoToPlayers(playerDtos, team);

        // Assert
        assertEquals(2, players.size());
        assertEquals("younes", players.get(0).getName());
        assertEquals("ST", players.get(0).getPosition());
        assertEquals("TeamA", players.get(0).getTeam().getName());
        assertEquals(2, players.size());
        assertEquals("lakhnichy", players.get(1).getName());
        assertEquals("CM", players.get(1).getPosition());
        assertEquals("TeamA", players.get(1).getTeam().getName());
    }
}
