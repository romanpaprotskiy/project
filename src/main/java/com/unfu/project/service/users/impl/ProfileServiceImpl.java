package com.unfu.project.service.users.impl;

import com.unfu.project.service.events.GoogleEventService;
import com.unfu.project.service.events.mapper.EventResponseMapper;
import com.unfu.project.service.events.payload.response.EventResponse;
import com.unfu.project.service.managerment.SubjectService;
import com.unfu.project.service.users.ProfileService;
import com.unfu.project.service.users.StudentService;
import com.unfu.project.service.users.UserService;
import com.unfu.project.service.users.payload.response.ProfileResponse;
import com.unfu.project.service.users.payload.response.StudentDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final UserService userService;

    private final StudentService studentService;

    private final SubjectService subjectService;

    private final GoogleEventService googleEventService;

    private final EventResponseMapper eventResponseMapper;

    @Override
    public ProfileResponse getCurrentUserProfile() throws IOException {
        var userResponse = userService.findCurrentUserResponse();
        Optional<StudentDTO> student = studentService.findResponseByUserId(userResponse.getId());
        var subjects = subjectService
                .getStudentSubjectsByStudentId(student.orElse(new StudentDTO()).getId());
        LocalDate now = LocalDate.now();
        final DayOfWeek firstDayOfWeek = WeekFields.of(Locale.ENGLISH).getFirstDayOfWeek();
        final DayOfWeek lastDayOfWeek = DayOfWeek.of(((firstDayOfWeek.getValue() + 5) % DayOfWeek.values().length) + 1);
        LocalDate monday = LocalDate.now().with(TemporalAdjusters.previousOrSame(firstDayOfWeek));
        LocalDate sunday = LocalDate.now().with(TemporalAdjusters.nextOrSame(lastDayOfWeek));
        List<EventResponse> events = googleEventService
                .getEventsByEmailAndDateIn(userResponse.getEmail(), monday, sunday)
                .stream()
                .map(eventResponseMapper::map)
                .collect(Collectors.toList());
        return ProfileResponse.builder()
                .user(userResponse)
                .student(student.orElse(null))
                .subjects(subjects)
                .events(events)
                .build();
    }
}
