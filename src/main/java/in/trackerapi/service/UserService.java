package in.trackerapi.service;

import in.trackerapi.entity.User;
import in.trackerapi.entity.UserModel;

public interface UserService {

    User createUser(UserModel user);
    User readUser();

    User updateUser(UserModel user);
    void deleteUser();
    User getLoggedInUser();


}
