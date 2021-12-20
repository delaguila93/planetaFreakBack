package com.api.planetafreak.auth;

public class JwtConfig {
	
	public static final String LLAVA_SECRETA = "mi.clave.secreta.12345678";
	
	public static final String RSA_PRIVADA =
			  "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCeCnV9YIvp60QJ\r\n"
			+ "sls7aWMp8dIzD+fIVCHvyrWdEf8FY8DC1m2v2feVyvrEjDMkhHzHIl7o9/4Qmyw9\r\n"
			+ "0yCDpa1Z1VuXdW1QaEqMna+14vYJMpPc4bjKIgzVCnZ2paAXMuz7FglhwktI7z7c\r\n"
			+ "WhEztBOXUN+3fOR+XkFGahXYl9HKmikq2ZHeWLg/LsWwxAN34ABDeq952c8TtIkZ\r\n"
			+ "4X5FkMDJYnYcVNOsvg8JEVhSgsY4EdxFc49A4hC4LGMtKp51RfPkvafUAJwmLm85\r\n"
			+ "2VP74UNNzAq3tKhjkMk1Fbxsd/veqK+icCOMrmyJXmxqP0FAkB/q6kfBKlPZp/vj\r\n"
			+ "wrhXcE5TAgMBAAECggEALcAhWvZECqeMvrHfL0fG6TGUBCwqkRep61znTl0TUmAa\r\n"
			+ "QU0J28phnf9wsM1VUND5vjv49cA00GHbYNppozKnCEZ3iVT5HDnUuvg7EZBuYSM/\r\n"
			+ "IsZFVF6fEF1DBf3UZmLbr6QleyNf3/FCZU+9gxYyRa+n09pX2y02AEhdoqHwnZGm\r\n"
			+ "QvRcWNdIbme1MLj4GfzD6T2tkk29UcVoXdE+szAoiZCshAtjOhLnsZG9SccG3Wpt\r\n"
			+ "/4bmYcO3YdXAB2AoU4/t2Tx5OTxoslYCc9fHwh9ZaPIsB0aF9EYR/tSAOtU9fhAN\r\n"
			+ "SaXtYEYUrD8Dp9mX7hunjeRCOQ6/a/aylKBDLiJkoQKBgQDcHG2ww2a5S4zgvfL7\r\n"
			+ "R0eF5p1nO5/Ee2Ee4oeTU2xXsgC8K8oCIvFIX7XT5Dh/xrG/n4y+Am+Z266uAiDw\r\n"
			+ "OqoCH1qcWfq3uqEVci9WJcT3ZtlosaFpSsh2onA3GobGGPpI40xC+OAX7MWB1JVv\r\n"
			+ "Oh48aok4xvQhATneKsm15tqbZQKBgQC3zy6gwG94DBSz/O8/GILqp/mifQITPM15\r\n"
			+ "00LdH2NZJUpvcgchj/pqFtoaN0k9sWzpZmpvU1xfk9OOPMr3a/3/JBSWRoJwVqCp\r\n"
			+ "sNtUVNqrimM1yShjRps5MVnqXnHFuyKrs+yRGPfFHd6013DvOrKU1xB5LlZ4AP19\r\n"
			+ "zk7indkTVwKBgHNJWJKa5fTrg1A7bUb7oJSbo5nsetQYo1ff4o7hxP9xtjzY2qef\r\n"
			+ "0azMpJnlEUqrsXHiiX8EG5wEmkt9xe7NazpNXdJCtrUAxVpMQkrm4XGLpxfU4ewa\r\n"
			+ "RUEN96SE+k5LytveYZU6cXVWiGRmChcF48xVKKZaXofi8oJQ+vGRhrKZAoGAP4Rp\r\n"
			+ "0YUOQAa0tMnoBv66ZsprGnpmWC1/t+SIS49ILosTYNWfnzp4x2ZkMgD+30HLqBAL\r\n"
			+ "IbNFzYVw6ssZPEzmNd/K+W8eLiHRXTJPaKH1+qdUx0ZfHYdNpoLmVnYSjMixH3KK\r\n"
			+ "gk2FeWlsB0QXs6OuCH8VIzsfCFMXRf0gB3GSjPsCgYEAqLUYJUdaZTl4ErazZk2W\r\n"
			+ "PWzR2c4i5YhQZmrnXjWps/kungTStYLfp4cDesXbzzUUmOMXotUS7cMNh4gjv0/W\r\n"
			+ "6Kz+7bnloXn7Zfv1EcOC8BQn49y0jrH0zQWn5OTtP+NMnzsKJFWvNP79M+Zlw9Oz\r\n"
			+ "H2mHdg0DBZI3RUMuK2k1XW0=="
			;
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAngp1fWCL6etECbJbO2lj\r\n"
			+ "KfHSMw/nyFQh78q1nRH/BWPAwtZtr9n3lcr6xIwzJIR8xyJe6Pf+EJssPdMgg6Wt\r\n"
			+ "WdVbl3VtUGhKjJ2vteL2CTKT3OG4yiIM1Qp2dqWgFzLs+xYJYcJLSO8+3FoRM7QT\r\n"
			+ "l1Dft3zkfl5BRmoV2JfRypopKtmR3li4Py7FsMQDd+AAQ3qvednPE7SJGeF+RZDA\r\n"
			+ "yWJ2HFTTrL4PCRFYUoLGOBHcRXOPQOIQuCxjLSqedUXz5L2n1ACcJi5vOdlT++FD\r\n"
			+ "TcwKt7SoY5DJNRW8bHf73qivonAjjK5siV5saj9BQJAf6upHwSpT2af748K4V3BO\r\n"
			+ "UwIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";

}
