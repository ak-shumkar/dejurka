package com.election.service.voter.impl;

import com.election.dto.VoterDto;
import com.election.model.Location;
import com.election.model.Voter;
import com.election.repository.UikRepository;
import com.election.repository.VoterRepository;
import com.election.service.location.LocationService;
import com.election.service.voter.VoterService;
import com.election.utils.TimeHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class VoterServiceImpl implements VoterService {

    private final VoterRepository voterRepository;
    private final UikRepository uikRepository;
    private final LocationService locationService;

    public VoterServiceImpl(
            VoterRepository voterRepository,
            UikRepository uikRepository,
            LocationService locationService) {
        this.voterRepository = voterRepository;
        this.uikRepository = uikRepository;
        this.locationService = locationService;
    }

    @Override
    public Voter create(VoterDto voterDto) {

        Voter voter = new Voter()
                .setBirthDate(LocalDate.parse(voterDto.getBirthDate(), TimeHelper.DATE_FORMATTER))
                .setFirstName(voterDto.getFirstName())
                .setLastName(voterDto.getLastName())
                .setMiddleName(voterDto.getMiddleName())
                .setGender(voterDto.getGender())
                .setPosition(voterDto.getPosition())
                .setPhone(voterDto.getPhone())
                .setUik(uikRepository.getOne(voterDto.getUikId()));

        return voterRepository.save(voter);
    }

    @Override
    public void edit(Voter src, VoterDto target) {

        src.setFirstName(target.getFirstName());
        src.setMiddleName(target.getMiddleName());
        src.setLastName(target.getLastName());
        src.setGender(target.getGender());
        src.setBirthDate(LocalDate.parse(target.getBirthDate(), TimeHelper.DATE_FORMATTER));
        src.setPosition(target.getPosition());
        src.setPhone(target.getPhone());

        if(Objects.nonNull(uikRepository.getOne(target.getUikId())))
            src.setUik(uikRepository.getOne(target.getUikId()));

        voterRepository.save(src);
    }

    @Override
    public Voter findById(Long id) {
        return voterRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Voter> list(){
        return voterRepository.findAll();
    }

    @Override
    public Page<Voter> listAllPageable(Long uikId, Pageable pageable) {
        if(Objects.isNull(uikId))
            uikId = 0l;
        return voterRepository.findAllByUik(uikId, pageable);
    }

    @Override
    public List<Voter> listByUser(Long userId) {
        return voterRepository.findAllByUser(userId);
    }

    @Override
    public Page<Voter> listAllByRolePageable(Long roleId, Pageable pageable) {
        if(Objects.isNull(roleId))
            roleId = 0l;
        return voterRepository.findAllByRole(roleId, pageable);
    }

    @Override
    public List<Voter> listByFilter(Long locationId, Integer month) {
        List<Voter> voters = new ArrayList<>();
        Long looper = locationId;
        while (locationService.list(null,null, looper) != null) {
            voters.addAll(voterRepository.findAllByLocationMonth(looper, month));
            Iterable<Location> locations = locationService.list(null,null, looper);
            for(Location location: locations){
                voters.addAll(traverse(location.getId(), month));
            }
        }
        return voterRepository.findAllByLocationMonth(locationId, month);
    }

    public List<Voter> traverse(Long locationId, Integer month) {

        if(locationService.list(null,null, locationId) == null)
            return null;

        List<Voter> locationList = new ArrayList<>();
        locationList.addAll(voterRepository.findAllByLocationMonth(locationId, month));
        Iterable<Location> locations = locationService.list(null,null, locationId);
        locations.forEach(l->{
            locationList.addAll(traverse(l.getId(), month));
        });

        return locationList;


    }

}
