package ru.relex.miniBooking.services.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.relex.miniBooking.services.facade.UserFacade;
import ru.relex.miniBooking.services.internal.UserService;
import ru.relex.miniBooking.services.meta.Facade;
import ru.relex.miniBooking.services.model.user.ExistingUser;
import ru.relex.miniBooking.services.model.user.NewUser;
import ru.relex.miniBooking.services.model.user.UpdatableUser;

import javax.validation.Valid;

@Facade
public class UserFacadeImpl implements UserFacade {
    private final UserService userService;


    @Autowired
    public UserFacadeImpl ( final UserService userService ) {
        this.userService = userService;
    }

    @Transactional
    @Override
    public ExistingUser createUser ( @Valid final NewUser user ) {
        return userService.createUser ( user );
    }

    @Override
    public ExistingUser getById ( long id ) {
        return userService.getById ( id );
    }

    @Override
    public ExistingUser update ( long id, UpdatableUser updatableUser ) {
        return userService.update ( id, updatableUser );
    }

    @Override
    public boolean deleteById ( long id ) {
        return userService.deleteById ( id );
    }

}
