package com.mikhail.library.controller

import com.mikhail.library.entity.UserEntity
import com.mikhail.library.exceptions.UserAlreadyExists
import com.mikhail.library.service.UserService
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

private val LOG = KotlinLogging.logger {}

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @PostMapping
    fun registration(@RequestBody user: UserEntity): ResponseEntity<String> {
        return try {
            userService.registration(user)
            ResponseEntity.ok("User created")
        } catch (e: UserAlreadyExists) {
            ResponseEntity.badRequest().body("User already exists")
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
