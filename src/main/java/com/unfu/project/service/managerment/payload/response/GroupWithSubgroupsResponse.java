package com.unfu.project.service.managerment.payload.response;

import com.unfu.project.service.users.payload.response.PublicTeacherResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupWithSubgroupsResponse implements Serializable {

    private static final long serialVersionUID = -4530303351671382758L;

    private Long id;

    private String name;

    private Long countOfStudents;

    private List<GroupWithSubgroupsResponse> subGroups;

    private PublicTeacherResponse guide;
}
