package com.matawan.nicefc.utils.mapper;

import com.matawan.nicefc.dto.PlayerDto;
import com.matawan.nicefc.entity.Player;
import com.matawan.nicefc.entity.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * The {@code PlayerMapper} class provides static methods for mapping between
 * {@link PlayerDto}, {@link Player}, and {@link Team} entities.
 */
public class PlayerMapper {

    // Private constructor to prevent instantiation
    private PlayerMapper(){}

    /**
     * Maps a {@link PlayerDto} to a {@link Player} entity.
     *
     * @param playerDto The player DTO to be mapped.
     * @param team      The team associated with the player.
     * @return The mapped player entity.
     */
    public static Player mapPlayerDtoToPlayer(PlayerDto playerDto,Team team){
        Player player = new Player();
        player.setName(playerDto.getName());
        player.setPosition(playerDto.getPosition());
        player.setTeam(team);
        return player;
    }

    /**
     * Maps a {@link Player} entity to a {@link PlayerDto}.
     *
     * @param player The player entity to be mapped.
     * @return The mapped player DTO.
     */
    public static PlayerDto mapPlayerToPlayerDto(Player player) {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setName(player.getName());
        playerDto.setPosition(player.getPosition());
        return playerDto;
    }

    /**
     * Maps a list of {@link Player} entities to a list of {@link PlayerDto}.
     *
     * @param players The list of players to be mapped.
     * @return The list of mapped player DTOs.
     */
    public static List<PlayerDto> mapPlayersToPlayerDtos(List<Player> players) {
        List<PlayerDto> playersDtoList = new ArrayList<>();
        if(players == null) return playersDtoList;
        for(Player player : players){
            playersDtoList.add(mapPlayerToPlayerDto(player));
        }
        return playersDtoList;
    }

    /**
     * Maps a list of {@link PlayerDto} to a list of {@link Player} entities.
     *
     * @param playersDto The list of player DTOs to be mapped.
     * @param team       The team associated with the players.
     * @return The list of mapped player entities.
     */
    public static List<Player> mapPlayersDtoToPlayers(List<PlayerDto> playersDto,Team team) {

        List<Player> playersList = new ArrayList<>();
        if(playersDto == null) return playersList;
        for(PlayerDto playerDto : playersDto){
            playersList.add(mapPlayerDtoToPlayer(playerDto,team));
        }
        return playersList;
    }
}
