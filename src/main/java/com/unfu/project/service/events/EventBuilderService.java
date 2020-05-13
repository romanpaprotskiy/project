package com.unfu.project.service.events;

import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventReminder;
import com.unfu.project.service.events.builder.RecurrenceRuleBuilder;
import com.unfu.project.service.events.payload.Recurrence;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Scope("prototype")
public final class EventBuilderService {

    private final EventDateTimeService eventDateTimeService;

    private final Event event = new Event();

    public EventBuilderService(EventDateTimeService eventDateTimeService) {
        this.eventDateTimeService = eventDateTimeService;
    }

    public EventBuilderService summary(String summary) {
        event.setSummary(summary);
        return this;
    }

    public EventBuilderService location(String location) {
        event.setLocation(location);
        return this;
    }

    public EventBuilderService startDateTime(LocalDateTime dateTime) {
        event.setStart(eventDateTimeService.fromLocalDateTime(dateTime));
        return this;
    }

    public EventBuilderService endDateTime(LocalDateTime dateTime) {
        event.setEnd(eventDateTimeService.fromLocalDateTime(dateTime));
        return this;
    }

    public EventBuilderService attendees(Collection<String> emails) {
        List<EventAttendee> attendees = emails.stream()
                .distinct()
                .map(e -> new EventAttendee().setEmail(e))
                .collect(Collectors.toList());
        event.setAttendees(attendees);
        return this;
    }

    public EventBuilderService recurrence(Recurrence recurrence) {
        String recurrenceRule = RecurrenceRuleBuilder
                .construct()
                .frequency(recurrence.getFrequency())
                .count(recurrence.getCount())
                .interval(recurrence.getInterval())
                .until(recurrence.getEndDate())
                .build();
        event.setRecurrence(List.of(recurrenceRule));
        return this;
    }

    public EventBuilderService reminders() {
        EventReminder[] reminderOverrides = new EventReminder[] {
                new EventReminder().setMethod("email").setMinutes(24 * 60),
                new EventReminder().setMethod("popup").setMinutes(10),
        };
        Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides));
        event.setReminders(reminders);
        return this;
    }

    public Event build() {
        return event;
    }
}
