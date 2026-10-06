package util;

public class RetryUtil {

        public static <T> T execute(
                Retryable<T> action,
                int maxAttempts) throws Exception {

            int attempt = 0;

            while (attempt < maxAttempts) {

                try {
                    return action.run();

                } catch (Exception e) {

                    attempt++;

                    if (attempt >= maxAttempts) {
                        throw e;
                    }

                    System.out.println(
                            "Retry Attempt: "
                                    + attempt);
                }
            }

            return null;
        }
    }

