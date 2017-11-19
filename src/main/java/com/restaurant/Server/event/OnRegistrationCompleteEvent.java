package com.restaurant.Server.event;

import com.restaurant.Server.model.Staff;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@SuppressWarnings("serial")
@Getter
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private final String appUrl;
    private final Locale locale;
    private final Staff staff;

    public OnRegistrationCompleteEvent(final Staff staff, final Locale locale, final String appUrl) {
        super(staff);
        this.staff = staff;
        this.locale = locale;
        this.appUrl = appUrl;
    }

}
