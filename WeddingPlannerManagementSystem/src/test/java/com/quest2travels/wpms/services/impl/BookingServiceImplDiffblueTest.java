package com.quest2travels.wpms.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.quest2travels.wpms.entities.Booking;
import com.quest2travels.wpms.entities.Client;
import com.quest2travels.wpms.entities.Event;
import com.quest2travels.wpms.entities.Vendor;
import com.quest2travels.wpms.exceptions.ResourceNotFoundException;
import com.quest2travels.wpms.payload.BookingRequestDto;
import com.quest2travels.wpms.payload.BookingResponseDto;
import com.quest2travels.wpms.repositories.BookingRepo;
import com.quest2travels.wpms.repositories.EventRepo;
import com.quest2travels.wpms.repositories.VendorRepo;

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

@ContextConfiguration(classes = {BookingServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class BookingServiceImplDiffblueTest {
    @MockBean
    private BookingRepo bookingRepo;

    @Autowired
    private BookingServiceImpl bookingServiceImpl;

    @MockBean
    private EventRepo eventRepo;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private VendorRepo vendorRepo;

    /**
     * Test {@link BookingServiceImpl#createBooking(BookingRequestDto)}.
     * <ul>
     *   <li>Given {@link Event} {@link Event#getBookings()} throw
     * {@link RuntimeException#RuntimeException(String)} with {@code foo}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link BookingServiceImpl#createBooking(BookingRequestDto)}
     */
    @Test
    @DisplayName("Test createBooking(BookingRequestDto); given Event getBookings() throw RuntimeException(String) with 'foo'")
    void testCreateBooking_givenEventGetBookingsThrowRuntimeExceptionWithFoo() {
        // Arrange
        Client client = new Client();
        client.setBudget(10.0d);
        client.setContact("Contact");
        client.setEmail("jane.doe@example.org");
        client.setEvents(new ArrayList<>());
        client.setId(1L);
        client.setName("Name");
        client.setWeddingDate(LocalDate.of(1970, 1, 1));
        Event event = mock(Event.class);
        when(event.getBookings()).thenThrow(new RuntimeException("foo"));
        when(event.getEventDate()).thenReturn(LocalDate.now());
        doNothing().when(event).setBookings(Mockito.<List<Booking>>any());
        doNothing().when(event).setClient(Mockito.<Client>any());
        doNothing().when(event).setCompleted(anyBoolean());
        doNothing().when(event).setEventDate(Mockito.<LocalDate>any());
        doNothing().when(event).setId(Mockito.<Long>any());
        doNothing().when(event).setName(Mockito.<String>any());
        event.setBookings(new ArrayList<>());
        event.setClient(client);
        event.setCompleted(true);
        event.setEventDate(LocalDate.of(1970, 1, 1));
        event.setId(1L);
        event.setName("Name");
        Optional<Event> ofResult = Optional.of(event);
        when(eventRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Vendor vendor = new Vendor();
        vendor.setAvailable(true);
        vendor.setBookings(new ArrayList<>());
        vendor.setContactInfo("Contact Info");
        vendor.setId(1L);
        vendor.setName("Name");
        vendor.setServiceType("Service Type");
        Optional<Vendor> ofResult2 = Optional.of(vendor);
        when(vendorRepo.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> bookingServiceImpl.createBooking(new BookingRequestDto()));
        verify(event).getBookings();
        verify(event).getEventDate();
        verify(event).setBookings(isA(List.class));
        verify(event).setClient(isA(Client.class));
        verify(event).setCompleted(eq(true));
        verify(event).setEventDate(isA(LocalDate.class));
        verify(event).setId(eq(1L));
        verify(event).setName(eq("Name"));
        verify(eventRepo).findById(isNull());
        verify(vendorRepo).findById(isNull());
    }

    /**
     * Test {@link BookingServiceImpl#createBooking(BookingRequestDto)}.
     * <ul>
     *   <li>Given {@link EventRepo} {@link CrudRepository#findById(Object)} return
     * {@link Optional} with {@link Event#Event()}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link BookingServiceImpl#createBooking(BookingRequestDto)}
     */
    @Test
    @DisplayName("Test createBooking(BookingRequestDto); given EventRepo findById(Object) return Optional with Event()")
    void testCreateBooking_givenEventRepoFindByIdReturnOptionalWithEvent() {
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
        Optional<Event> ofResult = Optional.of(event);
        when(eventRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> bookingServiceImpl.createBooking(new BookingRequestDto()));
        verify(eventRepo).findById(isNull());
    }

    /**
     * Test {@link BookingServiceImpl#createBooking(BookingRequestDto)}.
     * <ul>
     *   <li>Given {@link ModelMapper} {@link ModelMapper#map(Object, Class)} throw
     * {@link RuntimeException#RuntimeException(String)} with {@code foo}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link BookingServiceImpl#createBooking(BookingRequestDto)}
     */
    @Test
    @DisplayName("Test createBooking(BookingRequestDto); given ModelMapper map(Object, Class) throw RuntimeException(String) with 'foo'")
    void testCreateBooking_givenModelMapperMapThrowRuntimeExceptionWithFoo() {
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

        Vendor vendor = new Vendor();
        vendor.setAvailable(true);
        vendor.setBookings(new ArrayList<>());
        vendor.setContactInfo("Contact Info");
        vendor.setId(1L);
        vendor.setName("Name");
        vendor.setServiceType("Service Type");

        Booking booking = new Booking();
        booking.setActive(true);
        booking.setEvent(event);
        booking.setId(1L);
        booking.setPrice(10.0d);
        booking.setVendor(vendor);
        when(bookingRepo.save(Mockito.<Booking>any())).thenReturn(booking);

        Client client2 = new Client();
        client2.setBudget(10.0d);
        client2.setContact("Contact");
        client2.setEmail("jane.doe@example.org");
        client2.setEvents(new ArrayList<>());
        client2.setId(1L);
        client2.setName("Name");
        client2.setWeddingDate(LocalDate.of(1970, 1, 1));

        Client client3 = new Client();
        client3.setBudget(10.0d);
        client3.setContact("Contact");
        client3.setEmail("jane.doe@example.org");
        client3.setEvents(new ArrayList<>());
        client3.setId(1L);
        client3.setName("Name");
        client3.setWeddingDate(LocalDate.of(1970, 1, 1));
        Event event2 = mock(Event.class);
        when(event2.getClient()).thenReturn(client3);
        when(event2.getBookings()).thenReturn(new ArrayList<>());
        when(event2.getEventDate()).thenReturn(LocalDate.now());
        doNothing().when(event2).setBookings(Mockito.<List<Booking>>any());
        doNothing().when(event2).setClient(Mockito.<Client>any());
        doNothing().when(event2).setCompleted(anyBoolean());
        doNothing().when(event2).setEventDate(Mockito.<LocalDate>any());
        doNothing().when(event2).setId(Mockito.<Long>any());
        doNothing().when(event2).setName(Mockito.<String>any());
        event2.setBookings(new ArrayList<>());
        event2.setClient(client2);
        event2.setCompleted(true);
        event2.setEventDate(LocalDate.of(1970, 1, 1));
        event2.setId(1L);
        event2.setName("Name");
        Optional<Event> ofResult = Optional.of(event2);
        when(eventRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<BookingResponseDto>>any()))
                .thenThrow(new RuntimeException("foo"));
        Vendor vendor2 = mock(Vendor.class);
        when(vendor2.getAvailable()).thenReturn(true);
        doNothing().when(vendor2).setAvailable(Mockito.<Boolean>any());
        doNothing().when(vendor2).setBookings(Mockito.<List<Booking>>any());
        doNothing().when(vendor2).setContactInfo(Mockito.<String>any());
        doNothing().when(vendor2).setId(Mockito.<Long>any());
        doNothing().when(vendor2).setName(Mockito.<String>any());
        doNothing().when(vendor2).setServiceType(Mockito.<String>any());
        vendor2.setAvailable(true);
        vendor2.setBookings(new ArrayList<>());
        vendor2.setContactInfo("Contact Info");
        vendor2.setId(1L);
        vendor2.setName("Name");
        vendor2.setServiceType("Service Type");
        Optional<Vendor> ofResult2 = Optional.of(vendor2);
        when(vendorRepo.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> bookingServiceImpl.createBooking(new BookingRequestDto(1L, 1L, 10.0d)));
        verify(event2).getBookings();
        verify(event2).getClient();
        verify(event2).getEventDate();
        verify(event2).setBookings(isA(List.class));
        verify(event2).setClient(isA(Client.class));
        verify(event2).setCompleted(eq(true));
        verify(event2).setEventDate(isA(LocalDate.class));
        verify(event2).setId(eq(1L));
        verify(event2).setName(eq("Name"));
        verify(vendor2).getAvailable();
        verify(vendor2).setAvailable(eq(true));
        verify(vendor2).setBookings(isA(List.class));
        verify(vendor2).setContactInfo(eq("Contact Info"));
        verify(vendor2).setId(eq(1L));
        verify(vendor2).setName(eq("Name"));
        verify(vendor2).setServiceType(eq("Service Type"));
        verify(modelMapper).map(isA(Object.class), isA(Class.class));
        verify(eventRepo).findById(eq(1L));
        verify(vendorRepo).findById(eq(1L));
        verify(bookingRepo).save(isA(Booking.class));
    }

    /**
     * Test {@link BookingServiceImpl#createBooking(BookingRequestDto)}.
     * <ul>
     *   <li>Given {@link VendorRepo} {@link CrudRepository#findById(Object)} return
     * empty.</li>
     *   <li>Then throw {@link RuntimeException}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link BookingServiceImpl#createBooking(BookingRequestDto)}
     */
    @Test
    @DisplayName("Test createBooking(BookingRequestDto); given VendorRepo findById(Object) return empty; then throw RuntimeException")
    void testCreateBooking_givenVendorRepoFindByIdReturnEmpty_thenThrowRuntimeException() {
        // Arrange
        Client client = new Client();
        client.setBudget(10.0d);
        client.setContact("Contact");
        client.setEmail("jane.doe@example.org");
        client.setEvents(new ArrayList<>());
        client.setId(1L);
        client.setName("Name");
        client.setWeddingDate(LocalDate.of(1970, 1, 1));
        Event event = mock(Event.class);
        when(event.getEventDate()).thenReturn(LocalDate.now());
        doNothing().when(event).setBookings(Mockito.<List<Booking>>any());
        doNothing().when(event).setClient(Mockito.<Client>any());
        doNothing().when(event).setCompleted(anyBoolean());
        doNothing().when(event).setEventDate(Mockito.<LocalDate>any());
        doNothing().when(event).setId(Mockito.<Long>any());
        doNothing().when(event).setName(Mockito.<String>any());
        event.setBookings(new ArrayList<>());
        event.setClient(client);
        event.setCompleted(true);
        event.setEventDate(LocalDate.of(1970, 1, 1));
        event.setId(1L);
        event.setName("Name");
        Optional<Event> ofResult = Optional.of(event);
        when(eventRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Optional<Vendor> emptyResult = Optional.empty();
        when(vendorRepo.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> bookingServiceImpl.createBooking(new BookingRequestDto()));
        verify(event).getEventDate();
        verify(event).setBookings(isA(List.class));
        verify(event).setClient(isA(Client.class));
        verify(event).setCompleted(eq(true));
        verify(event).setEventDate(isA(LocalDate.class));
        verify(event).setId(eq(1L));
        verify(event).setName(eq("Name"));
        verify(eventRepo).findById(isNull());
        verify(vendorRepo).findById(isNull());
    }

    /**
     * Test {@link BookingServiceImpl#createBooking(BookingRequestDto)}.
     * <ul>
     *   <li>Then return {@link BookingResponseDto#BookingResponseDto()}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link BookingServiceImpl#createBooking(BookingRequestDto)}
     */
    @Test
    @DisplayName("Test createBooking(BookingRequestDto); then return BookingResponseDto()")
    void testCreateBooking_thenReturnBookingResponseDto() {
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

        Vendor vendor = new Vendor();
        vendor.setAvailable(true);
        vendor.setBookings(new ArrayList<>());
        vendor.setContactInfo("Contact Info");
        vendor.setId(1L);
        vendor.setName("Name");
        vendor.setServiceType("Service Type");

        Booking booking = new Booking();
        booking.setActive(true);
        booking.setEvent(event);
        booking.setId(1L);
        booking.setPrice(10.0d);
        booking.setVendor(vendor);
        when(bookingRepo.save(Mockito.<Booking>any())).thenReturn(booking);

        Client client2 = new Client();
        client2.setBudget(10.0d);
        client2.setContact("Contact");
        client2.setEmail("jane.doe@example.org");
        client2.setEvents(new ArrayList<>());
        client2.setId(1L);
        client2.setName("Name");
        client2.setWeddingDate(LocalDate.of(1970, 1, 1));

        Client client3 = new Client();
        client3.setBudget(10.0d);
        client3.setContact("Contact");
        client3.setEmail("jane.doe@example.org");
        client3.setEvents(new ArrayList<>());
        client3.setId(1L);
        client3.setName("Name");
        client3.setWeddingDate(LocalDate.of(1970, 1, 1));
        Event event2 = mock(Event.class);
        when(event2.getClient()).thenReturn(client3);
        when(event2.getBookings()).thenReturn(new ArrayList<>());
        when(event2.getEventDate()).thenReturn(LocalDate.now());
        doNothing().when(event2).setBookings(Mockito.<List<Booking>>any());
        doNothing().when(event2).setClient(Mockito.<Client>any());
        doNothing().when(event2).setCompleted(anyBoolean());
        doNothing().when(event2).setEventDate(Mockito.<LocalDate>any());
        doNothing().when(event2).setId(Mockito.<Long>any());
        doNothing().when(event2).setName(Mockito.<String>any());
        event2.setBookings(new ArrayList<>());
        event2.setClient(client2);
        event2.setCompleted(true);
        event2.setEventDate(LocalDate.of(1970, 1, 1));
        event2.setId(1L);
        event2.setName("Name");
        Optional<Event> ofResult = Optional.of(event2);
        when(eventRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<BookingResponseDto>>any()))
                .thenReturn(bookingResponseDto);
        Vendor vendor2 = mock(Vendor.class);
        when(vendor2.getAvailable()).thenReturn(true);
        doNothing().when(vendor2).setAvailable(Mockito.<Boolean>any());
        doNothing().when(vendor2).setBookings(Mockito.<List<Booking>>any());
        doNothing().when(vendor2).setContactInfo(Mockito.<String>any());
        doNothing().when(vendor2).setId(Mockito.<Long>any());
        doNothing().when(vendor2).setName(Mockito.<String>any());
        doNothing().when(vendor2).setServiceType(Mockito.<String>any());
        vendor2.setAvailable(true);
        vendor2.setBookings(new ArrayList<>());
        vendor2.setContactInfo("Contact Info");
        vendor2.setId(1L);
        vendor2.setName("Name");
        vendor2.setServiceType("Service Type");
        Optional<Vendor> ofResult2 = Optional.of(vendor2);
        when(vendorRepo.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        // Act
        BookingResponseDto actualCreateBookingResult = bookingServiceImpl
                .createBooking(new BookingRequestDto(1L, 1L, 10.0d));

        // Assert
        verify(event2).getBookings();
        verify(event2).getClient();
        verify(event2).getEventDate();
        verify(event2).setBookings(isA(List.class));
        verify(event2).setClient(isA(Client.class));
        verify(event2).setCompleted(eq(true));
        verify(event2).setEventDate(isA(LocalDate.class));
        verify(event2).setId(eq(1L));
        verify(event2).setName(eq("Name"));
        verify(vendor2).getAvailable();
        verify(vendor2).setAvailable(eq(true));
        verify(vendor2).setBookings(isA(List.class));
        verify(vendor2).setContactInfo(eq("Contact Info"));
        verify(vendor2).setId(eq(1L));
        verify(vendor2).setName(eq("Name"));
        verify(vendor2).setServiceType(eq("Service Type"));
        verify(modelMapper).map(isA(Object.class), isA(Class.class));
        verify(eventRepo).findById(eq(1L));
        verify(vendorRepo).findById(eq(1L));
        verify(bookingRepo).save(isA(Booking.class));
        assertSame(bookingResponseDto, actualCreateBookingResult);
    }

    /**
     * Test {@link BookingServiceImpl#createBooking(BookingRequestDto)}.
     * <ul>
     *   <li>Then throw {@link ResourceNotFoundException}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link BookingServiceImpl#createBooking(BookingRequestDto)}
     */
    @Test
    @DisplayName("Test createBooking(BookingRequestDto); then throw ResourceNotFoundException")
    void testCreateBooking_thenThrowResourceNotFoundException() {
        // Arrange
        Optional<Event> emptyResult = Optional.empty();
        when(eventRepo.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        Vendor vendor = new Vendor();
        vendor.setAvailable(true);
        vendor.setBookings(new ArrayList<>());
        vendor.setContactInfo("Contact Info");
        vendor.setId(1L);
        vendor.setName("Name");
        vendor.setServiceType("Service Type");
        Optional<Vendor> ofResult = Optional.of(vendor);
        when(vendorRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> bookingServiceImpl.createBooking(new BookingRequestDto()));
        verify(eventRepo).findById(isNull());
    }

    /**
     * Test {@link BookingServiceImpl#getBookingById(Long)}.
     * <ul>
     *   <li>Given {@link BookingRepo} {@link CrudRepository#findById(Object)} return
     * empty.</li>
     *   <li>Then throw {@link RuntimeException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link BookingServiceImpl#getBookingById(Long)}
     */
    @Test
    @DisplayName("Test getBookingById(Long); given BookingRepo findById(Object) return empty; then throw RuntimeException")
    void testGetBookingById_givenBookingRepoFindByIdReturnEmpty_thenThrowRuntimeException() {
        // Arrange
        Optional<Booking> emptyResult = Optional.empty();
        when(bookingRepo.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn("Map");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<BookingResponseDto>>any()))
                .thenReturn(new BookingResponseDto());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> bookingServiceImpl.getBookingById(1L));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
        verify(bookingRepo).findById(eq(1L));
    }

    /**
     * Test {@link BookingServiceImpl#getBookingById(Long)}.
     * <ul>
     *   <li>Given {@link Client#Client()} Budget is ten.</li>
     *   <li>Then return {@link BookingResponseDto#BookingResponseDto()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link BookingServiceImpl#getBookingById(Long)}
     */
    @Test
    @DisplayName("Test getBookingById(Long); given Client() Budget is ten; then return BookingResponseDto()")
    void testGetBookingById_givenClientBudgetIsTen_thenReturnBookingResponseDto() {
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

        Vendor vendor = new Vendor();
        vendor.setAvailable(true);
        vendor.setBookings(new ArrayList<>());
        vendor.setContactInfo("Contact Info");
        vendor.setId(1L);
        vendor.setName("Name");
        vendor.setServiceType("Service Type");

        Booking booking = new Booking();
        booking.setActive(true);
        booking.setEvent(event);
        booking.setId(1L);
        booking.setPrice(10.0d);
        booking.setVendor(vendor);
        Optional<Booking> ofResult = Optional.of(booking);
        when(bookingRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<BookingResponseDto>>any()))
                .thenReturn(bookingResponseDto);

        // Act
        BookingResponseDto actualBookingById = bookingServiceImpl.getBookingById(1L);

        // Assert
        verify(modelMapper).map(isA(Object.class), isA(Class.class));
        verify(bookingRepo).findById(eq(1L));
        assertSame(bookingResponseDto, actualBookingById);
    }

    /**
     * Test {@link BookingServiceImpl#getBookingById(Long)}.
     * <ul>
     *   <li>Given {@link ModelMapper} {@link ModelMapper#map(Object, Class)} throw
     * {@link RuntimeException#RuntimeException(String)} with {@code foo}.</li>
     * </ul>
     * <p>
     * Method under test: {@link BookingServiceImpl#getBookingById(Long)}
     */
    @Test
    @DisplayName("Test getBookingById(Long); given ModelMapper map(Object, Class) throw RuntimeException(String) with 'foo'")
    void testGetBookingById_givenModelMapperMapThrowRuntimeExceptionWithFoo() {
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

        Vendor vendor = new Vendor();
        vendor.setAvailable(true);
        vendor.setBookings(new ArrayList<>());
        vendor.setContactInfo("Contact Info");
        vendor.setId(1L);
        vendor.setName("Name");
        vendor.setServiceType("Service Type");

        Booking booking = new Booking();
        booking.setActive(true);
        booking.setEvent(event);
        booking.setId(1L);
        booking.setPrice(10.0d);
        booking.setVendor(vendor);
        Optional<Booking> ofResult = Optional.of(booking);
        when(bookingRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<BookingResponseDto>>any()))
                .thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> bookingServiceImpl.getBookingById(1L));
        verify(modelMapper).map(isA(Object.class), isA(Class.class));
        verify(bookingRepo).findById(eq(1L));
    }

    /**
     * Test {@link BookingServiceImpl#getBookingsByEventId(Long)}.
     * <ul>
     *   <li>Given {@link Client#Client()} Budget is {@code 0.5}.</li>
     *   <li>Then return size is two.</li>
     * </ul>
     * <p>
     * Method under test: {@link BookingServiceImpl#getBookingsByEventId(Long)}
     */
    @Test
    @DisplayName("Test getBookingsByEventId(Long); given Client() Budget is '0.5'; then return size is two")
    void testGetBookingsByEventId_givenClientBudgetIs05_thenReturnSizeIsTwo() {
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

        Vendor vendor = new Vendor();
        vendor.setAvailable(true);
        vendor.setBookings(new ArrayList<>());
        vendor.setContactInfo("Contact Info");
        vendor.setId(1L);
        vendor.setName("Name");
        vendor.setServiceType("Service Type");

        Booking booking = new Booking();
        booking.setActive(true);
        booking.setEvent(event);
        booking.setId(1L);
        booking.setPrice(10.0d);
        booking.setVendor(vendor);

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

        Vendor vendor2 = new Vendor();
        vendor2.setAvailable(false);
        vendor2.setBookings(new ArrayList<>());
        vendor2.setContactInfo("42");
        vendor2.setId(2L);
        vendor2.setName("42");
        vendor2.setServiceType("42");

        Booking booking2 = new Booking();
        booking2.setActive(false);
        booking2.setEvent(event2);
        booking2.setId(2L);
        booking2.setPrice(0.5d);
        booking2.setVendor(vendor2);

        ArrayList<Booking> bookingList = new ArrayList<>();
        bookingList.add(booking2);
        bookingList.add(booking);
        when(bookingRepo.findByEventId(Mockito.<Long>any())).thenReturn(bookingList);
        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<BookingResponseDto>>any()))
                .thenReturn(bookingResponseDto);

        // Act
        List<BookingResponseDto> actualBookingsByEventId = bookingServiceImpl.getBookingsByEventId(1L);

        // Assert
        verify(bookingRepo).findByEventId(eq(1L));
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), isA(Class.class));
        assertEquals(2, actualBookingsByEventId.size());
        assertSame(bookingResponseDto, actualBookingsByEventId.get(0));
        assertSame(bookingResponseDto, actualBookingsByEventId.get(1));
    }

    /**
     * Test {@link BookingServiceImpl#getBookingsByEventId(Long)}.
     * <ul>
     *   <li>Given {@link ModelMapper}.</li>
     *   <li>Then return Empty.</li>
     * </ul>
     * <p>
     * Method under test: {@link BookingServiceImpl#getBookingsByEventId(Long)}
     */
    @Test
    @DisplayName("Test getBookingsByEventId(Long); given ModelMapper; then return Empty")
    void testGetBookingsByEventId_givenModelMapper_thenReturnEmpty() {
        // Arrange
        when(bookingRepo.findByEventId(Mockito.<Long>any())).thenReturn(new ArrayList<>());

        // Act
        List<BookingResponseDto> actualBookingsByEventId = bookingServiceImpl.getBookingsByEventId(1L);

        // Assert
        verify(bookingRepo).findByEventId(eq(1L));
        assertTrue(actualBookingsByEventId.isEmpty());
    }

    /**
     * Test {@link BookingServiceImpl#getBookingsByEventId(Long)}.
     * <ul>
     *   <li>Then return size is one.</li>
     * </ul>
     * <p>
     * Method under test: {@link BookingServiceImpl#getBookingsByEventId(Long)}
     */
    @Test
    @DisplayName("Test getBookingsByEventId(Long); then return size is one")
    void testGetBookingsByEventId_thenReturnSizeIsOne() {
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

        Vendor vendor = new Vendor();
        vendor.setAvailable(true);
        vendor.setBookings(new ArrayList<>());
        vendor.setContactInfo("Contact Info");
        vendor.setId(1L);
        vendor.setName("Name");
        vendor.setServiceType("Service Type");

        Booking booking = new Booking();
        booking.setActive(true);
        booking.setEvent(event);
        booking.setId(1L);
        booking.setPrice(10.0d);
        booking.setVendor(vendor);

        ArrayList<Booking> bookingList = new ArrayList<>();
        bookingList.add(booking);
        when(bookingRepo.findByEventId(Mockito.<Long>any())).thenReturn(bookingList);
        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<BookingResponseDto>>any()))
                .thenReturn(bookingResponseDto);

        // Act
        List<BookingResponseDto> actualBookingsByEventId = bookingServiceImpl.getBookingsByEventId(1L);

        // Assert
        verify(bookingRepo).findByEventId(eq(1L));
        verify(modelMapper).map(isA(Object.class), isA(Class.class));
        assertEquals(1, actualBookingsByEventId.size());
        assertSame(bookingResponseDto, actualBookingsByEventId.get(0));
    }

    /**
     * Test {@link BookingServiceImpl#getBookingsByEventId(Long)}.
     * <ul>
     *   <li>Then throw {@link RuntimeException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link BookingServiceImpl#getBookingsByEventId(Long)}
     */
    @Test
    @DisplayName("Test getBookingsByEventId(Long); then throw RuntimeException")
    void testGetBookingsByEventId_thenThrowRuntimeException() {
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

        Vendor vendor = new Vendor();
        vendor.setAvailable(true);
        vendor.setBookings(new ArrayList<>());
        vendor.setContactInfo("Contact Info");
        vendor.setId(1L);
        vendor.setName("Name");
        vendor.setServiceType("Service Type");

        Booking booking = new Booking();
        booking.setActive(true);
        booking.setEvent(event);
        booking.setId(1L);
        booking.setPrice(10.0d);
        booking.setVendor(vendor);

        ArrayList<Booking> bookingList = new ArrayList<>();
        bookingList.add(booking);
        when(bookingRepo.findByEventId(Mockito.<Long>any())).thenReturn(bookingList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<BookingResponseDto>>any()))
                .thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> bookingServiceImpl.getBookingsByEventId(1L));
        verify(bookingRepo).findByEventId(eq(1L));
        verify(modelMapper).map(isA(Object.class), isA(Class.class));
    }

    /**
     * Test {@link BookingServiceImpl#getAllBookings()}.
     * <ul>
     *   <li>Given {@link Client#Client()} Budget is {@code 0.5}.</li>
     *   <li>Then return size is two.</li>
     * </ul>
     * <p>
     * Method under test: {@link BookingServiceImpl#getAllBookings()}
     */
    @Test
    @DisplayName("Test getAllBookings(); given Client() Budget is '0.5'; then return size is two")
    void testGetAllBookings_givenClientBudgetIs05_thenReturnSizeIsTwo() {
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

        Vendor vendor = new Vendor();
        vendor.setAvailable(true);
        vendor.setBookings(new ArrayList<>());
        vendor.setContactInfo("Contact Info");
        vendor.setId(1L);
        vendor.setName("Name");
        vendor.setServiceType("Service Type");

        Booking booking = new Booking();
        booking.setActive(true);
        booking.setEvent(event);
        booking.setId(1L);
        booking.setPrice(10.0d);
        booking.setVendor(vendor);

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

        Vendor vendor2 = new Vendor();
        vendor2.setAvailable(false);
        vendor2.setBookings(new ArrayList<>());
        vendor2.setContactInfo("42");
        vendor2.setId(2L);
        vendor2.setName("42");
        vendor2.setServiceType("42");

        Booking booking2 = new Booking();
        booking2.setActive(false);
        booking2.setEvent(event2);
        booking2.setId(2L);
        booking2.setPrice(0.5d);
        booking2.setVendor(vendor2);

        ArrayList<Booking> bookingList = new ArrayList<>();
        bookingList.add(booking2);
        bookingList.add(booking);
        when(bookingRepo.findAll()).thenReturn(bookingList);
        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<BookingResponseDto>>any()))
                .thenReturn(bookingResponseDto);

        // Act
        List<BookingResponseDto> actualAllBookings = bookingServiceImpl.getAllBookings();

        // Assert
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), isA(Class.class));
        verify(bookingRepo).findAll();
        assertEquals(2, actualAllBookings.size());
        assertSame(bookingResponseDto, actualAllBookings.get(0));
        assertSame(bookingResponseDto, actualAllBookings.get(1));
    }

    /**
     * Test {@link BookingServiceImpl#getAllBookings()}.
     * <ul>
     *   <li>Given {@link ModelMapper}.</li>
     *   <li>Then return Empty.</li>
     * </ul>
     * <p>
     * Method under test: {@link BookingServiceImpl#getAllBookings()}
     */
    @Test
    @DisplayName("Test getAllBookings(); given ModelMapper; then return Empty")
    void testGetAllBookings_givenModelMapper_thenReturnEmpty() {
        // Arrange
        when(bookingRepo.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<BookingResponseDto> actualAllBookings = bookingServiceImpl.getAllBookings();

        // Assert
        verify(bookingRepo).findAll();
        assertTrue(actualAllBookings.isEmpty());
    }

    /**
     * Test {@link BookingServiceImpl#getAllBookings()}.
     * <ul>
     *   <li>Then return size is one.</li>
     * </ul>
     * <p>
     * Method under test: {@link BookingServiceImpl#getAllBookings()}
     */
    @Test
    @DisplayName("Test getAllBookings(); then return size is one")
    void testGetAllBookings_thenReturnSizeIsOne() {
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

        Vendor vendor = new Vendor();
        vendor.setAvailable(true);
        vendor.setBookings(new ArrayList<>());
        vendor.setContactInfo("Contact Info");
        vendor.setId(1L);
        vendor.setName("Name");
        vendor.setServiceType("Service Type");

        Booking booking = new Booking();
        booking.setActive(true);
        booking.setEvent(event);
        booking.setId(1L);
        booking.setPrice(10.0d);
        booking.setVendor(vendor);

        ArrayList<Booking> bookingList = new ArrayList<>();
        bookingList.add(booking);
        when(bookingRepo.findAll()).thenReturn(bookingList);
        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<BookingResponseDto>>any()))
                .thenReturn(bookingResponseDto);

        // Act
        List<BookingResponseDto> actualAllBookings = bookingServiceImpl.getAllBookings();

        // Assert
        verify(modelMapper).map(isA(Object.class), isA(Class.class));
        verify(bookingRepo).findAll();
        assertEquals(1, actualAllBookings.size());
        assertSame(bookingResponseDto, actualAllBookings.get(0));
    }

    /**
     * Test {@link BookingServiceImpl#getAllBookings()}.
     * <ul>
     *   <li>Then throw {@link RuntimeException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link BookingServiceImpl#getAllBookings()}
     */
    @Test
    @DisplayName("Test getAllBookings(); then throw RuntimeException")
    void testGetAllBookings_thenThrowRuntimeException() {
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

        Vendor vendor = new Vendor();
        vendor.setAvailable(true);
        vendor.setBookings(new ArrayList<>());
        vendor.setContactInfo("Contact Info");
        vendor.setId(1L);
        vendor.setName("Name");
        vendor.setServiceType("Service Type");

        Booking booking = new Booking();
        booking.setActive(true);
        booking.setEvent(event);
        booking.setId(1L);
        booking.setPrice(10.0d);
        booking.setVendor(vendor);

        ArrayList<Booking> bookingList = new ArrayList<>();
        bookingList.add(booking);
        when(bookingRepo.findAll()).thenReturn(bookingList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<BookingResponseDto>>any()))
                .thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> bookingServiceImpl.getAllBookings());
        verify(modelMapper).map(isA(Object.class), isA(Class.class));
        verify(bookingRepo).findAll();
    }

    /**
     * Test {@link BookingServiceImpl#cancelBooking(Long)}.
     * <ul>
     *   <li>Then return {@link BookingResponseDto#BookingResponseDto()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link BookingServiceImpl#cancelBooking(Long)}
     */
    @Test
    @DisplayName("Test cancelBooking(Long); then return BookingResponseDto()")
    void testCancelBooking_thenReturnBookingResponseDto() {
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

        Vendor vendor = new Vendor();
        vendor.setAvailable(true);
        vendor.setBookings(new ArrayList<>());
        vendor.setContactInfo("Contact Info");
        vendor.setId(1L);
        vendor.setName("Name");
        vendor.setServiceType("Service Type");

        Booking booking = new Booking();
        booking.setActive(true);
        booking.setEvent(event);
        booking.setId(1L);
        booking.setPrice(10.0d);
        booking.setVendor(vendor);
        Optional<Booking> ofResult = Optional.of(booking);

        Client client2 = new Client();
        client2.setBudget(10.0d);
        client2.setContact("Contact");
        client2.setEmail("jane.doe@example.org");
        client2.setEvents(new ArrayList<>());
        client2.setId(1L);
        client2.setName("Name");
        client2.setWeddingDate(LocalDate.of(1970, 1, 1));

        Event event2 = new Event();
        event2.setBookings(new ArrayList<>());
        event2.setClient(client2);
        event2.setCompleted(true);
        event2.setEventDate(LocalDate.of(1970, 1, 1));
        event2.setId(1L);
        event2.setName("Name");

        Vendor vendor2 = new Vendor();
        vendor2.setAvailable(true);
        vendor2.setBookings(new ArrayList<>());
        vendor2.setContactInfo("Contact Info");
        vendor2.setId(1L);
        vendor2.setName("Name");
        vendor2.setServiceType("Service Type");

        Booking booking2 = new Booking();
        booking2.setActive(true);
        booking2.setEvent(event2);
        booking2.setId(1L);
        booking2.setPrice(10.0d);
        booking2.setVendor(vendor2);
        when(bookingRepo.save(Mockito.<Booking>any())).thenReturn(booking2);
        when(bookingRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<BookingResponseDto>>any()))
                .thenReturn(bookingResponseDto);

        // Act
        BookingResponseDto actualCancelBookingResult = bookingServiceImpl.cancelBooking(1L);

        // Assert
        verify(modelMapper).map(isA(Object.class), isA(Class.class));
        verify(bookingRepo).findById(eq(1L));
        verify(bookingRepo).save(isA(Booking.class));
        assertSame(bookingResponseDto, actualCancelBookingResult);
    }

    /**
     * Test {@link BookingServiceImpl#cancelBooking(Long)}.
     * <ul>
     *   <li>Then throw {@link RuntimeException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link BookingServiceImpl#cancelBooking(Long)}
     */
    @Test
    @DisplayName("Test cancelBooking(Long); then throw RuntimeException")
    void testCancelBooking_thenThrowRuntimeException() {
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

        Vendor vendor = new Vendor();
        vendor.setAvailable(true);
        vendor.setBookings(new ArrayList<>());
        vendor.setContactInfo("Contact Info");
        vendor.setId(1L);
        vendor.setName("Name");
        vendor.setServiceType("Service Type");

        Booking booking = new Booking();
        booking.setActive(true);
        booking.setEvent(event);
        booking.setId(1L);
        booking.setPrice(10.0d);
        booking.setVendor(vendor);
        Optional<Booking> ofResult = Optional.of(booking);

        Client client2 = new Client();
        client2.setBudget(10.0d);
        client2.setContact("Contact");
        client2.setEmail("jane.doe@example.org");
        client2.setEvents(new ArrayList<>());
        client2.setId(1L);
        client2.setName("Name");
        client2.setWeddingDate(LocalDate.of(1970, 1, 1));

        Event event2 = new Event();
        event2.setBookings(new ArrayList<>());
        event2.setClient(client2);
        event2.setCompleted(true);
        event2.setEventDate(LocalDate.of(1970, 1, 1));
        event2.setId(1L);
        event2.setName("Name");

        Vendor vendor2 = new Vendor();
        vendor2.setAvailable(true);
        vendor2.setBookings(new ArrayList<>());
        vendor2.setContactInfo("Contact Info");
        vendor2.setId(1L);
        vendor2.setName("Name");
        vendor2.setServiceType("Service Type");

        Booking booking2 = new Booking();
        booking2.setActive(true);
        booking2.setEvent(event2);
        booking2.setId(1L);
        booking2.setPrice(10.0d);
        booking2.setVendor(vendor2);
        when(bookingRepo.save(Mockito.<Booking>any())).thenReturn(booking2);
        when(bookingRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<BookingResponseDto>>any()))
                .thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> bookingServiceImpl.cancelBooking(1L));
        verify(modelMapper).map(isA(Object.class), isA(Class.class));
        verify(bookingRepo).findById(eq(1L));
        verify(bookingRepo).save(isA(Booking.class));
    }
}
