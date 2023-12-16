package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.EmailService;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.cases.SendEmailCase;
import dev.chrisjosue.xatruchbarbershopapi.common.enums.Templates;
import dev.chrisjosue.xatruchbarbershopapi.common.exceptions.InternalServerException;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.BookingDetailDto;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Booking;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.BookingDetail;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.User;
import dev.chrisjosue.xatruchbarbershopapi.domain.pojo.Mail;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final SendEmailCase sendEmailCase;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${clients.CLIENT_SERVER_FORGOT_PASSWORD_URI}")
    private String clientUrl;


    @Override
    public void sendRecoveryPasswordEmail(User user, String token) {
        try {
            String resolvedClientUrl = clientUrl + "?token=" + token;

            Mail mail = new Mail();
            mail.setFrom(fromEmail);
            mail.setTo(user.getUsername());
            mail.setSubject("Recuperación de Contraseña");

            Map<String, Object> model = new HashMap<String, Object>();
            model.put("name", user.getFullName());
            model.put("url", resolvedClientUrl);
            model.put("year", getCurrentYear());
            mail.setModel(model);

            sendEmailCase.sendTemplateEmail(mail, Templates.RECOVERY_PASSWORD_TEMPLATE);
        } catch (Exception exception) {
            throw new InternalServerException(exception.getMessage(), "mail");
        }
    }

    @Override
    public void sendBookingEmail(Booking booking) {
        try {
            Mail mail = new Mail();
            mail.setFrom(fromEmail);
            mail.setTo(booking.getUser().getUsername());
            mail.setSubject("Detalle de Reserva de Sesión");

            Map<String, Object> model = new HashMap<String, Object>();
            model.put("name", booking.getUser().getFullName());
            model.put("id", booking.getId());
            model.put("bookingDate", booking.getBookingDate());
            model.put("bookingTime", booking.getBookingTime());
            model.put("details", getBookingDetails(booking.getBookingDetails()));
            model.put("total", getTotal(booking.getBookingDetails()));
            model.put("year", getCurrentYear());

            mail.setModel(model);
            sendEmailCase.sendTemplateEmail(mail, Templates.BOOKING_DETAIL_TEMPLATE);
        } catch (Exception exception) {
            throw new InternalServerException(exception.getMessage(), "mail");
        }
    }

    private static List<BookingDetailDto> getBookingDetails(List<BookingDetail> details) {
        return details
                .stream()
                .map(detail -> {
                    String serviceName = detail.getBarberService().getServiceName();
                    Double price = detail.getPrice();
                    return new BookingDetailDto(serviceName, price);
                })
                .toList();
    }

    private static Double getTotal(List<BookingDetail> details) {
        return details
                .stream()
                .map(BookingDetail::getPrice)
                .reduce((double) 0, Double::sum);
    }

    private static int getCurrentYear() {
        return Year.now().getValue();
    }
}
