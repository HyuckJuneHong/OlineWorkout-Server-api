package project.olineworkout.repository.user;


import org.springframework.data.jpa.repository.JpaRepository;
import project.olineworkout.domain.entity.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
