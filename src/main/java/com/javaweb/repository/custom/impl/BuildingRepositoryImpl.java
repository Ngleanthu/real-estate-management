package com.javaweb.repository.custom.impl;

import com.javaweb.constant.SystemConstant;
import com.javaweb.dto.request.BuildingSearchRequestDTO;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public void querySimple(BuildingSearchRequestDTO buildingDTO, StringBuilder sql){
        try {
            Field[] fields = buildingDTO.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();

                if (!fieldName.startsWith("rentArea") && !fieldName.startsWith("rentPrice")
                        && !fieldName.equals("staffId") && !fieldName.equals("type")) {
                    Object value = field.get(buildingDTO);
                    if (value != null && !"".equals(value)) {
                        if (field.getType().isAssignableFrom(String.class)) {
                            sql.append(" AND building." + fieldName.toLowerCase() + " LIKE '%" + value + "%'");
                        } else if (Number.class.isAssignableFrom(field.getType())) {
                            sql.append(" AND building." + fieldName.toLowerCase() + " = " + value);
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void querySpecial(BuildingSearchRequestDTO buildingDTO, StringBuilder sql) {
        Long rentAreaFrom = buildingDTO.getRentAreaFrom();
        Long rentAreaTo = buildingDTO.getRentAreaTo();
        Long rentPriceFrom = buildingDTO.getRentPriceFrom();
        Long rentPriceTo = buildingDTO.getRentPriceTo();
        Long staffId = buildingDTO.getStaffId();
        String type = buildingDTO.getType();

        if (type != null && !type.equals("") ){
            String[] typeCode = type.split(",");
            for(String item : typeCode){
                sql.append( " AND building.type LIKE '%" + item + "%'");
            }
        }
        if (rentAreaFrom != null || rentAreaTo != null ) {
            if (rentAreaFrom != null) {
                sql.append(
                        " AND EXISTS (SELECT * FROM rentarea WHERE building.id = rentarea.buildingid AND rentarea.value >= "
                                + rentAreaFrom );
            }
            if (rentAreaTo != null) {
                sql.append(
                        " AND EXISTS (SELECT * FROM rentarea WHERE building.id = rentarea.buildingid AND rentarea.value <= "
                                + rentAreaTo + ")");
            }
            if(rentAreaFrom != null){
                sql.append(")");
            }
        }
        if (rentPriceFrom != null || rentPriceTo != null) {
            if (rentPriceFrom != null) {
                sql.append(" AND building.rentprice >= " + rentPriceFrom);
            }
            if (rentPriceTo != null) {
                sql.append(" AND building.rentprice <= " + rentPriceTo);
            }
        }
        if (staffId != null) {
            sql.append(" AND EXISTS (SELECT * FROM assignmentbuilding WHERE building.id = assignmentbuilding.buildingid"
                    + " AND EXISTS (SELECT * FROM user WHERE assignmentbuilding.staffid = user.id"
                    + " AND user.id =" + staffId  + "))");
        }
    }
    @Override
    public Page<BuildingEntity> findAll(BuildingSearchRequestDTO buildingDTO, Pageable pageable) {
        StringBuilder sqlQuery = new StringBuilder(query(buildingDTO,pageable));
        sqlQuery.append(" LIMIT ").append(pageable.getPageSize()).append("\n")
                .append(" OFFSET ").append(pageable.getOffset());
        Query query = entityManager.createNativeQuery(sqlQuery.toString(), BuildingEntity.class);
        List<BuildingEntity> resultList = query.getResultList();
        Integer totalCounts = countTotalItems(buildingDTO, pageable);
        return new PageImpl<>(resultList, pageable, totalCounts);
    }

    public String query(BuildingSearchRequestDTO buildingDTO, Pageable pageable){
        StringBuilder sqlQuery = new StringBuilder(
                "SELECT building.id, building.name, building.street, building.ward, building.district, building.structure, building.numberofbasement, building.floorarea, building.direction, building.level, building.rentprice, building.rentpricedescription, building.servicefee, building.carfee, building.motofee, building.overtimefee, building.electricityfee, building.deposit, building.payment, building.renttime, building.decorationtime, building.managername, building.managerphone, building.type, " +
                        "building.brokeragefee , building.avatar, building.linkofbuilding, building.map, building.note, building.waterfee, building.emptyarea FROM building");
        sqlQuery.append(SystemConstant.ONE_EQUALS_ONE);
        querySimple(buildingDTO, sqlQuery);
        querySpecial(buildingDTO, sqlQuery);
        sqlQuery.append(" GROUP BY building.id ");
        return sqlQuery.toString();
    }
    @Override
    @Transactional
    public void saveBuilding(BuildingEntity buildingEntity) {
        entityManager.persist(buildingEntity);
    }

    @Override
    public int countTotalItems(BuildingSearchRequestDTO buildingDTO, Pageable pageable) {
        String sql = query(buildingDTO, pageable);
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList().size();
    }


}
