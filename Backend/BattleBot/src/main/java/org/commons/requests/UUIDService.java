package org.commons.requests;

import org.commons.NameId;
import java.util.UUID;

class UUIDService {
    private static UUIDService uuidService;

    public static UUIDService getInstance() {
        synchronized (UUIDService.class) {
            if (uuidService == null) uuidService = new UUIDService();
            synchronized (UUIDService.class){
                return uuidService;
            }
        }
    }


    private UUIDService(){

    }

    public NameId generateUUID(){
        return new NameId("1");
        /*return new NameId(UUID.randomUUID().toString()+System.currentTimeMillis()+
                UUID.randomUUID().toString()+System.currentTimeMillis()+
                UUID.randomUUID().toString()+System.currentTimeMillis()+
                UUID.randomUUID().toString()+System.currentTimeMillis()+
                UUID.randomUUID().toString()+System.currentTimeMillis());*/
    }
}
