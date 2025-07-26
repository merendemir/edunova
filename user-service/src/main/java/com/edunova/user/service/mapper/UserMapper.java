package com.edunova.user.service.mapper;

import com.edunova.user.service.data.request.CreateUserRequest;
import com.edunova.user.service.data.request.UpdateUserRequest;
import com.edunova.user.service.data.response.UserResponse;
import com.edunova.user.service.data.vo.UserVO;
import com.edunova.user.service.entity.ApplicationUser;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    
    public abstract ApplicationUser toEntity(CreateUserRequest request);
    public abstract UserVO toVO(ApplicationUser entity);
    public abstract UserResponse toResponse(ApplicationUser entity);
    public abstract UserResponse toResponse(UserVO vo);
    
    public  abstract List<UserVO> toVOList(List<ApplicationUser> entities);
    public abstract List<UserResponse> toResponseList(List<ApplicationUser> entities);
    public abstract List<UserResponse> voListToResponseList(List<UserVO> vos);

    public abstract void updateEntity(@MappingTarget ApplicationUser entity, UpdateUserRequest request);
}