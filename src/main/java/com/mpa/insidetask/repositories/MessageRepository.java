package com.mpa.insidetask.repositories;

import com.mpa.insidetask.domain.models.Message;
import com.mpa.insidetask.domain.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

    List<Message> findAllByUser(User user);
    Page<Message> findAllByUser(User user, Pageable pageable);
}
