package com.mikhail.library.service

import com.mikhail.library.entity.UserEntity
import com.mikhail.library.entity.UserRepository
import com.mikhail.library.exceptions.UserAlreadyExists
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    fun registration(user: UserEntity): UserEntity{
        return if (userRepository.findOneByUsername(user.username) != null) {
            throw UserAlreadyExists()
        } else {
            userRepository.save(user)
        }
    }

}