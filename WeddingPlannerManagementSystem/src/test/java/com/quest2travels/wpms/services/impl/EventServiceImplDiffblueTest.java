package com.quest2travels.wpms.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.quest2travels.wpms.entities.Client;
import com.quest2travels.wpms.entities.Event;
import com.quest2travels.wpms.exceptions.ResourceNotFoundException;
import com.quest2travels.wpms.payload.EventRequestDto;
import com.quest2travels.wpms.payload.EventResponseDto;
import com.quest2travels.wpms.repositories.ClientRepo;
import com.quest2travels.wpms.repositories.EventRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EventServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class EventServiceImplDiffblueTest {
    @MockBean
    private ClientRepo clientRepo;

    @MockBean
    private EventRepo eventRepo;

    @Autowired
    private EventServiceImpl eventServiceImpl;

    @MockBean
    private ModelMapper modelMapper;

    /**
     * Test {@link EventServiceImpl#createEvent(EventRequestDto)}.
     * <ul>
     *   <li>Given {@link Client#Client()} Budget is ten.</li>
     *   <li>When {@link EventRequestDto#EventRequestDto()}.</li>
     *   <li>Then calls {@link ModelMapper#map(Object, Class)}.</li>
     * </ul>
     * <p>
     * Method under test: {@link EventServiceImpl#createEvent(EventRequestDto)}
     */
    @Test
    @DisplayName("Test createEvent(EventRequestDto); given Client() Budget is ten; when EventRequestDto(); then calls map(Object, Class)")
    void testCreateEvent_givenClientBudgetIsTen_whenEventRequestDto_thenCallsMap() {
        // Arrange
        Client client = new Client();
        client.setBudget(10.0d);
        client.setContact("Contact");
        client.setEmail("jane.doe@example.org");
        client.setEvents(new ArrayList<>());
        client.setId(1L);
        client.setName("Name");
        client.setWeddingDate(LocalDate.of(1970, 1, 1));
        Optional<Client> ofResult = Optional.of(client);
        when(clientRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Client client2 = new Client();
        client2.setBudget(10.0d);
        client2.setContact("Contact");
        client2.setEmail("jane.doe@example.org");
        client2.setEvents(new ArrayList<>());
        client2.setId(1L);
        client2.setName("Name");
        client2.setWeddingDate(LocalDate.of(1970, 1, 1));

        Event event = new Event();
        event.setBookings(new ArrayList<>());
        event.setClient(client2);
        event.setCompleted(true);
        event.setEventDate(LocalDate.of(1970, 1, 1));
        event.setId(1L);
        event.setName("Name");
        when(eventRepo.save(Mockito.<Event>any())).thenReturn(event);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> eventServiceImpl.createEvent(new EventRequestDto()));
        verify(modelMapper).map(isA(Object.class), isA(Class.class));
        verify(clientRepo).findById(isNull());
        verify(eventRepo).save(isA(Event.class));
    }

    /**
     * Test {@link EventServiceImpl#createEvent(EventRequestDto)}.
     * <ul>
     *   <li>Given {@link ClientRepo} {@link CrudRepository#findById(Object)} return
     * empty.</li>
     * </ul>
     * <p>
     * Method under test: {@link EventServiceImpl#createEvent(EventRequestDto)}
     */
    @Test
    @DisplayName("Test createEvent(EventRequestDto); given ClientRepo findById(Object) return empty")
    void testCreateEvent_givenClientRepoFindByIdReturnEmpty() {
        // Arrange
        Optional<Client> emptyResult = Optional.empty();
        when(clientRepo.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> eventServiceImpl.createEvent(new EventRequestDto()));
        verify(clientRepo).findById(isNull());
    }

    /**
     * Test {@link EventServiceImpl#getEventByClientId(Long)}.
     * <ul>
     *   <li>Given {@link Client#Client()} Budget is {@code 0.5}.</li>
     *   <li>Then return size is two.</li>
     * </ul>
     * <p>
     * Method under test: {@link EventServiceImpl#getEventByClientId(Long)}
     */
    @Test
    @DisplayName("Test getEventByClientId(Long); given Client() Budget is '0.5'; then return size is two")
    void testGetEventByClientId_givenClientBudgetIs05_thenReturnSizeIsTwo() {
        // Arrange
        Client client = new Client();
        client.setBudget(10.0d);
        client.setContact("Contact");
        client.setEmail("jane.doe@example.org");
        client.setEvents(new ArrayList<>());
        client.setId(1L);
        client.setName("Name");
        client.setWeddingDate(LocalDate.of(1970, 1, 1));

        Event event = new Event();
        event.setBookings(new ArrayList<>());
        event.setClient(client);
        event.setCompleted(true);
        event.setEventDate(LocalDate.of(1970, 1, 1));
        event.setId(1L);
        event.setName("Name");

        Client client2 = new Client();
        client2.setBudget(0.5d);
        client2.setContact("42");
        client2.setEmail("john.smith@example.org");
        client2.setEvents(new ArrayList<>());
        client2.setId(2L);
        client2.setName("42");
        client2.setWeddingDate(LocalDate.of(1970, 1, 1));

        Event event2 = new Event();
        event2.setBookings(new ArrayList<>());
        event2.setClient(client2);
        event2.setCompleted(false);
        event2.setEventDate(LocalDate.of(1970, 1, 1));
        event2.setId(2L);
        event2.setName("42");

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(event2);
        eventList.add(event);
        when(eventRepo.findByClientId(Mockito.<Long>any())).thenReturn(eventList);
        EventResponseDto eventResponseDto = new EventResponseDto();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<EventResponseDto>>any())).thenReturn(eventResponseDto);

        // Act
        List<EventResponseDto> actualEventByClientId = eventServiceImpl.getEventByClientId(1L);

        // Assert
        verify(eventRepo).findByClientId(eq(1L));
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), isA(Class.class));
        assertEquals(2, actualEventByClientId.size());
        assertSame(eventResponseDto, actualEventByClientId.get(0));
        assertSame(eventResponseDto, actualEventByClientId.get(1));
    }

    /**
     * Test {@link EventServiceImpl#getEventByClientId(Long)}.
     * <ul>
     *   <li>Given {@link ModelMapper}.</li>
     *   <li>Then return Empty.</li>
     * </ul>
     * <p>
     * Method under test: {@link EventServiceImpl#getEventByClientId(Long)}
     */
    @Test
    @DisplayName("Test getEventByClientId(Long); given ModelMapper; then return Empty")
    void testGetEventByClientId_givenModelMapper_thenReturnEmpty() {
        // Arrange
        when(eventRepo.findByClientId(Mockito.<Long>any())).thenReturn(new ArrayList<>());

        // Act
        List<EventResponseDto> actualEventByClientId = eventServiceImpl.getEventByClientId(1L);

        // Assert
        verify(eventRepo).findByClientId(eq(1L));
        assertTrue(actualEventByClientId.isEmpty());
    }

    /**
     * Test {@link EventServiceImpl#getEventByClientId(Long)}.
     * <ul>
     *   <li>Then return size is one.</li>
     * </ul>
     * <p>
     * Method under test: {@link EventServiceImpl#getEventByClientId(Long)}
     */
    @Test
    @DisplayName("Test getEventByClientId(Long); then return size is one")
    void testGetEventByClientId_thenReturnSizeIsOne() {
        // Arrange
        Client client = new Client();
        client.setBudget(10.0d);
        client.setContact("Contact");
        client.setEmail("jane.doe@example.org");
        client.setEvents(new ArrayList<>());
        client.setId(1L);
        client.setName("Name");
        client.setWeddingDate(LocalDate.of(1970, 1, 1));

        Event event = new Event();
        event.setBookings(new ArrayList<>());
        event.setClient(client);
        event.setCompleted(true);
        event.setEventDate(LocalDate.of(1970, 1, 1));
        event.setId(1L);
        event.setName("Name");

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(event);
        when(eventRepo.findByClientId(Mockito.<Long>any())).thenReturn(eventList);
        EventResponseDto eventResponseDto = new EventResponseDto();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<EventResponseDto>>any())).thenReturn(eventResponseDto);

        // Act
        List<EventResponseDto> actualEventByClientId = eventServiceImpl.getEventByClientId(1L);

        // Assert
        verify(eventRepo).findByClientId(eq(1L));
        verify(modelMapper).map(isA(Object.class), isA(Class.class));
        assertEquals(1, actualEventByClientId.size());
        assertSame(eventResponseDto, actualEventByClientId.get(0));
    }

    /**
     * Test {@link EventServiceImpl#getEventByClientId(Long)}.
     * <ul>
     *   <li>Then throw {@link ResourceNotFoundException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link EventServiceImpl#getEventByClientId(Long)}
     */
    @Test
    @DisplayName("Test getEventByClientId(Long); then throw ResourceNotFoundException")
    void testGetEventByClientId_thenThrowResourceNotFoundException() {
        // Arrange
        Client client = new Client();
        client.setBudget(10.0d);
        client.setContact("Contact");
        client.setEmail("jane.doe@example.org");
        client.setEvents(new ArrayList<>());
        client.setId(1L);
        client.setName("Name");
        client.setWeddingDate(LocalDate.of(1970, 1, 1));

        Event event = new Event();
        event.setBookings(new ArrayList<>());
        event.setClient(client);
        event.setCompleted(true);
        event.setEventDate(LocalDate.of(1970, 1, 1));
        event.setId(1L);
        event.setName("Name");

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(event);
        when(eventRepo.findByClientId(Mockito.<Long>any())).thenReturn(eventList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<EventResponseDto>>any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> eventServiceImpl.getEventByClientId(1L));
        verify(eventRepo).findByClientId(eq(1L));
        verify(modelMapper).map(isA(Object.class), isA(Class.class));
    }

    /**
     * Test {@link EventServiceImpl#getAllEvents()}.
     * <ul>
     *   <li>Given {@link ModelMapper}.</li>
     *   <li>Then return Empty.</li>
     * </ul>
     * <p>
     * Method under test: {@link EventServiceImpl#getAllEvents()}
     */
    @Test
    @DisplayName("Test getAllEvents(); given ModelMapper; then return Empty")
    void testGetAllEvents_givenModelMapper_thenReturnEmpty() {
        // Arrange
        when(eventRepo.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<EventResponseDto> actualAllEvents = eventServiceImpl.getAllEvents();

        // Assert
        verify(eventRepo).findAll();
        assertTrue(actualAllEvents.isEmpty());
    }
}
