package com.kjit.Diekraft.mapper;

import com.kjit.Diekraft.dto.LabourDTO;
import com.kjit.Diekraft.entity.Authorities;
import com.kjit.Diekraft.entity.Labour;
import com.kjit.Diekraft.entity.User;
import com.kjit.Diekraft.enums.AvailabilityStatus;
import com.kjit.Diekraft.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class LabourMapper {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private ProfessionRepository professionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LabourRepository labourRepository;

    public User toUserAndSaveLabour(LabourDTO labourDTO){
        User user = labourDTO.getId()==null ? new User() : userRepository.getOne(labourDTO.getUserId());
        user.setDateOfBirth(labourDTO.getBirthDate());
        user.setMobileNo(labourDTO.getMobileNo());
        user.setUserName(labourDTO.getUserName());
        user.setState(stateRepository.getOne(labourDTO.getState()));
        user.setAddress(labourDTO.getAddress());
        user.setCity(labourDTO.getCity());
        user.setZipcode(labourDTO.getZipcode());

        if (labourDTO.getAuthorities() != null) {
            Set<Authorities> authorities = labourDTO.getAuthorities().stream()
                    .map(authorityRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
            user.setAuthorities(authorities);
        }

        Labour labour = toLabour(labourDTO);
        user.setLabour(labour);
        labour.setUser(user);
        userRepository.save(user);
        return user;
    }


    public Labour toLabour(LabourDTO labourDTO){
        Labour labour = labourDTO.getId()==null ? new Labour() : labourRepository.getOne(labourDTO.getId());
        labour.setDept(deptRepository.getOne(labourDTO.getDepartment()));
        labour.setProfession(professionRepository.getOne(labourDTO.getProfession()));
        labour.setExperiance(labourDTO.getExperiance());
        labour.setAvailabilityStatus(AvailabilityStatus.valueOf(labourDTO.getAvailabilityStatus()).name());
        labour.setHindi(labourDTO.isHindi());
        labour.setEnglish(labourDTO.isEnglish());
        labour.setGujarati(labourDTO.isGujarati());

        return labour;
    }

    public LabourDTO toDTO(User user, Labour labour){
        LabourDTO labourDTO = new LabourDTO();
        labourDTO.setId(labour.getId());
        labourDTO.setAvailabilityStatus(labour.getAvailabilityStatus());
        labourDTO.setDepartment(labour.getDept().getName());
        labourDTO.setProfession(labour.getProfession().getName());
        labourDTO.setEnglish(labour.isEnglish());
        labourDTO.setHindi(labour.isHindi());
        labourDTO.setGujarati(labour.isGujarati());
        labourDTO.setAddress(user.getAddress());
        labourDTO.setCity(user.getCity());
        labourDTO.setAddress(user.getAddress());
        labourDTO.setZipcode(user.getZipcode());
        labourDTO.setMobileNo(user.getMobileNo());
        labourDTO.setBirthDate(user.getDateOfBirth());
        labourDTO.setUserId(user.getId());
        labourDTO.setExperiance(labour.getExperiance());
        labourDTO.setState(user.getState().getName());
        labourDTO.setUserName(user.getUserName());
        Set<String> authorities = new HashSet<>();
        authorities.add("LABOUR");
        labourDTO.setAuthorities(authorities);
        return labourDTO;
    }
}
