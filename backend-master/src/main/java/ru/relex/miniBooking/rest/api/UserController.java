package ru.relex.miniBooking.rest.api;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.relex.miniBooking.rest.exception.NoSuchObjectException;
import ru.relex.miniBooking.services.facade.UserFacade;
import ru.relex.miniBooking.services.model.hotel.NewHotel;
import ru.relex.miniBooking.services.model.user.ExistingUser;
import ru.relex.miniBooking.services.model.user.NewUser;
import ru.relex.miniBooking.services.model.user.UpdatableUser;

@RestController
@RequestMapping(
        value = "/user"
)

public class UserController {
    private final UserFacade userFacade;

    @Autowired
    public UserController ( UserFacade userFacade ) {
        this.userFacade = userFacade;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ExistingUser createUser ( @RequestBody final NewUser user ) {
        return userFacade.createUser ( user );
    }

    @GetMapping(path = "/{id}")
    ExistingUser getById ( @PathVariable("id") long id ) {

        final var user = userFacade.getById ( id );
        if ( user == null ) {
            throw new NoSuchObjectException ( User.class );
        }

        return user;
    }

    @PutMapping(path = "/{id}")
    ExistingUser updateUser ( @PathVariable("id") long id,
                              @RequestBody UpdatableUser updatableUser ) {
        final var user = userFacade.update ( id, updatableUser );

        if ( user == null ) {
            throw new NoSuchObjectException ( NewHotel.class );
        }

        return user;
    }

    @DeleteMapping(path = "/{id}")
    void deleteUser ( @PathVariable("id") long id ) {
        if ( userFacade.deleteById ( id ) ) {
            return;
        }

        throw new NoSuchObjectException ( User.class );
    }
    @GetMapping("/current")
    Object getCurrentUser ( Authentication authentication ) {
        if ( authentication != null )
            return authentication.getPrincipal ( );
        return null;
    }

}
