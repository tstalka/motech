package org.motechproject.appointments.api.dao;

import org.motechproject.appointments.api.model.Visit;
import org.motechproject.appointments.api.model.jobs.AppointmentReminderJob;
import org.motechproject.appointments.api.model.jobs.VisitReminderJob;
import org.motechproject.scheduler.MotechSchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AllReminderJobs {

    private MotechSchedulerService schedulerService;

    @Autowired
    public AllReminderJobs(MotechSchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    public void addAppointmentJob(String externalId, Visit visit) {
        if (visit.appointmentReminder() == null) return;
        AppointmentReminderJob appointmentReminderJob = new AppointmentReminderJob(externalId, visit);
        schedulerService.safeScheduleJob(appointmentReminderJob);
    }

    public void addVisitJob(String externalId, Visit visit) {
        if (visit.reminder() == null) return;
        VisitReminderJob visitReminderJob = new VisitReminderJob(externalId, visit);
        schedulerService.safeScheduleJob(visitReminderJob);
    }

    public void removeAppointmentJob(String externalId, Visit visit) {
        schedulerService.safeUnscheduleJob(AppointmentReminderJob.SUBJECT, AppointmentReminderJob.getJobIdUsing(externalId, visit));
    }

    public void removeVisitJob(String externalId, Visit visit) {
        schedulerService.safeUnscheduleJob(VisitReminderJob.SUBJECT, VisitReminderJob.getJobIdUsing(externalId, visit));
    }

    public void rescheduleAppointmentJob(String externalId, Visit visit) {
        removeAppointmentJob(externalId, visit);
        addAppointmentJob(externalId, visit);
    }

    public void rescheduleVisitJob(String externalId, Visit visit) {
        removeVisitJob(externalId, visit);
        addVisitJob(externalId, visit);
    }

    public void removeAll(String externalId) {
        schedulerService.unscheduleAllJobs(AppointmentReminderJob.SUBJECT + externalId);
        schedulerService.unscheduleAllJobs(VisitReminderJob.SUBJECT + externalId);
    }
}
