package org.example.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Handler.Error.ApiRequestException;
import org.example.model.response.AuthResponse;
import org.example.services.Auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Collection;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private AuthService service;
    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        //get jwt token from http request
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        //System.out.println("header:: " + authorizationHeader);

        String token = null;
        String roll = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            roll = JwtTokenProvider.decodeRoll(token);
        } else {

            AccessDeniedException e = new AccessDeniedException("bearer missing");
            handlerExceptionResolver.resolveException(httpServletRequest, httpServletResponse, null, e);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

//        if (roll != null) {
//            AuthResponse response = service.studentLogin(roll);
//            UserDetails userDetails = new UserDetails() {
//                @Override
//                public Collection<? extends GrantedAuthority> getAuthorities() {
//                    return null;
//                }
//
//                @Override
//                public String getPassword() {
//                    return null;
//                }
//
//                @Override
//                public String getUsername() {
//                    return response.roll_no;
//                }
//
//                @Override
//                public boolean isAccountNonExpired() {
//                    return false;
//                }
//
//                @Override
//                public boolean isAccountNonLocked() {
//                    return false;
//                }
//
//                @Override
//                public boolean isCredentialsNonExpired() {
//                    return false;
//                }
//
//                @Override
//                public boolean isEnabled() {
//                    return false;
//                }
//            };
//
//            if (!JwtTokenProvider.checkExpiration(token) && JwtTokenProvider.validateToken(token)) {
//                //System.out.println("un: " + userDetails.getUsername());
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken
//                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//        } else {
//            //System.out.println("error");
//        }

        AuthResponse response = service.studentLogin(roll);
        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return response.roll_no;
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        };

        if (!JwtTokenProvider.checkExpiration(token) && JwtTokenProvider.validateToken(token)) {
            //System.out.println("un: " + userDetails.getUsername());
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
