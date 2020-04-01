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
public class GroupWithSubgroupsResponse {

    private Long id;

    private String name;

    private Long countOfStudents;

    private List<SubGroupWithCountOfStudents> subGroups;

    private PublicTeacherResponse guide;
}
