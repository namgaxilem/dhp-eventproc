package com.humana.dhp.eventproc.portal.security;

import java.security.Principal;

public interface UserInfoService {
    UserInfo getUserInfo(Principal principal);
}
