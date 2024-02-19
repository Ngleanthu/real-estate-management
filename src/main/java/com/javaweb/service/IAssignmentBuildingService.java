package com.javaweb.service;

import java.util.List;

public interface IAssignmentBuildingService {
    void deleteExist(Long buildingId, List<Long> staffId);
    void addAssignment(Long buildingId, List<Long> staffId);
}
