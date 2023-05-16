package com.brvsk.studentmanagementsystemV2.model.request;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroupRequest {

    @NotNull
    private String groupName;

    @NotNull
    private String departmentShortcut;
}
