package finalproject.EcommerceApp.service;


import finalproject.EcommerceApp.exception.UnauthorizedOperationException;
import finalproject.EcommerceApp.model.SystemUser;

public class ServiceUtils {
    public static void validateSystemUserForEntity(SystemUser expectedUser,
                                                   SystemUser actualUser,
                                                   Object entity) throws UnauthorizedOperationException {
        if (!expectedUser.equals(actualUser)) {
            throw new UnauthorizedOperationException(
                    String.format("Unauthorized Operation upon [%s] entity from user [%s]",
                            entity.getClass().getSimpleName(), actualUser.getId())
            );
        }
    }
}
