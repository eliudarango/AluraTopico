package eliud.topico.controller;

import com.forohub.dto.TopicCreateDTO;
import com.forohub.dto.TopicResponseDTO;
import com.forohub.dto.TopicUpdateDTO;
import com.forohub.entity.Topic;
import com.forohub.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    public ResponseEntity<TopicResponseDTO> createTopic(@RequestBody @Valid TopicCreateDTO dto) {
        Topic topic = topicService.createTopic(dto);
        return ResponseEntity.ok(toDTO(topic));
    }

    @GetMapping
    public ResponseEntity<List<TopicResponseDTO>> getAllTopics() {
        List<TopicResponseDTO> list = topicService.getAllTopics()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponseDTO> getTopicById(@PathVariable Long id) {
        Topic topic = topicService.getTopicById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));
        return ResponseEntity.ok(toDTO(topic));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicResponseDTO> updateTopic(@PathVariable Long id,
                                                        @RequestBody @Valid TopicUpdateDTO dto) {
        Topic updated = topicService.updateTopic(id, dto);
        return ResponseEntity.ok(toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }

    private TopicResponseDTO toDTO(Topic topic) {
        return TopicResponseDTO.builder()
                .id(topic.getId())
                .titulo(topic.getTitulo())
                .mensaje(topic.getMensaje())
                .fechaCreacion(topic.getFechaCreacion())
                .status(topic.getStatus())
                .autor(topic.getAutor())
                .curso(topic.getCurso())
                .build();
    }
}
