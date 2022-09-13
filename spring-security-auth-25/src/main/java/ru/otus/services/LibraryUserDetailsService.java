package ru.otus.services;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.domain.LibraryUser;
import ru.otus.repository.LibraryAuthorityRepository;
import ru.otus.repository.LibraryUserRepository;


@RequiredArgsConstructor
public class LibraryUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LibraryUserDetailsService.class);

    @Autowired
    private LibraryUserRepository userRepository;
    @Autowired
    private LibraryAuthorityRepository customAuthorityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException("With following username " + username);
        } else {
            LOGGER.info("getting user from repository " + username);
            var libraryUser = this.userRepository.findByUserName(username);
            LOGGER.info("User provided " + libraryUser.getUserName() + " " + libraryUser.getEnabled() + " authorities " + libraryUser.getAuthorities());
            List<GrantedAuthority> authorites = customAuthorityRepository.findByUserId(libraryUser.getId())
                                                                .stream().map(
                                                                customAuthority -> {
                                                                    LOGGER.info("Getting CustomAuthority from Repository " +  customAuthority.getAuthority());
                                                                    return customAuthority;
                                                                }).map(a -> a.getAuthority())
                                                                .map(SimpleGrantedAuthority::new)
                                                                .collect(Collectors.toList());

            return createUserDetails(libraryUser, authorites);
        }
    }

    protected UserDetails createUserDetails(LibraryUser libraryUser, List<GrantedAuthority> authorities){
        return new User(libraryUser.getUserName(), libraryUser.getPassword(),
                        libraryUser.getEnabled(), libraryUser.getAccountNonExpired(), libraryUser.getCredentialsNonExpired(), libraryUser.getAccountNonLocked(), authorities);
    }
}
