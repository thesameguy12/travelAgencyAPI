package com.example.krompirica.Worker;

import com.example.krompirica.Utils.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class WorkerService {
    private final WorkerRepo repo;
    private final ModelMapper mapper;


    private WorkerEntity convertToEntity(WorkerDto dto){return mapper.map(dto,WorkerEntity.class);}
    private WorkerDto convertToDto(WorkerEntity entity){return mapper.map(entity,WorkerDto.class);}

    private byte[] createSalt(){
        var random=new SecureRandom();
        var salt=new byte[128];
        random.nextBytes(salt);
        return salt;
    }
    private byte[] createPasswordHash(String password, byte[] salt) throws NoSuchAlgorithmException {
        var md= MessageDigest.getInstance("SHA-512");
        md.update(salt);
        return md.digest(password.getBytes(StandardCharsets.UTF_8));
    }
    public WorkerEntity findByUsername(String username){

        return repo.findByUsername(username);
    }
    public List<WorkerEntity> getAllWorkers(){
        return repo.findAll();
    }
    public String createWorker(WorkerDto dto) throws BadRequestException, NoSuchAlgorithmException {

        if(dto.getPassword().isEmpty())throw new IllegalArgumentException("Password is required");
        if(repo.selectExistsUsername(dto.getUsername()))throw new BadRequestException("Username "+dto.getUsername()+" is taken");
        var salt=createSalt();

        var passwordHash=createPasswordHash(dto.getPassword(),salt);
        var worker=convertToEntity(dto);
        System.out.println(dto);

        worker.setStoredHash(passwordHash);
        worker.setStoredSalt(salt);
        repo.save(worker);
        System.out.println(convertToDto(worker)+"AWBFAZFBIAFZ");
        return "Created user "+dto.getUsername();
    }
    private WorkerEntity findOrThrow(Integer id){
        return repo.findById(id).orElseThrow(()->new NotFoundException("Worker by id "+id+" was not found."));
    }

    public String updateWorker(Integer id,WorkerDto dto) throws NoSuchAlgorithmException {
        findOrThrow(id);
        var worker=convertToEntity(dto);
        if(!dto.getPassword().isBlank()){
            var salt=createSalt();
            var passwordHash=createPasswordHash(dto.getPassword(),salt);
            worker.setStoredHash(passwordHash);
            worker.setStoredSalt(salt);
        }
        repo.save(worker);
        return "Updated the worker with id "+id;
    }
    public String deleteWorker(Integer id){
        findOrThrow(id);
        repo.deleteById(id);
        return "Deleted the worker with id "+id;
    }
}
