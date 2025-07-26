package com.edunova.user.service.mapper;

import com.edunova.user.service.data.request.CreateUserRequest;
import com.edunova.user.service.data.request.UpdateUserRequest;
import com.edunova.user.service.data.response.UserResponse;
import com.edunova.user.service.data.vo.UserVO;
import com.edunova.user.service.entity.ApplicationUser;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    ApplicationUser toEntity(CreateUserRequest request);
    UserVO toVO(ApplicationUser entity);
    UserResponse toResponse(ApplicationUser entity);
    UserResponse toResponse(UserVO vo);
    
    List<UserVO> toVOList(List<ApplicationUser> entities);
    List<UserResponse> toResponseList(List<ApplicationUser> entities);
    List<UserResponse> voListToResponseList(List<UserVO> vos);

    void updateEntity(@MappingTarget ApplicationUser entity, UpdateUserRequest request);
}