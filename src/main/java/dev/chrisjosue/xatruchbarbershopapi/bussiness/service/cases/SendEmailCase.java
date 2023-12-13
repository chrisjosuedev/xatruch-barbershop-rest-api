package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.cases;

import dev.chrisjosue.xatruchbarbershopapi.common.enums.Templates;
import dev.chrisjosue.xatruchbarbershopapi.domain.pojo.Mail;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface SendEmailCase {
    void sendTemplateEmail(Mail mail, Templates template) throws MessagingException, IOException;
}
