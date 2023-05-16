package com.brvsk.studentmanagementsystemV2.model.request;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GroupRequest {

    @NotNull
    private String groupName;
    @NotNull
    private String departmentShortcut;
//    @NotNull
//    private List<Long> studentsId;
}
