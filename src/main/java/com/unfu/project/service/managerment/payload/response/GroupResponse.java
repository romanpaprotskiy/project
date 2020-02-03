package com.unfu.project.service.managerment.payload.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupResponse implements Serializable {

    private static final long serialVersionUID = -7486082223837637760L;

    private Long id;

    private String name;

    private GroupResponse parent;
}
