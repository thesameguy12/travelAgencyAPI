package com.example.krompirica.Line;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v1/line")
@AllArgsConstructor
public class LineController {
    private LineService service;
    private ModelMapper mapper;
    private LineEntity convertToEntity(LineDto dto){return mapper.map(dto,LineEntity.class);}
    private LineDto convertToDto(LineEntity entity){return mapper.map(entity,LineDto.class);}

    @GetMapping
    public List<LineDto> getLines(){
        var lines= StreamSupport.stream(service.getLines().spliterator(),false).collect(Collectors.toList());

        return  lines.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @PostMapping
    public LineDto postLine (@Valid @RequestBody LineDto dto){
        System.out.println(dto);
        return convertToDto(service.postLine(convertToEntity(dto)));
    }
}
