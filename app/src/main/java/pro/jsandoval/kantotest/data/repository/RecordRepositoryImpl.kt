package pro.jsandoval.kantotest.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pro.jsandoval.kantotest.data.DataState
import pro.jsandoval.kantotest.domain.model.Record
import pro.jsandoval.kantotest.domain.model.Record.Companion.record
import pro.jsandoval.kantotest.domain.model.User.Companion.user
import pro.jsandoval.kantotest.domain.repository.RecordRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecordRepositoryImpl @Inject constructor(

) : RecordRepository {

    override fun getRecords(): Flow<DataState<List<Record>>> = flow {
        emit(DataState.data(data = mock_recordList()))
    }

    private fun mock_recordList(): List<Record> {
        return listOf(
            record {
                user = user {
                    name = "TestAndroide"
                    username = "@testotesto"
                    img = "https://ks-profiles-dev.s3.amazonaws.com/media/user_photos/2949/tempImage1578523169147.png"
                }
                song = "Secreto"
                video = "https://s3.amazonaws.com/ks-records-test/media/records/2990/3654c78e9e_video_mixed.mp4"
                preview = "https://s3.amazonaws.com/ks-records-test/media/records/3346/fff3c44ef3_img.jpg"
                likes = 25
            },
            record {
                user = user {
                    name = "Usuario Prueba"
                    username = "@userTest"
                    img = "https://ks-profiles-dev.s3.amazonaws.com/media/user_photos/12/tempImage1580240237560.png"
                }
                song = "Relación"
                video = "https://s3.amazonaws.com/ks-records-test/media/records/2993/14415049a8_video_mixed.mp4"
                preview = "https://s3.amazonaws.com/ks-records-test/media/records/2753/7e75737624_img.jpg"
                likes = 3
            },
            record {
                user = user {
                    name = "videokanto"
                    username = "@kantovideo"
                    img = "https://ks-profiles-dev.s3.amazonaws.com/photos/user/7052/image.png?AWSAccessKeyId=AKIAJCQQV35SSGNPJEKA&Signature=tECXdKygdW7cpuVzlKu8%2FIRBjPk%3D&Expires=2102109863"
                }
                song = "Ella me levantó"
                video = "https://s3.amazonaws.com/ks-records-test/media/records/2987/3cb9609a86_video_mixed.mp4"
                preview = "https://s3.amazonaws.com/ks-records-test/media/records/3346/fff3c44ef3_img.jpg"
                likes = 8
            }
        )
    }

}