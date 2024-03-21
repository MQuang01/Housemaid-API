package com.cghue.projecthousemaidwebapp.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum EShift {
    SHIFT_1(0, 8),
    SHIFT_2(8,16),
    SHIFT_3(16,24),
    SHIFT_4(0,24);

    private final int startTime;
    private final int endTime;
}
