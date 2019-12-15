package com.election.repository;

import com.election.model.QUik;
import com.election.model.Uik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface UikRepository extends JpaRepository<Uik, Long>,
        QuerydslPredicateExecutor<Uik>, QuerydslBinderCustomizer<QUik> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QUik root) {
    }

    // Get All By User
    @Query(value = "SELECT u.* FROM ref_uik as u " +
            "LEFT JOIN ref_location as rl ON rl.id = u.location_id " +
            "LEFT JOIN m2m_user_location_permission as m2mulp ON m2mulp.location_id = rl.id " +
            "LEFT JOIN user as u ON u.id = m2mulp.user_id " +
            "where u.id = ?1", nativeQuery = true)
    List<Uik> findAllByUser(Long userId);
}

