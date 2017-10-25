package core.gateways;

/**
 * Created by ryan on 10/16/17.
 */
public abstract class ResponseMessage<T> {
    protected boolean successful;
    protected T message;

    public boolean isSuccessful() {
        return successful;
    }

    public abstract T getMessage();
}
