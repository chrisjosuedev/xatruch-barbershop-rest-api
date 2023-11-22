package dev.chrisjosue.xatruchbarbershopapi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Barber extends User {
    private Long id;
    private Date hired;
    /***
     * TODO: User Child
     */
}
