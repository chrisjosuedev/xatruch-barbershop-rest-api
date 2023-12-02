package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.app;

import dev.chrisjosue.xatruchbarbershopapi.domain.entity.User;

import java.security.Principal;

public interface AuthenticationService {
    User getUserLoggedIn(Principal principal);
}
