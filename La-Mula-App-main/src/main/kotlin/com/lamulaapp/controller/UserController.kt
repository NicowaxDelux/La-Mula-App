package com.lamulaapp.controller

import com.lamulaapp.controller.dto.OrderDto
import com.lamulaapp.controller.dto.UserDto
import com.lamulaapp.controller.utils.validateCreateUser
import com.lamulaapp.controller.utils.validateUpdateUser
import com.lamulaapp.exception.ValidationErrorsException
import com.lamulaapp.service.UserService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class UserController(
    private val userService: UserService
) {
    @PostMapping("/users")
    @Operation(
        summary = "Create a new user.",
        description = "Create a new user with their respective role."
    )
    fun createUser(@RequestBody userDto: UserDto): ResponseEntity<UserDto> {
        val validateUser = validateCreateUser(userDto)

        if (!validateUser.isValid) {
            throw ValidationErrorsException(validateUser.errors)
        }

        return ResponseEntity(userService.createUser(userDto), HttpStatus.CREATED)
    }

    @GetMapping("/users")
    @Operation(
        summary = "Get all users.",
        description = "Finds all users in the database and displays them in a list."
    )
    fun getUsers(): ResponseEntity<List<UserDto>> {
        return ResponseEntity(userService.getUsers(), HttpStatus.OK)
    }

    @GetMapping("/users/{id}")
    @Operation(
        summary = "Get user by id.",
        description = "Searches for the user in the database by Id and lists it."
    )
    fun getUserById(@PathVariable("id") id: UUID): ResponseEntity<UserDto> {
        return ResponseEntity(userService.getUserById(id), HttpStatus.OK)
    }

    @GetMapping("/users/{id}/orders")
    @Operation(
        summary = "Retrieve all orders for a user",
        description = "Gets a list of orders associated with a specific user, identified by their ID. Can be limited to a specific default order quantity of 10."
    )
    fun findAllByUserId(
        @PathVariable("id") id: UUID,
        @RequestParam("limit") limit: Int = 10,
    ): ResponseEntity<List<OrderDto>> {
        return ResponseEntity(userService.findOrdersByUserId(id, limit), HttpStatus.OK)
    }

    @PutMapping("/users/{id}")
    @Operation(
        summary = "Update a user.",
        description = "Search the database for the user by id and update it."
    )
    fun  updateUser(@PathVariable("id") id: UUID, @RequestBody userDto: UserDto): ResponseEntity<UserDto> {
        val validation = validateUpdateUser(userDto)

        if (!validation.isValid) {
            throw ValidationErrorsException(validation.errors)
        }

        return ResponseEntity(userService.updateUser(id, userDto), HttpStatus.OK)
    }

    @DeleteMapping("/users/{id}")
    @Operation(
        summary = "Delete a user.",
        description = "Search the database for the user by id and delete it."
    )
    fun deleteUser(@PathVariable("id") id: UUID): ResponseEntity<Unit> {
        return ResponseEntity(userService.deleteUser(id), HttpStatus.NO_CONTENT)
    }
}