package com.cghue.projecthousemaidwebapp.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
public enum EShift {
    SHIFT_1(LocalTime.of(6, 0), LocalTime.of(13, 0)),
    SHIFT_2(LocalTime.of(13, 0),LocalTime.of(20, 0)),
    SHIFT_3(LocalTime.of(6, 0),LocalTime.of(20, 0));

    private final LocalTime startTime;
    private final LocalTime endTime;
}
