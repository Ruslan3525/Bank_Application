package kz.kalybayevv.Bank.repositories;

import kz.kalybayevv.Bank.controllers.userController.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByIin(String iin);

    User findUserByIinAndPassword(String iin, String password);
}
