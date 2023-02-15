package com.humana.dhp.eventproc.portal.security;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserInfo {
    /**
     * Username of the current logged in user.
     */
    String userName;

    /**
     * Email of the current logged in user, this is grabbed from the `upn` field for the JWT claims.
     */
    String email;

    /**
     * First name of the current logged in user.
     */
    String name;

    /**
     * Roles of the current logged in users.
     */
    Set<String> roles;
}
