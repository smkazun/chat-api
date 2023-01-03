package org.chatapp.infrastructure;

import org.chatapp.infrastructure.config.SecurityConfiguration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    public SecurityWebApplicationInitializer(){
        super(SecurityConfiguration.class);
    }
}
