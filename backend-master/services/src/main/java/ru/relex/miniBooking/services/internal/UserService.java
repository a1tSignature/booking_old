package ru.relex.miniBooking.services.internal;

import ru.relex.miniBooking.services.model.user.ExistingUser;
import ru.relex.miniBooking.services.model.user.NewUser;
import ru.relex.miniBooking.services.model.user.UpdatableUser;


public interface UserService {
    ExistingUser createUser(NewUser user);

    ExistingUser getById ( long id );

    ExistingUser update ( long id, UpdatableUser updatableUser );

    boolean deleteById ( long id );
}
