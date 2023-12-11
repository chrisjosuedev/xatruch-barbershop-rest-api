package dev.chrisjosue.xatruchbarbershopapi.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mail {
    private String from;
    private String to;
    private String subject;
    private String content;
    private List<Object> attachments;
    private Map<String, Object> model;
}
