package com.brvsk.studentmanagementsystemV2.auth.appuser;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class AppUserController {

    private final AppUserService appUserService;

//    @GetMapping
//    public List<AppUser> getAllUsers(){
//        return appUserService.getUsers();
//    }
//
//    @PostMapping
//    public void addUser(AppUser appUser){
//        appUserService.saveUser(appUser);
//    }
//    @GetMapping("/role/addtouser")
//    public void refreshToken(HttpServletRequest request, HttpServletResponse response){
//        String authorizationHeader = request.getHeader(AUTHORIZATION);
//    }
}
