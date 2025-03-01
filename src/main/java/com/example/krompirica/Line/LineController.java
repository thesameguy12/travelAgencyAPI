package com.example.krompirica.Line;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v1/line")
@AllArgsConstructor
//@EnableMethodSecurity(prePostEnabled = true)
//@PreAuthorize("isAuthorised()")
public class LineController {
    private LineService service;
    private ModelMapper mapper;
    private LineEntity convertToEntity(LineResponseDto dto){return mapper.map(dto,LineEntity.class);}
    private LineEntity convertToEntity(LineRequestDto dto){return mapper.map(dto,LineEntity.class);}
    private LineResponseDto convertToResponseDto(LineEntity entity){return mapper.map(entity,LineResponseDto.class);}
    private LineRequestDto convertToRequestDto(LineEntity entity){return mapper.map(entity,LineRequestDto.class);}

    @GetMapping
    public List<LineResponseDto> getLines(){
        var lines= StreamSupport.stream(service.getLines().spliterator(),false).collect(Collectors.toList());

        return  lines.stream().map(this::convertToResponseDto).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public LineResponseDto getLineById(@PathVariable Integer id){
        return convertToResponseDto(service.getLineById(id));
    }

    @PostMapping
    public LineResponseDto postLine (@Valid @RequestBody LineRequestDto dto){

        return convertToResponseDto(service.postLine(convertToEntity(dto)));
    }

    @PutMapping("/{id}")
    public LineResponseDto putLine(@PathVariable Integer id,@Valid @RequestBody LineRequestDto dto){
        return convertToResponseDto(service.updateLineById(id,convertToEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public String deleteLine(@PathVariable Integer id){
        return service.deleteLineById(id);
    }
}
