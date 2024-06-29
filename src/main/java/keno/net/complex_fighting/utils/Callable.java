package keno.net.complex_fighting.utils;

public record Callable(String method_name, Class<?>... args) {
    public <T> T call() {
        T result = null;
        try {
            var caller = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass();
            var method = caller.getMethod(method_name,args);
            result = (T) ((args.length > 0) ? method.invoke(caller, (Object) args) : method.invoke(caller));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    public <T> T call(Object caller) {
        T result = null;
        try {
            var method = caller.getClass().getMethod(method_name,args);
            result = (T) ((args.length > 0) ? method.invoke(caller, (Object) args) : method.invoke(caller));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
