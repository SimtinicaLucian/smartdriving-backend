package ro.softwarelabz.smartdriving.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.softwarelabz.smartdriving.controller.response.Response;
import ro.softwarelabz.smartdriving.notification.NotificationService;
import ro.softwarelabz.smartdriving.notification.domain.Notification;
import ro.softwarelabz.smartdriving.user.domain.User;

import java.util.List;

@Slf4j
@CrossOrigin(maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/notification")
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping
    public Response<List<Notification>> list(@AuthenticationPrincipal User user) {
        return Response.one(notificationService.list(user));
    }

    @GetMapping("/{carId}")
    public Response<List<Notification>> listByCarId(@AuthenticationPrincipal User user, long carId) {
        return Response.one(notificationService.listByCarId(user, carId));
    }
}
