package com.javaweb.service.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IAssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssignmentBuildingService implements IAssignmentBuildingService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BuildingRepository buildingRepository;


    public void deleteExist(Long buildingId, List<Long> staffId){
        BuildingEntity building = buildingRepository.findById(buildingId).get();
        List<UserEntity> staffs = building.getStaffs();
        for(UserEntity item : staffs){
            userRepository.delete(item);
        }
    }

    public void addAssignment(Long buildingId, List<Long> staffId){
        List<UserEntity> userEntities = new ArrayList<>();
        for(Long item : staffId){
            userEntities.add(userRepository.findById(item).get());
        }
        BuildingEntity building = buildingRepository.findById(buildingId).get();
        building.setStaffs(userEntities);
        buildingRepository.save(building);
    }
}
