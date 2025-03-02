package com.example.krompirica.Role;

import com.example.krompirica.Utils.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor

public class RoleService {
    private final RoleRepo repo;

    private final ModelMapper mapper;
    private RoleEntity convertToEntity(RoleDto dto){return mapper.map(dto,RoleEntity.class);}
    private RoleDto convertToDto(RoleEntity entity){return mapper.map(entity,RoleDto.class);}

    private RoleEntity findOrThrow(Integer id){
        return repo.findById(id).orElseThrow(()->new NotFoundException("Role with the id "+id+" was not found."));
    }

    public List<RoleDto> getAllRoles(){
        var roles= StreamSupport.stream(repo.findAll().spliterator(),false).collect(Collectors.toList());
        return roles.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    public RoleDto getRoleById(Integer id){

        return convertToDto(findOrThrow(id));
    }
    public String postRole(RoleDto dto){
        repo.save(convertToEntity(dto));
        return "Added role "+dto.getRole();
    }
    public String putRole(Integer id,RoleDto dto){
        findOrThrow(id);
        repo.save(convertToEntity(dto));
        return "Changed role to "+dto.getRole();
    }

    public String deleteRole(Integer id){
        findOrThrow(id);
        repo.deleteById(id);
        return "Deleted role with id "+id;
    }
}
