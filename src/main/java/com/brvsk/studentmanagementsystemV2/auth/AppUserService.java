package com.brvsk.studentmanagementsystemV2.auth;

import com.brvsk.studentmanagementsystemV2.model.entity.AppUser;
import com.brvsk.studentmanagementsystemV2.model.entity.Role;
import com.brvsk.studentmanagementsystemV2.repository.AppUserRepository;
import com.brvsk.studentmanagementsystemV2.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findAppUserByUsername(username);
        if (appUser == null){
            log.error("user not found in database");
            throw new UsernameNotFoundException("User not found in database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        appUser.getRoles()
                .forEach(role -> {
                    authorities.add(new SimpleGrantedAuthority(role.getName().toString()));
                });

        return new org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(), authorities);
    }
    public AppUser saveUser(AppUser appUser){
        log.info("Saving new user to the database");
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepository.save(appUser);
    }

    public Role saveRole(Role role){
        log.info("Saving new role to the database");
        return roleRepository.save(role);
    }

    public void addRoleToUser(String username, String roleName){
        log.info("Adding role to user");
        AppUser user = appUserRepository.findAppUserByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    public AppUser getUser(String username){
        return appUserRepository.findAppUserByUsername(username);
    }

    public List<AppUser> getUsers(){
        return appUserRepository.findAll();
    }

}
