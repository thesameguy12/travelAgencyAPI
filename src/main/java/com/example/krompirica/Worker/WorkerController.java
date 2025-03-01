package com.example.krompirica.Worker;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class WorkerController {
    private WorkerService service;
    private final ModelMapper mapper;
    private WorkerEntity convertToEntity(WorkerDto dto){return mapper.map(dto,WorkerEntity.class);}
    private WorkerDto convertToDto(WorkerEntity entity){return mapper.map(entity,WorkerDto.class);}

    @PostMapping
    public String createWorker(@Valid @RequestBody WorkerDto dto) throws BadRequestException, NoSuchAlgorithmException {
        return service.createWorker(dto);
    }

}
