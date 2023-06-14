package com.brvsk.studentmanagementsystemV2.auth.registration;

import com.brvsk.studentmanagementsystemV2.auth.appuser.AppUserRepository;
import com.brvsk.studentmanagementsystemV2.auth.appuser.AppUserService;
import com.brvsk.studentmanagementsystemV2.auth.registration.token.ConfirmationToken;
import com.brvsk.studentmanagementsystemV2.auth.registration.token.ConfirmationTokenRepository;
import com.brvsk.studentmanagementsystemV2.auth.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final ConfirmationTokenService confirmationTokenService;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final AppUserRepository appUserRepository;

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
//        confirmationTokenRepository.delete(confirmationToken);
//        appUserRepository.delete(confirmationToken.getAppUser());

        return "confirmed";
    }
}
