package com.cghue.projecthousemaidwebapp.domain.enumeration;


import lombok.Getter;

@Getter
public enum ETypeJob {
    JOB_SIZE ( "Size"),
    JOB_QUANTITY ( "Quantity");

    private final String name;

    ETypeJob(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public static ETypeJob findByName(String name) {
        for (ETypeJob eTypeJob : ETypeJob.values()) {
            if (eTypeJob.getName().equals(name)) {
                return eTypeJob;
            }
        }
        return null;
    }

}
