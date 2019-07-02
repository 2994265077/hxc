package com.cetccity.operationcenter.webframework.web.model.build;

public class EnterpriseModel<T> {

    private long legalPerson;

    private long registerLegalPerson;

    private long otherPerson;

    private  T alarmList;

    public long getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(long legalPerson) {
        this.legalPerson = legalPerson;
    }

    public long getRegisterLegalPerson() {
        return registerLegalPerson;
    }

    public void setRegisterLegalPerson(long registerLegalPerson) {
        this.registerLegalPerson = registerLegalPerson;
    }

    public long getOtherPerson() {
        return otherPerson;
    }

    public void setOtherPerson(long otherPerson) {
        this.otherPerson = otherPerson;
    }

    public T getAlarmList() {
        return alarmList;
    }

    public void setAlarmList(T alarmList) {
        this.alarmList = alarmList;
    }
}
