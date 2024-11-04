package com.lamulaapp.controller

import com.lamulaapp.service.RoleService
import com.lamulaapp.controller.dto.RoleDto
import com.lamulaapp.controller.utils.validateCreateRole
import com.lamulaapp.controller.utils.validateUpdateRole
import com.lamulaapp.exception.ValidationErrorsException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class RoleController(
    private val roleService: RoleService
) {
    @PostMapping("/Roles")
    fun createRole(@RequestBody roleDto: RoleDto): ResponseEntity<RoleDto> {
        val validation = validateCreateRole(roleDto)

        if (!validation.isValid) {
            throw ValidationErrorsException(validation.errors)
        }

        return ResponseEntity(roleService.createRole(roleDto), HttpStatus.CREATED)
    }

    @GetMapping("/Roles")
    fun getRoles():ResponseEntity<List<RoleDto>> {
        return ResponseEntity(roleService.getRoles(), HttpStatus.OK)
    }

    @GetMapping("/Roles/{id}")
    fun getRoleById(@PathVariable("id") id: UUID): ResponseEntity<RoleDto> {
        return ResponseEntity(roleService.getRoleById(id), HttpStatus.OK)
    }

    @PutMapping("/Roles/{id}")
    fun updateRole(@PathVariable("id") id: UUID,@RequestBody roleDto: RoleDto): ResponseEntity<RoleDto> {
        val validation = validateUpdateRole(roleDto)

        if (!validation.isValid) {
            throw ValidationErrorsException(validation.errors)
        }

        return ResponseEntity(roleService.updateRole(id, roleDto), HttpStatus.OK)
    }

    @DeleteMapping("/Roles/{id}")
    fun deleteRole(@PathVariable("id") id: UUID): ResponseEntity<Unit> {
        return ResponseEntity(roleService.deleteRole(id), HttpStatus.NO_CONTENT)
    }
}