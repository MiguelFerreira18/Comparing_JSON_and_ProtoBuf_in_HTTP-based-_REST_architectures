package com.isep.acme.Mapper;

import com.isep.acme.model.H2Entity.User;
import com.isep.acme.model.View.UserView;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Profile;


@Profile("h2Profile")
@Mapper(componentModel = "spring", uses = User.class)
public abstract class UserViewMapper {

    public abstract UserView toUserView(User user);
}
