package com.election.service.user;

import com.election.dto.UserDto;
import com.election.model.Uik;
import com.election.model.User;
import com.election.model.Voter;
import org.springframework.security.core.Authentication;

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
