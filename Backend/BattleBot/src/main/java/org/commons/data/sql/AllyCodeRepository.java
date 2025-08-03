package org.commons.data.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllyCodeRepository extends JpaRepository<AllyCodeEntity, Long> {
}
