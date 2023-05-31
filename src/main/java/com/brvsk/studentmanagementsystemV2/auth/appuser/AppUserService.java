package com.brvsk.studentmanagementsystemV2.auth.appuser;

import com.brvsk.studentmanagementsystemV2.auth.registration.EmailValidator;
import com.brvsk.studentmanagementsystemV2.auth.registration.token.ConfirmationToken;
import com.brvsk.studentmanagementsystemV2.auth.registration.token.ConfirmationTokenService;
import com.brvsk.studentmanagementsystemV2.email.EmailSender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailValidator emailValidator;
    private final EmailSender emailSender;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(AppUser appUser){
        boolean isValidEmail = emailValidator.test(appUser.getEmail());
        if (!isValidEmail){
            throw new IllegalStateException("email is not valid");
        }
        boolean userExists = appUserRepository
                .findByEmail(appUser.getEmail())
                .isPresent();
        if (userExists){
            throw new IllegalStateException("email already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;
        emailSender.send(
                appUser.getEmail(),
                "Confirm your email",
                buildConfirmationEmail(appUser.getFirstName(), link));
        return token;
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }

    private String buildConfirmationEmail(String name, String link) {
        return "Hello " + name +
                "\nConfirm your email by clicking on link below \n"+
                link;
    }
}
