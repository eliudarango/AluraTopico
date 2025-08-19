package eliud.topico.repository;

import com.forohub.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    Optional<Topic> findByTituloAndMensaje(String titulo, String mensaje);
}
