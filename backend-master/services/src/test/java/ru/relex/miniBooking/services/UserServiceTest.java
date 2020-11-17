package ru.relex.miniBooking.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.relex.miniBooking.bd.mapper.UserMapper;
import ru.relex.miniBooking.bd.model.UserModel;
import ru.relex.miniBooking.commons.model.Role;
import ru.relex.miniBooking.services.internal.UserService;
import ru.relex.miniBooking.services.internal.impl.UserServiceImpl;
import ru.relex.miniBooking.services.mapper.UserStruct;
import ru.relex.miniBooking.services.model.Location;
import ru.relex.miniBooking.services.model.user.ExistingUser;
import ru.relex.miniBooking.services.model.user.NewUser;
import ru.relex.miniBooking.services.model.user.PersonalInfo;
import ru.relex.miniBooking.services.model.user.UserStatus;
import ru.relex.miniBooking.services.security.PasswordEncoder;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class UserServiceTest {
    private static LocalDate date = LocalDate.parse ( "9999-12-31" );
    private static Instant instant = date.atStartOfDay ( ZoneId.of ( "Europe/Paris" ) ).toInstant ( );
    private static UserService userService;
    private static UserStruct userStructMock;
    private static UserMapper userMapperMock;
    private static final String PASSWORD = "123";
    private static PasswordEncoder encoderMock;
    private static PersonalInfo personalInfo = new PersonalInfo ( "asdsd", "asdsad", "adasdsa", "adsadasd", new Location ( "asddd", "asdad", "asdasd", "asdasdas" ) );
    private static NewUser newUser = NewUser.builder ( ).username ( "123" ).password ( PASSWORD ).personalInfo ( personalInfo ).role ( Role.TENANT ).build ( );
    private static ExistingUser existingUser = ExistingUser.builder ( ).role ( Role.TENANT ).personalInfo ( personalInfo ).id ( 1 ).status ( new UserStatus ( ) ).username ( "sasd" ).build ( );
    private static UserModel userModel = new UserModel ( 1, "asdsd", "asdsad", "adasdsa", "adsadasd", false, false, "123", PASSWORD, instant, Role.TENANT, "asddd", "asdad", "asdasd", "asdasdas" );

    @BeforeEach
    void setup ( ) {
        userStructMock = Mockito.mock ( UserStruct.class );
        encoderMock = Mockito.mock ( PasswordEncoder.class );
        userMapperMock = Mockito.mock ( UserMapper.class );

        Mockito.when ( encoderMock.encode ( PASSWORD ) ).thenReturn ( PASSWORD );

        Mockito.when ( userStructMock.defaultStatusMapper ( null ) ).thenReturn ( null );
        Mockito.when ( userStructMock.fromNewUser ( Mockito.argThat ( a -> !newUser.equals ( a ) ), Mockito.anyLong ( ) ) ).thenThrow ( new RuntimeException ( ) );
        Mockito.when ( userStructMock.fromNewUser ( newUser, 1L ) ).thenReturn ( existingUser );
        Mockito.when ( userStructMock.fromNewUser ( newUser, PASSWORD ) ).thenReturn ( userModel );
        Mockito.when ( userStructMock.toExistingUser ( userModel, 1, instant ) ).thenReturn ( existingUser );
        Mockito.when ( userStructMock.toExistingUser ( userModel ) ).thenReturn ( existingUser );
        Mockito.when ( userStructMock.defaultStatusMapper ( Mockito.any ( ) ) ).thenThrow ( new RuntimeException ( ) );
        Mockito.when ( userStructMock.merge ( Mockito.any ( ), Mockito.any ( ) ) ).thenThrow ( new RuntimeException ( ) );

        Mockito.when ( userMapperMock.createUser ( Mockito.any ( ) ) ).thenReturn ( userModel );
        Mockito.when ( userMapperMock.findById ( 1 ) ).thenReturn ( userModel );
        Mockito.doNothing ( ).when ( userMapperMock ).saveUserRole ( Mockito.anyLong ( ), Mockito.any ( ) );
        Mockito.when ( userMapperMock.deleteUser ( 1 ) ).thenReturn ( true );
        Mockito.when ( userMapperMock.deleteUser ( Mockito.anyLong ( ) ) ).thenReturn ( false );
        userService = new UserServiceImpl ( userStructMock, userMapperMock, encoderMock );
    }

    @Test
    void testMock ( ) {
        Assertions.assertEquals ( userService.createUser ( newUser ), existingUser );

    }

    @Test
    void checkUserFound ( ) {
        Assertions.assertEquals ( userService.getById ( 1L ), existingUser );

    }

    @Test
    void checkUserNotExists ( ) {
        Assertions.assertNull ( userService.getById ( 2L ) );
    }
}
