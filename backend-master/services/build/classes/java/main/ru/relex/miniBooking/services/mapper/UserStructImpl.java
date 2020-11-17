package ru.relex.miniBooking.services.mapper;

import java.time.Instant;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.relex.miniBooking.bd.model.UserModel;
import ru.relex.miniBooking.commons.model.Role;
import ru.relex.miniBooking.services.model.Location;
import ru.relex.miniBooking.services.model.user.ExistingUser;
import ru.relex.miniBooking.services.model.user.NewUser;
import ru.relex.miniBooking.services.model.user.PersonalInfo;
import ru.relex.miniBooking.services.model.user.UpdatableUser;
import ru.relex.miniBooking.services.model.user.UserStatus;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-14T17:51:22+0300",
    comments = "version: 1.4.0.Beta2, compiler: IncrementalProcessingEnvironment from gradle-language-java-4.10.3.jar, environment: Java 11.0.5 (JetBrains s.r.o)"
)
@Component
public class UserStructImpl implements UserStruct {

    @Override
    public UserModel fromNewUser(NewUser user, String password) {
        if ( user == null && password == null ) {
            return null;
        }

        UserModel userModel = new UserModel();

        if ( user != null ) {
            userModel.setFirstName( userPersonalInfoFirstName( user ) );
            userModel.setLastName( userPersonalInfoLastName( user ) );
            userModel.setEmail( userPersonalInfoEmail( user ) );
            userModel.setPhone( userPersonalInfoPhoneNumber( user ) );
            userModel.setCountry( userPersonalInfoLocationCountry( user ) );
            userModel.setCity( userPersonalInfoLocationCity( user ) );
            userModel.setStreet( userPersonalInfoLocationStreet( user ) );
            userModel.setHouse( userPersonalInfoLocationHouse( user ) );
            userModel.setUsername( user.getUsername() );
            userModel.setRole( user.getRole() );
        }
        if ( password != null ) {
            userModel.setPassword( password );
        }
        userModel.setActive( false );
        userModel.setLocked( false );

        return userModel;
    }

    @Override
    public ExistingUser toExistingUser(UserModel model, long id, Instant createdAt) {
        if ( model == null && createdAt == null ) {
            return null;
        }

        PersonalInfo personalInfo = null;
        UserStatus status = null;
        Role role = null;
        String username = null;
        if ( model != null ) {
            personalInfo = userModelToPersonalInfo( model );
            status = userModelToUserStatus( model );
            role = model.getRole();
            username = model.getUsername();
        }
        long id1 = 0L;
        id1 = id;

        ExistingUser existingUser = new ExistingUser( role, personalInfo, id1, username, status );

        if ( createdAt != null ) {
            existingUser.setCreatedAt( createdAt );
        }

        return existingUser;
    }

    @Override
    public ExistingUser toExistingUser(UserModel byId) {
        if ( byId == null ) {
            return null;
        }

        PersonalInfo personalInfo = null;
        UserStatus status = null;
        Role role = null;
        long id = 0L;
        String username = null;

        personalInfo = userModelToPersonalInfo1( byId );
        status = userModelToUserStatus1( byId );
        role = byId.getRole();
        id = byId.getId();
        username = byId.getUsername();

        ExistingUser existingUser = new ExistingUser( role, personalInfo, id, username, status );

        existingUser.setCreatedAt( byId.getCreatedAt() );

        return existingUser;
    }

    @Override
    public UserModel mergeUpdated(UpdatableUser user, UserModel prev) {
        if ( user == null && prev == null ) {
            return null;
        }

        UserModel userModel = new UserModel();

        if ( user != null ) {
            userModel.setPassword( user.getPassword() );
            userModel.setRole( user.getRole() );
            userModel.setFirstName( userPersonalInfoFirstName1( user ) );
            userModel.setLastName( userPersonalInfoLastName1( user ) );
            userModel.setEmail( userPersonalInfoEmail1( user ) );
            userModel.setPhone( userPersonalInfoPhoneNumber1( user ) );
            userModel.setActive( userStatusActive( user ) );
            userModel.setLocked( userStatusBlocked( user ) );
            userModel.setCountry( userPersonalInfoLocationCountry1( user ) );
            userModel.setCity( userPersonalInfoLocationCity1( user ) );
            userModel.setStreet( userPersonalInfoLocationStreet1( user ) );
            userModel.setHouse( userPersonalInfoLocationHouse1( user ) );
        }
        if ( prev != null ) {
            userModel.setId( prev.getId() );
            userModel.setUsername( prev.getUsername() );
            userModel.setCreatedAt( prev.getCreatedAt() );
        }

        return userModel;
    }

    @Override
    public ExistingUser merge(ExistingUser user, UpdatableUser updateData) {
        if ( user == null && updateData == null ) {
            return null;
        }

        long id = 0L;
        String username = null;
        if ( user != null ) {
            id = user.getId();
            username = user.getUsername();
        }
        UserStatus status = null;
        PersonalInfo personalInfo = null;
        Role role = null;
        if ( updateData != null ) {
            status = updateData.getStatus();
            personalInfo = updateData.getPersonalInfo();
            role = updateData.getRole();
        }

        ExistingUser existingUser = new ExistingUser( role, personalInfo, id, username, status );

        if ( user != null ) {
            existingUser.setCreatedAt( user.getCreatedAt() );
        }

        return existingUser;
    }

