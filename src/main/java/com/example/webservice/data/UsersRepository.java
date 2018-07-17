package com.example.webservice.data;

//import com.sun.xml.internal.fastinfoset.util.CharArray;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, String> {
}
