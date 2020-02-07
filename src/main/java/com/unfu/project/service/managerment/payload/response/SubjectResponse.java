package com.unfu.project.service.managerment.payload.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
public class SubjectResponse implements Serializable {

    private static final long serialVersionUID = 2587878160068993197L;

    private Long id;

    private String name;
}
