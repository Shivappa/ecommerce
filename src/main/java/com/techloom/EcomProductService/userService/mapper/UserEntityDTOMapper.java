package com.techloom.EcomProductService.userService.mapper;

import com.techloom.EcomProductService.userService.dto.UserDto;
import com.techloom.EcomProductService.userService.model.User;

public class UserEntityDTOMapper {
    public static UserDto getUserDTOFromUserEntity(User user){
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());
        return userDto;
    }
}
