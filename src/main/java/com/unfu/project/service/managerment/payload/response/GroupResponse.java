package com.unfu.project.service.managerment.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupResponse implements Serializable {

    private static final long serialVersionUID = -7486082223837637760L;

    private Long id;

    private String name;

    private GroupResponse parent;
}
