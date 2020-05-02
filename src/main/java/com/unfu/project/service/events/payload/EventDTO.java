package com.unfu.project.service.events.payload;

import com.unfu.project.domain.events.TargetType;
import com.unfu.project.service.events.payload.response.EventResponse;
import com.unfu.project.service.users.payload.response.PublicUserResponse;
import lombok.Data;

import java.util.Set;

@Data
public class EventDTO {

    private Long id;

    private TargetType targetType;

    private EventResponse googleEvent;

    private Set<PublicUserResponse> users;
}
