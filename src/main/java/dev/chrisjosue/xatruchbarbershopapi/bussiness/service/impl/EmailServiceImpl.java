package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.EmailService;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.cases.SendEmailCase;
import dev.chrisjosue.xatruchbarbershopapi.common.exceptions.InternalServerException;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.User;
import dev.chrisjosue.xatruchbarbershopapi.domain.pojo.Mail;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.HashMap;
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
            int year = Year.now().getValue();

            Mail mail = new Mail();
            mail.setFrom(fromEmail);
            mail.setTo(user.getUsername());
            mail.setSubject("Recuperación de Contraseña");

            Map<String, Object> model = new HashMap<String, Object>();
            model.put("name", user.getFullName());
            model.put("url", resolvedClientUrl);
            model.put("year", year);
            mail.setModel(model);

            sendEmailCase.sendSimpleEmail(mail);
        } catch (Exception exception) {
            throw new InternalServerException(exception.getMessage(), "mail");
        }

    }
}
