package com.apartment.repository;

import com.apartment.model.QVoter;
import com.apartment.model.Voter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface VoterRepository extends JpaRepository<Voter, Long>, QuerydslPredicateExecutor<Voter>, QuerydslBinderCustomizer<QVoter> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QVoter root) {
    }

    // Get All By User
    @Query(value = "SELECT v.* FROM voter as v " +
            "LEFT JOIN ref_uik as ru ON ru.id = v.uik_id " +
            "LEFT JOIN ref_location as rl ON rl.id = ru.location_id " +
            "LEFT JOIN m2m_user_location_permission as m2mulp ON m2mulp.location_id = rl.id " +
            "LEFT JOIN users as u ON u.id = m2mulp.user_id " +
            "where u.id = ?1", nativeQuery = true)
    List<Voter> findAllByUser(Long userId);

    // Get All By Uik
    @Query(value = "SELECT v.* FROM voter as v " +
            "LEFT JOIN ref_uik as ru ON ru.id = v.uik_id " +
            "LEFT JOIN ref_location as rl ON rl.id = ru.location_id " +
            "LEFT JOIN m2m_user_location_permission as m2mulp ON m2mulp.location_id = rl.id " +
            "LEFT JOIN users as u ON u.id = m2mulp.user_id " +
            "where (:uik = 0 or ru.id = :uik)", nativeQuery = true)
    Page<Voter> findAllByUik(@Param("uik") Long uikId, Pageable pageable);

    // Get All By Role
    @Query(value = "SELECT v.* FROM voter as v " +
            "LEFT JOIN ref_uik as ru ON ru.id = v.uik_id " +
            "LEFT JOIN ref_location as rl ON rl.id = ru.location_id " +
            "LEFT JOIN m2m_user_location_permission as m2mulp ON m2mulp.location_id = rl.id " +
            "LEFT JOIN users as u ON u.id = m2mulp.user_id " +
            "LEFT JOIN user_roles ur on u.id = ur.user_id " +
            "LEFT JOIN roles r on ur.role_id = r.id " +
            "where (r.id = ?1 or r.id = 0)", nativeQuery = true)
    Page<Voter> findAllByRole(Long roleId, Pageable pageable);

    // Get All By Location and Month
    @Query(value = "SELECT v.* FROM voter as v " +
            "LEFT JOIN ref_uik as ru ON ru.id = v.uik_id " +
            "LEFT JOIN ref_location as rl ON rl.id = ru.location_id " +
            "LEFT JOIN m2m_user_location_permission as m2mulp ON m2mulp.location_id = rl.id " +
            "LEFT JOIN users as u ON u.id = m2mulp.user_id " +
            "where rl.id = ?1 and EXTRACT(MONTH FROM v.created_at) = ?2", nativeQuery = true)
    List<Voter> findAllByLocationMonth(Long locationId, Integer month);

}
