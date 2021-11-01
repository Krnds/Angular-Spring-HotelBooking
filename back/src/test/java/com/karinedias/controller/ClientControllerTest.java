package com.karinedias.controller;

import com.jayway.jsonpath.JsonPath;
import com.karinedias.hotelapp.controller.ClientController;
import com.karinedias.hotelapp.entity.Client;
import com.karinedias.hotelapp.exceptions.InvalidEntityException;
import com.karinedias.hotelapp.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = ClientController.class)
@WebMvcTest(value = ClientController.class)
@AutoConfigureMockMvc(addFilters = false)
@WebAppConfiguration
public class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private ClientService clientService;


    @Test
    void whenCallingExistingEdpoint_shouldExpectCorrectHTTPStatus() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/client")).andExpect(status().isOk());
    }


    @Test
    void whenCallingNonexistingEnpoint_shouldThrowNotFoundStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    void givenReturnTypeIsString_thenDefaultContentTypeIsJSON()
            throws Exception {

        String expectedMimeType = "application/json";
        String actualMimeType = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/client", 1))
                .andReturn().getResponse().getContentType();
        assertThat(actualMimeType).isEqualTo(expectedMimeType);
    }


    @Test
    void whenClientIsDeleted_thenHTTPServerStatusIsCorrect()
            throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/client/{id}", "2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    void whenNewClientIsAddeWithId_shouldReturnExpectedId() throws Exception, InvalidEntityException {
        /* Given*/
        Client client = new Client();
        client.setId(1);
        given(this.clientService.add(any())).willReturn(client);

        /* When-Then*/
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/client/")
                        .contentType(MediaType.APPLICATION_JSON).content("{\"nomComplet\":\"Manon Fanfrelune\",\"telephone\":\"0235668917\",\"email\":\"m.fanfrelune@company.gov\",\"adresse\":\"56 rue de la Loire, 44000 Nantes\"}"))
                .andExpect(status().isCreated()).andReturn();
        int id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        assertThat(id).isEqualTo(1);
    }
}
