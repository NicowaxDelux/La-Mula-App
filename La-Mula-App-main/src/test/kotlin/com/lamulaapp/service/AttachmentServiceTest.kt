package com.lamulaapp.service

import com.lamulaapp.controller.mapper.toEntity
import com.lamulaapp.datamock.getAttachmentDto
import com.lamulaapp.datamock.getAttachmentId
import com.lamulaapp.repository.AttachmentRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals

class AttachmentServiceTest {

    private val attachmentRepositoryMock = mockk<AttachmentRepository>()
    private val attachmentService = AttachmentService(attachmentRepositoryMock)

    companion object {
        val idAttachment: UUID = getAttachmentId()
        val attachmentDto = getAttachmentDto()
    }

    @Test
    fun `should create an attachment`() {
        // Given
        every { attachmentRepositoryMock.findById(any()) } returns Optional.empty()
        every { attachmentRepositoryMock.save(any()) } returns attachmentDto.toEntity()

        // When
        val result = attachmentService.createAttachment(attachmentDto)

        // Then
        assertEquals(attachmentDto, result)
        verify(exactly = 1) { attachmentRepositoryMock.findById(any()) }
        verify(exactly = 1) { attachmentRepositoryMock.save(any()) }
    }
}