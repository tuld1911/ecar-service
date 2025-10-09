package com.ecar.ecarservice.repositories;

import com.ecar.ecarservice.enitiies.AppUser;
import com.ecar.ecarservice.enums.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // cho rõ ràng
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findBySub(String sub);

    Optional<AppUser> findByEmail(String email);

    List<AppUser> findAllByActiveTrue();

    Optional<AppUser> findByIdAndActiveTrue(Long id);

    // roles là Set<AppRole> trong AppUser
    long countByRolesContaining(AppRole role);
}
