package com.kjit.Diekraft.service;

import com.kjit.Diekraft.dto.LabourDTO;
import com.kjit.Diekraft.entity.Labour;
import com.kjit.Diekraft.entity.User;
import com.kjit.Diekraft.mapper.LabourMapper;
import com.kjit.Diekraft.repository.LabourRepository;
import com.kjit.Diekraft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LabourService {

    @Autowired
    private LabourRepository labourRepository;

    @Autowired
    private LabourMapper labourMapper;

    public LabourService(LabourRepository labourRepository, LabourMapper labourMapper) {
        this.labourRepository = labourRepository;
        this.labourMapper = labourMapper;
    }

    public void createOrUpdateLabour(LabourDTO labourDTO){
        labourMapper.toUserAndSaveLabour(labourDTO);
    }

    public LabourDTO getLabourById(Long id){
        Labour labour = labourRepository.getOne(id);
        return labourMapper.toDTO(labour.getUser(), labour);
    }

    public List<LabourDTO> getAllLabour(){
        return labourRepository.findAll()
                .stream()
                .map(labour -> {
                    return labourMapper.toDTO(labour.getUser(), labour);
                }).collect(Collectors.toList());
    }


}
