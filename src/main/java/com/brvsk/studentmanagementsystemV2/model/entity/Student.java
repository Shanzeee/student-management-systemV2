package com.brvsk.studentmanagementsystemV2.model.entity;

import com.brvsk.studentmanagementsystemV2.auth.appuser.AppUser;
import com.brvsk.studentmanagementsystemV2.auth.appuser.AppUserRole;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Student")
@Table(name = "student")
public class Student extends AppUser {

    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;

    public Student(String firstName, String lastName, String email, String password, AppUserRole appUserRole, Group group) {
        super(firstName, lastName, email, password, appUserRole);
        this.group = group;
    }
}
