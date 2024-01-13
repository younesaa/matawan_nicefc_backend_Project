package com.matawan.nicefc.entity;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
/**
 * {@code PlayerEntityTest} is a Test class for the Player class.
 * This class contains unit tests for methods in the Player class.
 */
public class PlayerEntityTest {

    private Validator validator;

    /**
     * Sets up the {@code Validator} instance before each test method.
     */
    @BeforeEach
    void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator =factory.getValidator();
    }

    /**
     * Tests the {@code Player} entity when there are no validation violations.
     * It checks if the entity behaves as expected when provided with valid data.
     */
    @Test
    void playerEntityTest_NoValidationViolation(){
        Player player = new Player();
        player.setName("younes");
        player.setPosition("ST");

        Team team = new Team();
        player.setTeam(team);

        assertEquals("younes",player.getName());
        assertEquals("ST",player.getPosition());
        Set<ConstraintViolation<Player>> violations = validator.validate(player);
        assertTrue(violations.isEmpty());
    }

    /**
     * Tests the {@code Player} entity when there is a validation violation for the 'name' field.
     * It checks if the entity correctly handles the validation error message for an invalid 'name'.
     */
    @Test
    void playerEntityTest_withNameViolation(){

        Player player = new Player();
        player.setName("se");
        player.setPosition("ST");
        Team team = new Team();
        player.setTeam(team);
        Set<ConstraintViolation<Player>> violations = validator.validate(player);
        List<ConstraintViolation<Player>> violationList = new ArrayList<>(violations);
        assertEquals(violationList.get(0).getMessage(),"name size must be between 3 and 30 characters");
    }

    /**
     * Tests the {@code Player} entity when there is a validation violation for the 'position' field.
     * It checks if the entity correctly handles the validation error message for an invalid 'position'.
     */
    @Test
    void playerEntityTest_withPositionViolation(){
        Player player = new Player();
        player.setName("younes");
        player.setPosition("S");
        Team team = new Team();
        player.setTeam(team);
        Set<ConstraintViolation<Player>> violations = validator.validate(player);
        List<ConstraintViolation<Player>> violationList = new ArrayList<>(violations);
        assertEquals(violationList.get(0).getMessage(),"position size must have 2 to 3 characters");
    }

    /**
     * Tests the {@code Player} entity when there is a validation violation for the 'team' field.
     * It checks if the entity correctly handles the validation error message for an invalid 'team'.
     */
    @Test
    void playerEntityTest_withTeamViolation(){
        Player player = new Player();
        player.setName("younes");
        player.setPosition("ST");
        Set<ConstraintViolation<Player>> violations = validator.validate(player);
        List<ConstraintViolation<Player>> violationList = new ArrayList<>(violations);
        assertEquals(violationList.get(0).getMessage(),"player need to be assigned to a " + violationList.get(0).getPropertyPath());
    }

    /**
     * Tests the {@code Player} entity when there is a validation violation for the 'position' and 'name' field.
     * It checks if the entity correctly handles the validation error messages for an invalid 'position' and 'name'.
     */
    @Test
    void playerEntityTest_withPositionAndNameViolation(){
        Player player = new Player();
        Team team = new Team();
        player.setTeam(team);

        Set<ConstraintViolation<Player>> violations = validator.validate(player);
        List<ConstraintViolation<Player>> violationList = new ArrayList<>(violations);
        assertEquals(violationList.get(0).getMessage(),violationList.get(0).getPropertyPath() + " cannot be blank");
        assertEquals(violationList.get(1).getMessage(),violationList.get(1).getPropertyPath() + " cannot be blank");
    }
}
