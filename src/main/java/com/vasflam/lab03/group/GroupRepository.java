package com.vasflam.lab03.group;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends ListCrudRepository<Group, Long> {
}
