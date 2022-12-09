package org.chatapp.domain.utils;

public class UserIdGenerator {
    private static long idCounter = 0;

    private UserIdGenerator(){}

    //TODO: what if application goes down? This will reset, handle
    public static synchronized long createUniqueId(){
        return idCounter++;
    }
}
