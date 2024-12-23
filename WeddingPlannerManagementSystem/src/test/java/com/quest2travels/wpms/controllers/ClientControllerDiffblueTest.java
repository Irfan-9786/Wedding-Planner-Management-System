package com.quest2travels.wpms.controllers;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quest2travels.wpms.payload.ClientDto;
import com.quest2travels.wpms.services.ClientService;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ClientController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ClientControllerDiffblueTest {
    @Autowired
    private ClientController clientController;

    @MockBean
    private ClientService clientService;

    /**
     * Test {@link ClientController#addClient(ClientDto)}.
     * <p>
     * Method under test: {@link ClientController#addClient(ClientDto)}
     */
    @Test
    @DisplayName("Test addClient(ClientDto)")
    @Disabled("TODO: Complete this test")
    void testAddClient() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDate` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.quest2travels.wpms.payload.ClientDto["weddingDate"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1328)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:770)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:184)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:502)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:341)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4811)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:4052)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        ClientDto clientDto = new ClientDto();
        clientDto.setBudget(10.0d);
        clientDto.setContact("Contact");
        clientDto.setEmail("jane.doe@example.org");
        clientDto.setName("Name");
        clientDto.setWeddingDate(LocalDate.of(1970, 1, 1));
        String content = (new ObjectMapper()).writeValueAsString(clientDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(clientController).build().perform(requestBuilder);
    }

    /**
     * Test {@link ClientController#getClientById(Long)}.
     * <p>
     * Method under test: {@link ClientController#getClientById(Long)}
     */
    @Test
    @DisplayName("Test getClientById(Long)")
    void testGetClientById() throws Exception {
        // Arrange
        when(clientService.getClientById(Mockito.<Long>any())).thenReturn(new ClientDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/clients/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(clientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"name\":null,\"contact\":null,\"email\":null,\"weddingDate\":null,\"budget\":null}"));
    }

    /**
     * Test {@link ClientController#retrieveAllClients()}.
     * <p>
     * Method under test: {@link ClientController#retrieveAllClients()}
     */
    @Test
    @DisplayName("Test retrieveAllClients()")
    void testRetrieveAllClients() throws Exception {
        // Arrange
        when(clientService.getAllClients()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/clients");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(clientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
