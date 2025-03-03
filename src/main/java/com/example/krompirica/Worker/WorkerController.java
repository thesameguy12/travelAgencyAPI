package com.example.krompirica.Worker;

import com.example.krompirica.Worker.services.ApplicationUserDetailsService;
import com.example.krompirica.model.AuthenticationRequest;
import com.example.krompirica.model.AuthenticationResponce;
import com.example.krompirica.model.JwtUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/admin/user")
@AllArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
@PreAuthorize("isAuthenticated() and hasAuthority('1')")
public class WorkerController {
    private WorkerService service;
    private final ModelMapper mapper;


    private WorkerEntity convertToEntity(WorkerDto dto){return mapper.map(dto,WorkerEntity.class);}
    private WorkerDto convertToDto(WorkerEntity entity){return mapper.map(entity,WorkerDto.class);}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createWorker(@Valid @RequestBody WorkerDto dto) throws BadRequestException, NoSuchAlgorithmException {
        return service.createWorker(dto);
    }

    @GetMapping
    public List<WorkerDto> getAllWorkers(){
        var workers= StreamSupport.stream(service.getAllWorkers().spliterator(),false).collect(Collectors.toList());

        return  workers.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    @GetMapping("/{username}")
    public WorkerDto getWorkerByUsername(@PathVariable String username){
        return convertToDto(service.findByUsername(username));
    }
    @PutMapping("/{id}")
    public String putWorker(@PathVariable Integer id, @Valid @RequestBody WorkerDto dto) throws NoSuchAlgorithmException {
        return service.updateWorker(id,dto);
    }

    @DeleteMapping("/{id}")
    public String deleteWorker(@PathVariable Integer id){
        return service.deleteWorker(id);
    }



}
