package com.election.assembler.datatable;

import com.election.controller.api.v1.VoterController;
import com.election.model.Voter;
import com.election.resource.datatable.VoterResource;
import com.election.utils.TimeHelper;
import org.joda.time.Years;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class VoterResourceAssembler extends DataTableResourceAssembler<Voter, VoterResource> {
    public VoterResourceAssembler() {
        super(VoterController.class, VoterResource.class);
    }

    @Override
    public VoterResource toResource(Voter voter) {

        VoterResource resource = createResourceWithId(voter.getId(), voter);

        resource
                .setFirstName(voter.getFirstName())
                .setLastName(voter.getLastName())
                .setMiddleName(voter.getMiddleName())
                .setGender(voter.getGender())
                .setPhone(voter.getPhone())
                .setPin(voter.getPin())
                .setUikId(voter.getUik().getId())
                .setUikTitle(voter.getUik().getTitle())
                .setBirthDate(TimeHelper.DATE_FORMATTER.format(voter.getBirthDate()))
                .setPosition(voter.getPosition());
        return resource;
    }
}
