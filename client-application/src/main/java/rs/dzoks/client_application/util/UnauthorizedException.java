package rs.dzoks.client_application.util;

public class UnauthorizedException extends Exception {
    private static final long serialVersionUID = -1300922631131223484L;
    public UnauthorizedException(String message) {
        super(message);
    }
}
