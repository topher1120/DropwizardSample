package com.cmware.dw.example.auth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import com.cmware.dw.example.domain.User;
import com.google.common.base.Optional;

/**
 * Simple authenticator as a demo.
 */
public class SimpleAuthenticator implements Authenticator<BasicCredentials, User>
{
    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException
    {
        if ("secret".equals(credentials.getPassword())) {
            return Optional.of(new User(credentials.getUsername()));
        }
        return Optional.absent();
    }
}
