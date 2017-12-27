package kz.epam.atm.gmailtestPF.utils;

import kz.epam.atm.gmailtestPF.bo.Email;

public class SubjectBuilder {

    public static String buildSubjectString(Email email){
        return email.getSubject() + "(" + RandomNumberGenerator.getRandomInt() + ")" ;
    }
}
