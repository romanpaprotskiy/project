package com.unfu.project.service.managerment;

import com.unfu.project.service.managerment.payload.request.GroupCreateRequest;
import com.unfu.project.service.managerment.payload.response.GroupResponse;
import com.unfu.project.service.managerment.payload.response.GroupWithSubgroupsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface GroupService {

    Page<GroupResponse> findAll(Pageable pageable);

    Collection<GroupWithSubgroupsResponse> findAllWithSubgroups();

    GroupResponse save(GroupCreateRequest request);

    List<GroupResponse> findAllWithParentNull();
}
