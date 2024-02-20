//package com.movie.app.service;
//import com.movie.app.dto.UserDto;
//import com.movie.app.model.Auth;
//import com.movie.app.model.User;
//import com.movie.app.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//
//@Service
//public class CustomerUserDetailsService implements UserDetailsService {
//
//    private final UserRepository repos;
//    private final UserService service;
//
//    public CustomUserDetailsService(UserRepository repos, UserService service) {
//        this.repos = repos;
//        this.service = service;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) {
//        User user = new User();
//        UserDto userDto = service.userDto(user);
//        String password = userDto.password;
//
//        // Method to get authorities for a user
//        private Collection<? extends GrantedAuthority> getAuthorities(UserDto userDto) {
//            // Convert authorities from your UserDto to GrantedAuthority
//            return userDto.getAuthorities().stream()
//                    .map(auth -> new SimpleGrantedAuthority(auth.getName()))
//                    .collect(Collectors.toList());
//        }
//
//        Set<Auth> authorities = user.getAuthorities();
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        for (Auth authority: authorities) {
//            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
//        }
//
//        return new org.springframework.security.core.userdetails.User(username, password, grantedAuthorities);
//    }
//
//}