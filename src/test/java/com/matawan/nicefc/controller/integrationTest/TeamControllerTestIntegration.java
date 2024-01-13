package com.matawan.nicefc.controller.integrationTest;


import com.matawan.nicefc.exception.ValidationException;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Integration tests for the {@code TeamController} class.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class TeamControllerTestIntegration {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Test for adding a team successfully.
     *
     * @throws Exception if any error occurs during the test.
     */
    @Test
    void testAddTeam_Success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Ncefc\",\"acronym\":\"NFC\",\"budget\":100000," +
                                "\"players\":[{\"name\":\"Player1\",\"position\":\"ST\"}," +
                                "{\"name\":\"Player2\",\"position\":\"CM\"}," +
                                "{\"name\":\"Player3\",\"position\":\"CB\"}]}"))
        .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    /**
     * Test for attempting to add a team with a name that already exists.
     *
     * @throws Exception if any error occurs during the test.
     */
    @Test
    void testAddTeam_TeamAlreadyExists() throws Exception {
        // Adding a team with the name "Nice Fc"
        mockMvc.perform(MockMvcRequestBuilders.post("/api/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Nice Fc\",\"acronym\":\"NFC\",\"budget\":100000," +
                                "\"players\":[{\"name\":\"Player1\",\"position\":\"ST\"}," +
                                "{\"name\":\"Player2\",\"position\":\"CM\"}," +
                                "{\"name\":\"Player3\",\"position\":\"CB\"}]}"));

        // Attempting to add another team with the same name, expecting a status of 302 (FOUND)
        mockMvc.perform(MockMvcRequestBuilders.post("/api/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Nice Fc\",\"acronym\":\"NFC\",\"budget\":100000," +
                                "\"players\":[{\"name\":\"Player1\",\"position\":\"ST\"}," +
                                "{\"name\":\"Player2\",\"position\":\"CM\"}," +
                                "{\"name\":\"Player3\",\"position\":\"CB\"}]}"))
        .andExpect(MockMvcResultMatchers.status().isFound());
    }

    /**
     * Test for attempting to get Teams with players sorting.
     *
     * @throws Exception if any error occurs during the test.
     */
    @Test
    void testGetTeams_Success() throws Exception {
        // Adding a team with the name "Nice Fc"
        mockMvc.perform(MockMvcRequestBuilders.post("/api/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Nice Fc\",\"acronym\":\"NFC\",\"budget\":100000," +
                        "\"players\":[{\"name\":\"Player1\",\"position\":\"ST\"}," +
                        "{\"name\":\"Player2\",\"position\":\"CM\"}," +
                        "{\"name\":\"Player3\",\"position\":\"CB\"}]}"));

        // Adding a team with the name "Nice Fc"
        mockMvc.perform(MockMvcRequestBuilders.post("/api/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"antibes Fc\",\"acronym\":\"ANT\",\"budget\":100000," +
                        "\"players\":[{\"name\":\"Player1\",\"position\":\"ST\"}," +
                        "{\"name\":\"Player2\",\"position\":\"CM\"}," +
                        "{\"name\":\"Player3\",\"position\":\"CB\"}]}"));

        // Adding a team with the name "Nice Fc"
        mockMvc.perform(MockMvcRequestBuilders.post("/api/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"cannes Fc\",\"acronym\":\"CNN\",\"budget\":100000," +
                        "\"players\":[{\"name\":\"Player1\",\"position\":\"ST\"}," +
                        "{\"name\":\"Player2\",\"position\":\"CM\"}," +
                        "{\"name\":\"Player3\",\"position\":\"CB\"}]}"));

        // get Teams with OK response Code
        mockMvc.perform(MockMvcRequestBuilders.get("/api/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page",String.valueOf(0))
                        .param("size",String.valueOf(10))
                        .param("sortBy","name"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Test for attempting to get Teams with players sorting.
     *
     * @throws Exception if any error occurs during the test.
     */
    @Test
    void testGetTeams_failed() throws Exception {
        // Adding a team with the name "Nice Fc"
        mockMvc.perform(MockMvcRequestBuilders.post("/api/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Nice Fc\",\"acronym\":\"NFC\",\"budget\":100000," +
                        "\"players\":[{\"name\":\"Player1\",\"position\":\"ST\"}," +
                        "{\"name\":\"Player2\",\"position\":\"CM\"}," +
                        "{\"name\":\"Player3\",\"position\":\"CB\"}]}"));

        // Adding a team with the name "Nice Fc"
        mockMvc.perform(MockMvcRequestBuilders.post("/api/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"antibes Fc\",\"acronym\":\"ANT\",\"budget\":100000," +
                        "\"players\":[{\"name\":\"Player1\",\"position\":\"ST\"}," +
                        "{\"name\":\"Player2\",\"position\":\"CM\"}," +
                        "{\"name\":\"Player3\",\"position\":\"CB\"}]}"));

        // Adding a team with the name "Nice Fc"
        mockMvc.perform(MockMvcRequestBuilders.post("/api/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"cannes Fc\",\"acronym\":\"CNN\",\"budget\":100000," +
                        "\"players\":[{\"name\":\"Player1\",\"position\":\"ST\"}," +
                        "{\"name\":\"Player2\",\"position\":\"CM\"}," +
                        "{\"name\":\"Player3\",\"position\":\"CB\"}]}"));

        // get Teams with OK response Code
        mockMvc.perform(MockMvcRequestBuilders.get("/api/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page",String.valueOf(0))
                        .param("size",String.valueOf(10))
                        .param("sortBy","players"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
