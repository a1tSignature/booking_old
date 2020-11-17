package ru.relex.miniBooking.services.internal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.relex.miniBooking.bd.mapper.UserMapper;
import ru.relex.miniBooking.bd.model.UserModel;
import ru.relex.miniBooking.services.internal.UserService;
import ru.relex.miniBooking.services.mapper.UserStruct;
import ru.relex.miniBooking.services.model.user.ExistingUser;
import ru.relex.miniBooking.services.model.user.NewUser;
import ru.relex.miniBooking.services.model.user.UpdatableUser;
import ru.relex.miniBooking.services.security.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService {

    private final UserStruct userStruct;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserStruct userStruct, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userStruct = userStruct;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public ExistingUser createUser(NewUser user) {
        final var newPassword = passwordEncoder.encode(user.getPassword());
        final var model = userStruct.fromNewUser(user, newPassword);
        UserModel newUser = userMapper.createUser(model);
        userMapper.saveUserRole(newUser.getId(), user.getRole());
        return userStruct.toExistingUser(model, newUser.getId(), newUser.getCreatedAt());

    }

    @Override
    public ExistingUser getById(long id) {
        return userStruct.toExistingUser(userMapper.findById(id));

    }

    @Override
    public ExistingUser update(long id, UpdatableUser updatableUser) {
        UserModel previousModel = userMapper.findById(id);
        updatableUser.setPassword(passwordEncoder.encode(updatableUser.getPassword()));
        UserModel updatedModel = userStruct.mergeUpdated(updatableUser, previousModel);
        userMapper.updateUser(updatedModel);
        return userStruct.toExistingUser(updatedModel, updatedModel.getId(), updatedModel.getCreatedAt());
    }

    @Override
    public boolean deleteById(long id) {
        return userMapper.deleteUser(id);
    }

}
