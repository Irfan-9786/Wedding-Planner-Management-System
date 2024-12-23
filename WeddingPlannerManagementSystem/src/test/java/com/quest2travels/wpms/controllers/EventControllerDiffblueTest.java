package com.quest2travels.wpms.controllers;

import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quest2travels.wpms.payload.EventRequestDto;
import com.quest2travels.wpms.payload.EventResponseDto;
import com.quest2travels.wpms.services.EventService;

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

@ContextConfiguration(classes = {EventController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class EventControllerDiffblueTest {
    @Autowired
    private EventController eventController;

    @MockBean
    private EventService eventService;

    /**
     * Test {@link EventController#getEventById(Long)}.
     * <p>
     * Method under test: {@link EventController#getEventById(Long)}
     */
    @Test
    @DisplayName("Test getEventById(Long)")
    void testGetEventById() throws Exception {
        // Arrange
        when(eventService.getEventById(Mockito.<Long>any())).thenReturn(new EventResponseDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/events/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(eventController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":null,\"name\":null,\"eventDate\":null,\"clientDto\":null,\"completed\":false}"));
    }

    /**
     * Test {@link EventController#getEventsByStatus(boolean)}.
     * <p>
     * Method under test: {@link EventController#getEventsByStatus(boolean)}
     */
    @Test
    @DisplayName("Test getEventsByStatus(boolean)")
    void testGetEventsByStatus() throws Exception {
        // Arrange
        when(eventService.getEventByStatus(anyBoolean())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/events/status/{completed}", true);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(eventController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Test {@link EventController#getEventsByClient(Long)}.
     * <p>
     * Method under test: {@link EventController#getEventsByClient(Long)}
     */
    @Test
    @DisplayName("Test getEventsByClient(Long)")
    void testGetEventsByClient() throws Exception {
        // Arrange
        when(eventService.getEventByClientId(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/events/client/{clientId}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(eventController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Test {@link EventController#createEvent(EventRequestDto)}.
     * <p>
     * Method under test: {@link EventController#createEvent(EventRequestDto)}
     */
    @Test
    @DisplayName("Test createEvent(EventRequestDto)")
    @Disabled("TODO: Complete this test")
    void testCreateEvent() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDate` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.quest2travels.wpms.payload.EventRequestDto["eventDate"])
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
        EventRequestDto eventRequestDto = new EventRequestDto();
        eventRequestDto.setClientId(1L);
        eventRequestDto.setEventDate(LocalDate.of(1970, 1, 1));
        eventRequestDto.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(eventRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(eventController).build().perform(requestBuilder);
    }

    /**
     * Test {@link EventController#getAllEvents()}.
     * <p>
     * Method under test: {@link EventController#getAllEvents()}
     */
    @Test
    @DisplayName("Test getAllEvents()")
    void testGetAllEvents() throws Exception {
        // Arrange
        when(eventService.getAllEvents()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/events");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(eventController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Test {@link EventController#updateEventStatus(Long, boolean)}.
     * <p>
     * Method under test: {@link EventController#updateEventStatus(Long, boolean)}
     */
    @Test
    @DisplayName("Test updateEventStatus(Long, boolean)")
    void testUpdateEventStatus() throws Exception {
        // Arrange
        when(eventService.updateEventStatus(Mockito.<Long>any(), anyBoolean())).thenReturn(new EventResponseDto());
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders.put("/events/{id}/status", 1L);
        MockHttpServletRequestBuilder requestBuilder = putResult.param("completed", String.valueOf(true));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(eventController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":null,\"name\":null,\"eventDate\":null,\"clientDto\":null,\"completed\":false}"));
    }
}
