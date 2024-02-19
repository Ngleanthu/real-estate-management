package com.javaweb.service.impl;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IAssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentBuildingService implements IAssignmentBuildingService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;

    public void deleteExist(Long buildingId, List<Long> staffId){
        BuildingEntity building = buildingRepository.findById(buildingId).get();
        List<AssignmentBuildingEntity> assignmentBuildingEntities = building.getStaffs();
        for(AssignmentBuildingEntity item : assignmentBuildingEntities){
            assignmentBuildingRepository.delete(item);
        }
    }
    public void addAssignment(Long buildingId, List<Long> staffId){
        for(Long id : staffId) {
            UserEntity staff = userRepository.findById(id).get();
            BuildingEntity building = buildingRepository.findById(buildingId).get();
            AssignmentBuildingEntity assignmentBuilding = new AssignmentBuildingEntity();
            assignmentBuilding.setStaff(staff);
            assignmentBuilding.setBuilding(building);
            assignmentBuildingRepository.save(assignmentBuilding);
        }
    }


}
