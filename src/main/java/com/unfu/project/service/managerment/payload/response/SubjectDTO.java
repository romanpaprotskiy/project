package com.unfu.project.service.managerment.payload.response;

import com.unfu.project.service.events.payload.response.GoogleRecurrentEventResponse;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
public class SubjectDTO {

    private Long id;

    private String name;

    private String courseNumber;

    private GoogleRecurrentEventResponse schedule;
}
