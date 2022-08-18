package com.mikhail.library.controller

import com.mikhail.library.entity.UserEntity
import com.mikhail.library.entity.UserRepository
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

private val LOG = KotlinLogging.logger {}

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    private lateinit var userRepository: UserRepository

    @PostMapping
    fun registration(@RequestBody user: UserEntity): ResponseEntity<String> {
        return try {
            if (userRepository.findOneByUsername(user.username) != null) {
                ResponseEntity.badRequest().body("User already exists")
            } else {
                userRepository.save(user)
                ResponseEntity.ok("Ok")
            }
        } catch (t: Throwable) {
            LOG.error { t }
            ResponseEntity.badRequest().body("An error occurred")
        }
    }

    @GetMapping("/all")
    fun getUsers(): ResponseEntity<String> {
        return ResponseEntity.ok("Ok")
    }
}
