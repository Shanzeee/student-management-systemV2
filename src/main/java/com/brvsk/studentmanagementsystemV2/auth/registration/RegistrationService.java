package com.brvsk.studentmanagementsystemV2.auth.registration;

import com.brvsk.studentmanagementsystemV2.auth.appuser.AppUserRepository;
import com.brvsk.studentmanagementsystemV2.auth.appuser.AppUserRole;
import com.brvsk.studentmanagementsystemV2.auth.appuser.AppUserService;
import com.brvsk.studentmanagementsystemV2.auth.appuser.AppUser;
import com.brvsk.studentmanagementsystemV2.auth.registration.token.ConfirmationToken;
import com.brvsk.studentmanagementsystemV2.auth.registration.token.ConfirmationTokenService;
import com.brvsk.studentmanagementsystemV2.email.EmailSender;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final AppUserRepository appUserRepository;
    private final AppUserService appUserService;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail){
            throw new IllegalStateException("email is not valid");
        }

        String token = appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER

                )
        );

        String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;
        emailSender.send(
                request.getEmail(),
                "Confirm your email",
                buildEmail(request.getFirstName(), link));
        return token;
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(
                confirmationToken.getAppUser().getEmail());
        return "confirmed";
    }

    private String buildEmail(String name, String link) {
        return "Hello " + name +
                "\nConfirm your email by clicking on link below \n"+
                link;
    }
}
