package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private static final int MIN_AGE = 18;
    private static final int MIN_PASSWORD_LENGTH = 6;
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (user.getAge() == null || user.getAge() < MIN_AGE) {
            throw new RuntimeException("User should be at least " + MIN_AGE
                    + " years old, but was: "
                    + user.getAge());
        }
        if (user.getPassword() == null || user.getPassword().length() < MIN_PASSWORD_LENGTH) {
            throw new RuntimeException("Password minimal length must be " + MIN_PASSWORD_LENGTH
                    + " or more, but was: "
                    + user.getPassword().length());
        }
        if (user.getLogin() == null) {
            throw new RuntimeException("User login should not be null");
        }
        if (storageDao.get(user.getLogin()) != null) {
            throw new RuntimeException("User with that login already exist");
        }
        storageDao.add(user);
        return user;
    }
}
