package com.example.krompirica.Line;

import com.example.krompirica.Utils.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LineService {
    private LineRepo repo;

    private LineEntity findOrThrow(Integer id){
        return repo.findById(id).orElseThrow(()->new NotFoundException("Line with the id "+id+" was not found."));
    }
    public List<LineEntity> getLines(){
        return repo.findAll();
    }
    public LineEntity postLine(LineEntity line){
        return repo.save(line);
    }
}
