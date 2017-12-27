package kz.epam.atm.gmailtestPF.utils;

import kz.epam.atm.gmailtestPF.pages.GmailPage;

public class EmailCounter {

    public static int countNumberOfEmailsInFolder(GmailPage page){
        return page.getAllEmailsInFolder().size();
    }
}
