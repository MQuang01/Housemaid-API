package com.cghue.projecthousemaidwebapp.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum EHouseSize {
    HOUSE_1(1),
    HOUSE_2(2),
    HOUSE_3(3);
    private Integer value;
    public EHouseSize findByValue(Integer value) {
        for(EHouseSize houseSize : EHouseSize.values()) {
            if(Objects.equals(houseSize.value, value)) {
                return houseSize;
            }
        }
        return null;
    }
}
