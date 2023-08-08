package com.venly.venly.repositories;


import com.venly.venly.entities.Words;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepo extends CrudRepository<Words,Long> {

    Words findFirstByWord(String word);

}