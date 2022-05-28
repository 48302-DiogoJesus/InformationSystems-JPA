package DataScopes;

public class SessionThreadLocal {
    public static final ThreadLocal<Session> threadLocal = ThreadLocal.withInitial(() -> null);
}
