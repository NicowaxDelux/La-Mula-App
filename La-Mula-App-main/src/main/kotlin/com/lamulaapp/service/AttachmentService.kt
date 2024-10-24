package com.lamulaapp.service

import com.lamulaapp.controller.dto.AttachmentDto
import com.lamulaapp.controller.mapper.toDTO
import com.lamulaapp.controller.mapper.toEntity
import com.lamulaapp.repository.AttachmentRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class AttachmentService(
    private val attachmentRepository: AttachmentRepository
) {

    fun createAttachment(attachmentDto: AttachmentDto): AttachmentDto {
        val response = attachmentRepository.save(attachmentDto.toEntity())
        return response.toDTO()
    }

    fun getAttachments(): List<AttachmentDto> {
        return attachmentRepository.findAll().map { it.toDTO() }
    }

    fun getAttachmentById(id: UUID): AttachmentDto? {
        val response = attachmentRepository.findById(id)
        return if (response.isPresent) {
            response.get().toDTO()
        } else {
            null
        }
    }

    fun updateAttachment(id: UUID, attachmentDto: AttachmentDto): AttachmentDto? {
        val response = attachmentRepository.findById(id)

        if (!response.isPresent) {
            return null
        }

        if (id != attachmentDto.id) {
            return null
        }

        return attachmentRepository.save(attachmentDto.toEntity()).toDTO()
    }

    fun deleteAttachment(id: UUID) {
        attachmentRepository.deleteById(id)
    }
}
