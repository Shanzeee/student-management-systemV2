package com.brvsk.studentmanagementsystemV2.exception.notFound;

public class AnnouncementNotFoundException extends NotFoundException{

    public AnnouncementNotFoundException(final Long announcementId){
        super("Announcement with id "+announcementId+" not found");
    }
}

