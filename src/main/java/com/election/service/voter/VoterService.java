package com.election.service.voter;

import com.election.dto.VoterDto;
import com.election.model.Voter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VoterService {

    Voter create(VoterDto voterDto);
    void edit(Voter src, VoterDto target);
    Voter findById(Long id);
    Iterable<Voter> list();
    Page<Voter> listAllPageable(Long uikId, Pageable pageable);
    List<Voter> listByUser(Long userId);
    Page<Voter> listAllByRolePageable(Long roleId, Pageable pageable);
    List<Voter> listByFilter(Long locationId, Integer month);

}
