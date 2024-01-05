package com.example.demo.repositories;

//import java.sql.Date;
import com.example.demo.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessagerRepository extends JpaRepository<Message, Long>  {

    

}

