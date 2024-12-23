package com.quest2travels.wpms.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.quest2travels.wpms.entities.Client;
import com.quest2travels.wpms.exceptions.ResourceNotFoundException;
import com.quest2travels.wpms.payload.ClientDto;
import com.quest2travels.wpms.repositories.ClientRepo;

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

@ContextConfiguration(classes = {ClientServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ClientServiceImplDiffblueTest {
    @MockBean
    private ClientRepo clientRepo;

    @Autowired
    private ClientServiceImpl clientServiceImpl;

    @MockBean
    private ModelMapper modelMapper;

    /**
     * Test {@link ClientServiceImpl#registerClient(ClientDto)}.
     * <p>
     * Method under test: {@link ClientServiceImpl#registerClient(ClientDto)}
     */
    @Test
    @DisplayName("Test registerClient(ClientDto)")
    void testRegisterClient() {
        // Arrange
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> clientServiceImpl.registerClient(new ClientDto()));
        verify(modelMapper).map(isA(Object.class), isA(Class.class));
    }

    /**
     * Test {@link ClientServiceImpl#getAllClients()}.
     * <p>
     * Method under test: {@link ClientServiceImpl#getAllClients()}
     */
    @Test
    @DisplayName("Test getAllClients()")
    void testGetAllClients() {
        // Arrange
        Client client = new Client();
        client.setBudget(10.0d);
        client.setContact("None of the client has been registered yet...!!!");
        client.setEmail("jane.doe@example.org");
        client.setEvents(new ArrayList<>());
        client.setId(1L);
        client.setName("None of the client has been registered yet...!!!");
        client.setWeddingDate(LocalDate.of(1970, 1, 1));

        ArrayList<Client> clientList = new ArrayList<>();
        clientList.add(client);
        when(clientRepo.findAll()).thenReturn(clientList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ClientDto>>any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> clientServiceImpl.getAllClients());
        verify(modelMapper).map(isA(Object.class), isA(Class.class));
        verify(clientRepo).findAll();
    }

    /**
     * Test {@link ClientServiceImpl#getAllClients()}.
     * <ul>
     *   <li>Given {@link Client#Client()} Budget is {@code 0.5}.</li>
     *   <li>Then return size is two.</li>
     * </ul>
     * <p>
     * Method under test: {@link ClientServiceImpl#getAllClients()}
     */
    @Test
    @DisplayName("Test getAllClients(); given Client() Budget is '0.5'; then return size is two")
    void testGetAllClients_givenClientBudgetIs05_thenReturnSizeIsTwo() {
        // Arrange
        Client client = new Client();
        client.setBudget(10.0d);
        client.setContact("None of the client has been registered yet...!!!");
        client.setEmail("jane.doe@example.org");
        client.setEvents(new ArrayList<>());
        client.setId(1L);
        client.setName("None of the client has been registered yet...!!!");
        client.setWeddingDate(LocalDate.of(1970, 1, 1));

        Client client2 = new Client();
        client2.setBudget(0.5d);
        client2.setContact("42");
        client2.setEmail("john.smith@example.org");
        client2.setEvents(new ArrayList<>());
        client2.setId(2L);
        client2.setName("42");
        client2.setWeddingDate(LocalDate.of(1970, 1, 1));

        ArrayList<Client> clientList = new ArrayList<>();
        clientList.add(client2);
        clientList.add(client);
        when(clientRepo.findAll()).thenReturn(clientList);
        ClientDto clientDto = new ClientDto();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ClientDto>>any())).thenReturn(clientDto);

        // Act
        List<ClientDto> actualAllClients = clientServiceImpl.getAllClients();

        // Assert
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), isA(Class.class));
        verify(clientRepo).findAll();
        assertEquals(2, actualAllClients.size());
        assertSame(clientDto, actualAllClients.get(0));
        assertSame(clientDto, actualAllClients.get(1));
    }

    /**
     * Test {@link ClientServiceImpl#getAllClients()}.
     * <ul>
     *   <li>Given {@link ModelMapper} {@link ModelMapper#map(Object, Class)} return
     * {@link ClientDto#ClientDto()}.</li>
     *   <li>Then return size is one.</li>
     * </ul>
     * <p>
     * Method under test: {@link ClientServiceImpl#getAllClients()}
     */
    @Test
    @DisplayName("Test getAllClients(); given ModelMapper map(Object, Class) return ClientDto(); then return size is one")
    void testGetAllClients_givenModelMapperMapReturnClientDto_thenReturnSizeIsOne() {
        // Arrange
        Client client = new Client();
        client.setBudget(10.0d);
        client.setContact("None of the client has been registered yet...!!!");
        client.setEmail("jane.doe@example.org");
        client.setEvents(new ArrayList<>());
        client.setId(1L);
        client.setName("None of the client has been registered yet...!!!");
        client.setWeddingDate(LocalDate.of(1970, 1, 1));

        ArrayList<Client> clientList = new ArrayList<>();
        clientList.add(client);
        when(clientRepo.findAll()).thenReturn(clientList);
        ClientDto clientDto = new ClientDto();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ClientDto>>any())).thenReturn(clientDto);

        // Act
        List<ClientDto> actualAllClients = clientServiceImpl.getAllClients();

        // Assert
        verify(modelMapper).map(isA(Object.class), isA(Class.class));
        verify(clientRepo).findAll();
        assertEquals(1, actualAllClients.size());
        assertSame(clientDto, actualAllClients.get(0));
    }

    /**
     * Test {@link ClientServiceImpl#getAllClients()}.
     * <ul>
     *   <li>Given {@link ModelMapper}.</li>
     *   <li>Then throw {@link ResourceNotFoundException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ClientServiceImpl#getAllClients()}
     */
    @Test
    @DisplayName("Test getAllClients(); given ModelMapper; then throw ResourceNotFoundException")
    void testGetAllClients_givenModelMapper_thenThrowResourceNotFoundException() {
        // Arrange
        when(clientRepo.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> clientServiceImpl.getAllClients());
        verify(clientRepo).findAll();
    }

    /**
     * Test {@link ClientServiceImpl#getClientById(Long)}.
     * <p>
     * Method under test: {@link ClientServiceImpl#getClientById(Long)}
     */
    @Test
    @DisplayName("Test getClientById(Long)")
    void testGetClientById() {
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
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ClientDto>>any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> clientServiceImpl.getClientById(1L));
        verify(modelMapper).map(isA(Object.class), isA(Class.class));
        verify(clientRepo).findById(eq(1L));
    }

    /**
     * Test {@link ClientServiceImpl#getClientById(Long)}.
     * <ul>
     *   <li>Given {@link Client#Client()} Budget is ten.</li>
     *   <li>Then return {@link ClientDto#ClientDto()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ClientServiceImpl#getClientById(Long)}
     */
    @Test
    @DisplayName("Test getClientById(Long); given Client() Budget is ten; then return ClientDto()")
    void testGetClientById_givenClientBudgetIsTen_thenReturnClientDto() {
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
        ClientDto clientDto = new ClientDto();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ClientDto>>any())).thenReturn(clientDto);

        // Act
        ClientDto actualClientById = clientServiceImpl.getClientById(1L);

        // Assert
        verify(modelMapper).map(isA(Object.class), isA(Class.class));
        verify(clientRepo).findById(eq(1L));
        assertSame(clientDto, actualClientById);
    }

    /**
     * Test {@link ClientServiceImpl#getClientById(Long)}.
     * <ul>
     *   <li>Given {@link ClientRepo} {@link CrudRepository#findById(Object)} return
     * empty.</li>
     * </ul>
     * <p>
     * Method under test: {@link ClientServiceImpl#getClientById(Long)}
     */
    @Test
    @DisplayName("Test getClientById(Long); given ClientRepo findById(Object) return empty")
    void testGetClientById_givenClientRepoFindByIdReturnEmpty() {
        // Arrange
        Optional<Client> emptyResult = Optional.empty();
        when(clientRepo.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn("Map");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ClientDto>>any())).thenReturn(new ClientDto());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> clientServiceImpl.getClientById(1L));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
        verify(clientRepo).findById(eq(1L));
    }
}
