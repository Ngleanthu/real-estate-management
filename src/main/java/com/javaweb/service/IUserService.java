package com.javaweb.service;

import com.javaweb.dto.PasswordDTO;
import com.javaweb.dto.UserDTO;
import com.javaweb.dto.response.StaffResponseDTO;
import com.javaweb.exception.MyException;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IUserService {
    UserDTO findOneByUserNameAndStatus(String name, int status);
    List<UserDTO> getUsers(String searchValue, Pageable pageable);
    int getTotalItems(String searchValue);
    UserDTO findOneByUserName(String userName);
    UserDTO findUserById(long id);
    UserDTO insert(UserDTO userDTO);
    UserDTO update(Long id, UserDTO userDTO);
    void updatePassword(long id, PasswordDTO userDTO) throws MyException;
    UserDTO resetPassword(long id);
    UserDTO updateProfileOfUser(String id, UserDTO userDTO);
    void delete(long[] ids);

    Map<Long, String> getListStaff();

    StaffResponseDTO listStaff(Long buildingId);
}
