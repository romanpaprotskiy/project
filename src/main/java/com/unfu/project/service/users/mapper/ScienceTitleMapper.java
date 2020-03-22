package com.unfu.project.service.users.mapper;

import com.unfu.project.domain.users.ScienceTitle;
import com.unfu.project.service.users.payload.response.ScienceTitleResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScienceTitleMapper {

    ScienceTitleResponse map(ScienceTitle scienceTitle);
}
