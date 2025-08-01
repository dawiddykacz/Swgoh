package org.commons.requests;

import lombok.NonNull;
import org.commons.NameId;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class CommonQueueService<T,U> {
    private final BlockingQueue<NameId> blockingQueue = new LinkedBlockingQueue<>();
    private final Map<NameId, U> requestMap = new ConcurrentHashMap<>();
    private final Map<NameId, T> resultMap = new ConcurrentHashMap<>();
    private final Set<NameId> processingSet = ConcurrentHashMap.newKeySet();
    private final UUIDService uuidService = UUIDService.getInstance();

    public CommonQueueService() {
        this.runThread();
    }

    public NameId add(@NonNull final U request){
        final NameId nameId = this.uuidService.generateUUID();

        this.blockingQueue.add(nameId);
        this.requestMap.put(nameId, request);

        return nameId;
    }

    public RequestStatus getStatus(@NonNull final NameId name) {
        if(this.blockingQueue.contains(name)) return RequestStatus.NOT_STARTED;
        if (processingSet.contains(name)) return RequestStatus.PROCESSING;
        if (resultMap.containsKey(name)) return RequestStatus.COMPLETED;
        return RequestStatus.NOT_FOUND;
    }

    public T getResult(@NonNull final NameId id) {
        T result = this.resultMap.get(id);
        this.resultMap.remove(id);
        return result;
    }

    protected void setResult(@NonNull final NameId id, @NonNull final T value) {
        resultMap.put(id, value);
        this.processingSet.remove(id);
    }

    protected abstract void process(@NonNull final NameId id,U request);

    private void runThread(){
        Thread thread = new Thread(this::runProcess);
        thread.setDaemon(true);
        thread.start();
    }

    private void runProcess() {
        while(true){
            try {
                final NameId nameId = this.blockingQueue.take();
                this.processingSet.add(nameId);

                U request = this.requestMap.get(nameId);
                process(nameId, request);
                this.processingSet.remove(nameId);
            } catch (InterruptedException ignore) {

            }
        }
    }
}
