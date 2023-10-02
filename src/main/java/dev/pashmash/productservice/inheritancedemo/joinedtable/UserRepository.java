package dev.pashmash.productservice.inheritancedemo.joinedtable;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("jt_ur")
public interface UserRepository
        extends JpaRepository<User, Long> {

    @Override
    <S extends User> @NotNull S save(@NotNull S entity);
}
