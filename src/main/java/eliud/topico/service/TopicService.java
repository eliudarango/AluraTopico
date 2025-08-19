package eliud.topico.service;


import com.forohub.dto.TopicCreateDTO;
import com.forohub.dto.TopicUpdateDTO;
import com.forohub.entity.Topic;
import com.forohub.repository.TopicRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Transactional
    public Topic createTopic(TopicCreateDTO dto) {
        // Validar duplicados
        topicRepository.findByTituloAndMensaje(dto.getTitulo(), dto.getMensaje())
                .ifPresent(t -> {
                    throw new RuntimeException("El tópico ya existe");
                });

        Topic topic = Topic.builder()
                .titulo(dto.getTitulo())
                .mensaje(dto.getMensaje())
                .autor(dto.getAutor())
                .curso(dto.getCurso())
                .build();

        return topicRepository.save(topic);
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public Optional<Topic> getTopicById(Long id) {
        return topicRepository.findById(id);
    }

    @Transactional
    public Topic updateTopic(Long id, TopicUpdateDTO dto) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));

        topic.setTitulo(dto.getTitulo());
        topic.setMensaje(dto.getMensaje());
        topic.setAutor(dto.getAutor());
        topic.setCurso(dto.getCurso());

        return topicRepository.save(topic);
    }

    @Transactional
    public void deleteTopic(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));
        topicRepository.deleteById(id);
    }
}

