package com.lamulaapp.repository

import com.lamulaapp.domain.Attachment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface AttachmentRepository : JpaRepository<Attachment, UUID> {
}
