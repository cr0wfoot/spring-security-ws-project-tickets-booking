package com.booking.tickets.security;

import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.core.Authentication;

import java.util.Collection;

public class CustomRoleVoter extends RoleVoter {

    @Override
    public int vote(Authentication authentication, Object o, Collection collection) {
//        setRolePrefix("SEC");
        return super.vote(authentication, o, collection);
    }
}
