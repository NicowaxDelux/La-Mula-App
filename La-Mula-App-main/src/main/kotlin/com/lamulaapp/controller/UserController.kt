package com.lamulaapp.controller

import com.lamulaapp.controller.dto.OrderDto
import com.lamulaapp.controller.dto.UserDto
import com.lamulaapp.controller.utils.validateCreateUser
import com.lamulaapp.controller.utils.validateUpdateUser
import com.lamulaapp.exception.ValidationErrorsException
import com.lamulaapp.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class UserController(
    private val userService: UserService
) {
    @PostMapping("/users")
    fun createUser(@RequestBody userDto: UserDto): ResponseEntity<UserDto> {
        val validateUser = validateCreateUser(userDto)

        if (!validateUser.isValid) {
            throw ValidationErrorsException(validateUser.errors)
        }

        return ResponseEntity(userService.createUser(userDto), HttpStatus.CREATED)
    }

    @GetMapping("/users")
    fun getUsers(): ResponseEntity<List<UserDto>> {
        return ResponseEntity(userService.getUsers(), HttpStatus.OK)
    }

    @GetMapping("/users/{id}")
    fun getUserById(@PathVariable("id") id: UUID): ResponseEntity<UserDto> {
        return ResponseEntity(userService.getUserById(id), HttpStatus.OK)
    }

    @GetMapping("/users/{id}/orders")
    fun findAllByUserId(
        @PathVariable("id") id: UUID,
        @RequestParam("limit") limit: Int = 10,
    ): ResponseEntity<List<OrderDto>> {
        return ResponseEntity(userService.findOrdersByUserId(id, limit), HttpStatus.OK)
    }

    @PutMapping("/users/{id}")
    fun  updateUser(@PathVariable("id") id: UUID, @RequestBody userDto: UserDto): ResponseEntity<UserDto> {
        val validation = validateUpdateUser(userDto)

        if (!validation.isValid) {
            throw ValidationErrorsException(validation.errors)
        }

        return ResponseEntity(userService.updateUser(id, userDto), HttpStatus.OK)
    }

    @DeleteMapping("/users/{id}")
    fun deleteUser(@PathVariable("id") id: UUID): ResponseEntity<Unit> {
        return ResponseEntity(userService.deleteUser(id), HttpStatus.NO_CONTENT)
    }
}