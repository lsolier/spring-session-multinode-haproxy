package com.okta.developer.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import java.net.InetAddress;

@RestController
public class GreetingController {

    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

    @GetMapping()
    public String hello(@AuthenticationPrincipal OidcUser oidcUser) {
        return "Hello " + oidcUser.getFullName();
    }

    @GetMapping(value = "/greeting")
    public String getGreeting(@AuthenticationPrincipal OidcUser oidcUser) {
        String serverUsed = "unknown";
        try {
            InetAddress host = InetAddress.getLocalHost();
            serverUsed = host.getHostName();
        } catch (Exception ex) {
            logger.error("Could not get hostname", ex);
        }
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        logger.info("Request response by " + serverUsed);
        return "Hello " + oidcUser.getFullName() + ", your server is " + serverUsed + ", with sessionId " + sessionId;
    }
}
