package com.brvsk.studentmanagementsystemV2.email;

public interface EmailSender {
    void send(String to, String title, String body);
}
