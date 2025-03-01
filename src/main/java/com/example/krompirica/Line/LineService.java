package com.example.krompirica.Line;

import com.example.krompirica.Utils.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Line;
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
    public LineEntity getLineById(Integer id){
        findOrThrow(id);
        return repo.getReferenceById(id);
    }
    public LineEntity postLine(LineEntity line){
        return repo.save(line);
    }

    public LineEntity updateLineById(Integer id, LineEntity entity){
        findOrThrow(id);
        repo.save(entity);
        return entity;
    }
    public String deleteLineById(Integer id){
        findOrThrow(id);
        repo.deleteById(id);
        return "Deleted line by id "+id;
    }
}
