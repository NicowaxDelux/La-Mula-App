package com.lamulaapp.controller

import com.lamulaapp.service.RoleService
import com.lamulaapp.controller.dto.RoleDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class RoleController(
    private val roleService: RoleService
) {
    @PostMapping("/roles")
    fun createRole(@RequestBody roleDto: RoleDto): ResponseEntity<RoleDto> {
        return ResponseEntity(roleService.createRole(roleDto), HttpStatus.CREATED)
    }

    @GetMapping("/roles")
    fun getRoles():ResponseEntity<List<RoleDto>> {
        return ResponseEntity(roleService.getRoles(), HttpStatus.OK)
    }

    @GetMapping("/roles/{id}")
    fun getRoleById(@PathVariable("id") id: UUID): ResponseEntity<RoleDto> {
        return ResponseEntity(roleService.getRoleById(id), HttpStatus.OK)
    }

    @PutMapping("/roles/{id}")
    fun updateRole(@PathVariable("id") id: UUID,@RequestBody roleDto: RoleDto): ResponseEntity<RoleDto> {
        return ResponseEntity(roleService.updateRole(id, roleDto), HttpStatus.OK)
    }

    @DeleteMapping("/roles/{id}")
    fun deleteRole(@PathVariable("id") id: UUID): ResponseEntity<Unit> {
        return ResponseEntity(roleService.deleteRole(id), HttpStatus.NO_CONTENT)
    }
}