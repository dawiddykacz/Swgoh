package org.commons.requests;

import lombok.NonNull;
import org.commons.NameId;

public abstract class AppCommonQueueService<T,U> {
    private final CommonQueueService<T,U> queueService;

    public AppCommonQueueService(@NonNull final CommonQueueService<T,U> queueService) {
        this.queueService = queueService;
    }

    public String add(U request){
        return this.queueService.add(request).toString();
    }

    public RequestStatus getStatus(@NonNull final String name) {
        return this.queueService.getStatus(new NameId(name));
    }

    public T getResult(@NonNull final String id) {
        return this.queueService.getResult(new NameId(id));
    }
}
