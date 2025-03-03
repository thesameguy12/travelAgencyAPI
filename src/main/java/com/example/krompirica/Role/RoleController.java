package com.example.krompirica.Role;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/role")
@AllArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
@PreAuthorize("isAuthenticated() and hasAuthority('1')")
public class RoleController {
    private final RoleService service;

    @GetMapping
    public List<RoleDto> getAllRoles(){
        return service.getAllRoles();
    }

    @GetMapping("/{id}")
    public RoleDto getRoleById(@PathVariable Integer id){
        return service.getRoleById(id);
    }

    @PostMapping
    public String postRole(@Valid @RequestBody RoleDto dto){
        return service.postRole(dto);
    }

    @PutMapping("/{id}")
    public String putRole(@PathVariable Integer id,@Valid @RequestBody RoleDto dto){
        return service.putRole(id,dto);
    }

    @DeleteMapping("/{id}")
    public String deleteRole(@PathVariable Integer id){
        return service.deleteRole(id);
    }

}
