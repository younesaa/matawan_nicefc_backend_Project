package com.matawan.nicefc.entity;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@code TeamEntityTest} is a Test class for the Team class.
 * This class contains unit tests for methods in the Team class.
 */
public class TeamEntityTest {
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
     * Tests the {@code Team} entity when there are no validation violations.
     * It checks if the entity behaves as expected when provided with valid data.
     */
    @Test
    void teamEntityTest_NoValidationViolation(){
        Team team = new Team();
        team.setName("nice");
        team.setAcronym("NFC");
        team.setBudget(BigDecimal.valueOf(100000));
        assertEquals("nice",team.getName());
        assertEquals("NFC",team.getAcronym());
        assertEquals(BigDecimal.valueOf(100000),team.getBudget());
        Set<ConstraintViolation<Team>> violations = validator.validate(team);
        assertTrue(violations.isEmpty());
    }

    /**
     * Tests the {@code Team} entity when there is a validation violation for the 'name' field.
     * It checks if the entity correctly handles the validation error message for an invalid 'name'.
     */
    @Test
    void teamEntityTest_withNameViolation(){

        Team team = new Team();
        team.setName("ni");
        team.setAcronym("NFC");
        team.setBudget(BigDecimal.valueOf(100000));

        Set<ConstraintViolation<Team>> violations = validator.validate(team);
        List<ConstraintViolation<Team>> violationList = new ArrayList<>(violations);
        assertEquals(violationList.get(0).getMessage(),"name size must be between 3 and 30 characters");
    }

    /**
     * Tests the {@code Team} entity when there is a validation violation for the 'acronym' field.
     * It checks if the entity correctly handles the validation error message for an invalid 'acronym'.
     */
    @Test
    void teamEntityTest_withAcronymViolation(){

        Team team = new Team();
        team.setName("nice");
        team.setAcronym("NC");
        team.setBudget(BigDecimal.valueOf(100000));

        Set<ConstraintViolation<Team>> violations = validator.validate(team);
        List<ConstraintViolation<Team>> violationList = new ArrayList<>(violations);
        assertEquals(violationList.get(0).getMessage(),"acronym size must be between 3 and 6 characters");
    }

    /**
     * Tests the {@code Team} entity when there is a validation violation for the 'budget' field.
     * It checks if the entity correctly handles the validation error message for an invalid 'budget'.
     */
    @Test
    void teamEntityTest_withBudgetViolation(){
        Team team = new Team();
        team.setName("nice");
        team.setAcronym("NFC");

        Set<ConstraintViolation<Team>> violations = validator.validate(team);
        List<ConstraintViolation<Team>> violationList = new ArrayList<>(violations);
        assertEquals(violationList.get(0).getMessage(),"budget cannot be null");
    }

    /**
     * Tests the {@code team} entity when there is a validation violation for the 'acronym' and 'name' field.
     * It checks if the entity correctly handles the validation error messages for an invalid 'acronym' and 'name'.
     */
    @Test
    void teamEntityTest_withAcronymAndNameViolation(){
        Team team = new Team();
        team.setBudget(BigDecimal.valueOf(100000));

        Set<ConstraintViolation<Team>> violations = validator.validate(team);
        List<ConstraintViolation<Team>> violationList = new ArrayList<>(violations);
        assertEquals(violationList.get(0).getMessage(),violationList.get(0).getPropertyPath() + " cannot be blank");
        assertEquals(violationList.get(1).getMessage(),violationList.get(1).getPropertyPath() + " cannot be blank");
    }
}