    @Override
    public ExistingUser fromNewUser(NewUser user, long newId) {
        if ( user == null ) {
            return null;
        }

        UserStatus status = null;
        Role role = null;
        PersonalInfo personalInfo = null;
        String username = null;
        if ( user != null ) {
            status = defaultStatusMapper( user );
            role = user.getRole();
            personalInfo = user.getPersonalInfo();
            username = user.getUsername();
        }
        long id = 0L;
        id = newId;

        ExistingUser existingUser = new ExistingUser( role, personalInfo, id, username, status );

        return existingUser;
    }

    private String userPersonalInfoFirstName(NewUser newUser) {
        if ( newUser == null ) {
            return null;
        }
        PersonalInfo personalInfo = newUser.getPersonalInfo();
        if ( personalInfo == null ) {
            return null;
        }
        String firstName = personalInfo.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    private String userPersonalInfoLastName(NewUser newUser) {
        if ( newUser == null ) {
            return null;
        }
        PersonalInfo personalInfo = newUser.getPersonalInfo();
        if ( personalInfo == null ) {
            return null;
        }
        String lastName = personalInfo.getLastName();
        if ( lastName == null ) {
            return null;
        }
        return lastName;
    }

    private String userPersonalInfoEmail(NewUser newUser) {
        if ( newUser == null ) {
            return null;
        }
        PersonalInfo personalInfo = newUser.getPersonalInfo();
        if ( personalInfo == null ) {
            return null;
        }
        String email = personalInfo.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }

    private String userPersonalInfoPhoneNumber(NewUser newUser) {
        if ( newUser == null ) {
            return null;
        }
        PersonalInfo personalInfo = newUser.getPersonalInfo();
        if ( personalInfo == null ) {
            return null;
        }
        String phoneNumber = personalInfo.getPhoneNumber();
        if ( phoneNumber == null ) {
            return null;
        }
        return phoneNumber;
    }

    private String userPersonalInfoLocationCountry(NewUser newUser) {
        if ( newUser == null ) {
            return null;
        }
        PersonalInfo personalInfo = newUser.getPersonalInfo();
        if ( personalInfo == null ) {
            return null;
        }
        Location location = personalInfo.getLocation();
        if ( location == null ) {
            return null;
        }
        String country = location.getCountry();
        if ( country == null ) {
            return null;
        }
        return country;
    }

    private String userPersonalInfoLocationCity(NewUser newUser) {
        if ( newUser == null ) {
            return null;
        }
        PersonalInfo personalInfo = newUser.getPersonalInfo();
        if ( personalInfo == null ) {
            return null;
        }
        Location location = personalInfo.getLocation();
        if ( location == null ) {
            return null;
        }
        String city = location.getCity();
        if ( city == null ) {
            return null;
        }
        return city;
    }

    private String userPersonalInfoLocationStreet(NewUser newUser) {
        if ( newUser == null ) {
            return null;
        }
        PersonalInfo personalInfo = newUser.getPersonalInfo();
        if ( personalInfo == null ) {
            return null;
        }
        Location location = personalInfo.getLocation();
        if ( location == null ) {
            return null;
        }
        String street = location.getStreet();
        if ( street == null ) {
            return null;
        }
        return street;
    }

    private String userPersonalInfoLocationHouse(NewUser newUser) {
        if ( newUser == null ) {
            return null;
        }
        PersonalInfo personalInfo = newUser.getPersonalInfo();
        if ( personalInfo == null ) {
            return null;
        }
        Location location = personalInfo.getLocation();
        if ( location == null ) {
            return null;
        }
        String house = location.getHouse();
        if ( house == null ) {
            return null;
        }
        return house;
    }

    protected Location userModelToLocation(UserModel userModel) {
        if ( userModel == null ) {
            return null;
        }

        Location location = new Location();

        location.setCountry( userModel.getCountry() );
        location.setCity( userModel.getCity() );
        location.setStreet( userModel.getStreet() );
        location.setHouse( userModel.getHouse() );

        return location;
    }

    protected PersonalInfo userModelToPersonalInfo(UserModel userModel) {
        if ( userModel == null ) {
            return null;
        }

        PersonalInfo personalInfo = new PersonalInfo();

        personalInfo.setLocation( userModelToLocation( userModel ) );
        personalInfo.setFirstName( userModel.getFirstName() );
        personalInfo.setLastName( userModel.getLastName() );
        personalInfo.setEmail( userModel.getEmail() );
        personalInfo.setPhoneNumber( userModel.getPhone() );

        return personalInfo;
    }

    protected UserStatus userModelToUserStatus(UserModel userModel) {
        if ( userModel == null ) {
            return null;
        }

        UserStatus userStatus = new UserStatus();

        userStatus.setActive( userModel.isActive() );
        userStatus.setBlocked( userModel.isLocked() );

        return userStatus;
    }

    protected Location userModelToLocation1(UserModel userModel) {
        if ( userModel == null ) {
            return null;
        }

        Location location = new Location();

        location.setCountry( userModel.getCountry() );
        location.setCity( userModel.getCity() );
        location.setStreet( userModel.getStreet() );
        location.setHouse( userModel.getHouse() );

        return location;
    }

    protected PersonalInfo userModelToPersonalInfo1(UserModel userModel) {
        if ( userModel == null ) {
            return null;
        }

        PersonalInfo personalInfo = new PersonalInfo();

        personalInfo.setLocation( userModelToLocation1( userModel ) );
        personalInfo.setFirstName( userModel.getFirstName() );
        personalInfo.setLastName( userModel.getLastName() );
        personalInfo.setEmail( userModel.getEmail() );
        personalInfo.setPhoneNumber( userModel.getPhone() );

        return personalInfo;
    }

    protected UserStatus userModelToUserStatus1(UserModel userModel) {
        if ( userModel == null ) {
            return null;
        }

        UserStatus userStatus = new UserStatus();

        userStatus.setActive( userModel.isActive() );
        userStatus.setBlocked( userModel.isLocked() );

        return userStatus;
    }

    private String userPersonalInfoFirstName1(UpdatableUser updatableUser) {
        if ( updatableUser == null ) {
            return null;
        }
        PersonalInfo personalInfo = updatableUser.getPersonalInfo();
        if ( personalInfo == null ) {
            return null;
        }
        String firstName = personalInfo.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    private String userPersonalInfoLastName1(UpdatableUser updatableUser) {
        if ( updatableUser == null ) {
            return null;
        }
        PersonalInfo personalInfo = updatableUser.getPersonalInfo();
        if ( personalInfo == null ) {
            return null;
        }
        String lastName = personalInfo.getLastName();
        if ( lastName == null ) {
            return null;
        }
        return lastName;
    }

    private String userPersonalInfoEmail1(UpdatableUser updatableUser) {
        if ( updatableUser == null ) {
            return null;
        }
        PersonalInfo personalInfo = updatableUser.getPersonalInfo();
        if ( personalInfo == null ) {
            return null;
        }
        String email = personalInfo.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }

    private String userPersonalInfoPhoneNumber1(UpdatableUser updatableUser) {
        if ( updatableUser == null ) {
            return null;
        }
        PersonalInfo personalInfo = updatableUser.getPersonalInfo();
        if ( personalInfo == null ) {
            return null;
        }
        String phoneNumber = personalInfo.getPhoneNumber();
        if ( phoneNumber == null ) {
            return null;
        }
        return phoneNumber;
    }

    private boolean userStatusActive(UpdatableUser updatableUser) {
        if ( updatableUser == null ) {
            return false;
        }
        UserStatus status = updatableUser.getStatus();
        if ( status == null ) {
            return false;
        }
        boolean active = status.isActive();
        return active;
    }

    private boolean userStatusBlocked(UpdatableUser updatableUser) {
        if ( updatableUser == null ) {
            return false;
        }
        UserStatus status = updatableUser.getStatus();
        if ( status == null ) {
            return false;
        }
        boolean blocked = status.isBlocked();
        return blocked;
    }

    private String userPersonalInfoLocationCountry1(UpdatableUser updatableUser) {
        if ( updatableUser == null ) {
            return null;
        }
        PersonalInfo personalInfo = updatableUser.getPersonalInfo();
        if ( personalInfo == null ) {
            return null;
        }
        Location location = personalInfo.getLocation();
        if ( location == null ) {
            return null;
        }
        String country = location.getCountry();
        if ( country == null ) {
            return null;
        }
        return country;
    }

    private String userPersonalInfoLocationCity1(UpdatableUser updatableUser) {
        if ( updatableUser == null ) {
            return null;
        }
        PersonalInfo personalInfo = updatableUser.getPersonalInfo();
        if ( personalInfo == null ) {
            return null;
        }
        Location location = personalInfo.getLocation();
        if ( location == null ) {
            return null;
        }
        String city = location.getCity();
        if ( city == null ) {
            return null;
        }
        return city;
    }

    private String userPersonalInfoLocationStreet1(UpdatableUser updatableUser) {
        if ( updatableUser == null ) {
            return null;
        }
        PersonalInfo personalInfo = updatableUser.getPersonalInfo();
        if ( personalInfo == null ) {
            return null;
        }
        Location location = personalInfo.getLocation();
        if ( location == null ) {
            return null;
        }
        String street = location.getStreet();
        if ( street == null ) {
            return null;
        }
        return street;
    }

    private String userPersonalInfoLocationHouse1(UpdatableUser updatableUser) {
        if ( updatableUser == null ) {
            return null;
        }
        PersonalInfo personalInfo = updatableUser.getPersonalInfo();
        if ( personalInfo == null ) {
            return null;
        }
        Location location = personalInfo.getLocation();
        if ( location == null ) {
            return null;
        }
        String house = location.getHouse();
        if ( house == null ) {
            return null;
        }
        return house;
    }
}
