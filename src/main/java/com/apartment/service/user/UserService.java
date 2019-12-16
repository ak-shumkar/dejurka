package com.apartment.service.user;

import com.apartment.dto.UserDto;
import com.apartment.model.Uik;
import com.apartment.model.User;
import com.apartment.model.Voter;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    boolean existByUsername(String username);

    boolean existByEmail(String username);

    void delete(Long id);

    void edit(User src, UserDto target);

    List<Uik> userUikList(Long userId);

    List<Voter> userVoterList(Long userId);

    List<User> getAllByRole(Long roleId);

}
