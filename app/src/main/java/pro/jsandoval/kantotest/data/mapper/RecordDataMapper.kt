package pro.jsandoval.kantotest.data.mapper

import pro.jsandoval.kantotest.data.local.room.entity.RecordEntity
import pro.jsandoval.kantotest.data.local.room.entity.RecordEntity.Companion.recordEntity
import pro.jsandoval.kantotest.data.remote.RecordResponse
import pro.jsandoval.kantotest.domain.model.Record
import pro.jsandoval.kantotest.domain.model.Record.Companion.record

fun RecordResponse.toEntity(): RecordEntity = recordEntity {
    val response = this@toEntity
    user = response.user?.toEntity()
    song = response.songName ?: ""
    video = response.recordVideo ?: ""
    preview = response.previewImg ?: ""
    likes = response.likes ?: 0
}

fun RecordEntity.toModel(): Record = record {
    val entity = this@toModel
    id = entity.id
    user = entity.user?.toModel()
    song = entity.song
    video = entity.video
    preview = entity.preview
    likes = entity.likes
    likedByMe = entity.likedByMe
}