package com.election.service.uik;

import com.election.dto.UikDto;
import com.election.model.Uik;

import java.util.List;

public interface UikService {

    Uik create(UikDto uikDto);
    void edit(Uik src, UikDto target);
    Uik findById(Long id);
    Iterable<Uik> list(Long location);
    List<Uik> listByUser(Long userId);
}
