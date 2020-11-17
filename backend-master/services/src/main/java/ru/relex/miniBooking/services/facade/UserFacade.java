package ru.relex.miniBooking.services.facade;

import ru.relex.miniBooking.services.model.user.ExistingUser;
import ru.relex.miniBooking.services.model.user.NewUser;
import ru.relex.miniBooking.services.model.user.UpdatableUser;

import javax.validation.Valid;

public interface UserFacade {
    ExistingUser createUser(@Valid NewUser user);

    ExistingUser getById(long id);

    ExistingUser update( long id, UpdatableUser updatableUser);

    boolean deleteById(long id);

}
