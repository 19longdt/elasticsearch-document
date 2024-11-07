package sds.easywrite.constants;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";
    public static final String USER = "ROLE_USER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";
    public static final String VIEW_CUSTOMER = "0301";
    public static final String VIEW_KYC_VERIFY = "0401";

    public interface Customer {
        public static final String CUSTOMER = "01";
        public static final String VIEW_ALL_CUSTOMER = "0101";
        public static final String ADD_CUSTOMER = "0102";
        public static final String UPDATE_CUSTOMER = "0103";
        public static final String DELETE_CUSTOMER = "0104";
    }

    public interface User {
        public static final String USER = "01";
        public static final String VIEW_ALL_USER = "0101";
        public static final String ADD_USER = "0102";
        public static final String UPDATE_USER = "0103";
        public static final String UPDATE_ACTIVE_USER = "0104";
        public static final String DELETE_USER = "0105";
        public static final String CHANGE_PASSWORD_USER = "0106";
    }

    public interface Role {
        public static final String ROLE = "02";
        public static final String VIEW_ALL_ROLE = "0201";
        public static final String ADD_ROLE = "0202";
        public static final String UPDATE_ROLE = "0203";
        public static final String DELETE_ROLE = "0204";
    }

    public interface Config {
        public static final String CONFIG = "05";
        public static final String VIEW_ALL_CONFIG = "0501";
        public static final String UPDATE_CONFIG = "0502";
    }

    public interface Report {
        public static final String REPORT = "06";
        public static final String VIEW_ALL_REPORT = "0601";
        public static final String EXPORT_EXCEL = "0602";
    }

    public interface System {
        public static final String SYSTEM = "07";
        public static final String VIEW_ALL_SYSTEM = "0701";
        public static final String UPDATE_SYSTEM = "0702";
        public static final String ADD_SYSTEM = "0703";
        public static final String DELETE_SYSTEM = "0704";
    }

    private AuthoritiesConstants() {}
}
