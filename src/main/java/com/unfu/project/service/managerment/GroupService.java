package com.unfu.project.service.managerment;

import com.unfu.project.service.managerment.payload.response.GroupResponse;
import com.unfu.project.service.managerment.payload.response.GroupWithSubgroupsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupService {

    Page<GroupResponse> findAll(Pageable pageable);

    Page<GroupWithSubgroupsResponse> findAllWithSubgroups(Pageable pageable);
}
