package ph.theorangeplatform.camtime.ui.common
/*

import android.content.Context
import android.graphics.Bitmap
import com.amplifyframework.AmplifyException
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.async.AmplifyOperation
import com.amplifyframework.core.async.Cancelable
import com.amplifyframework.storage.operation.StorageUploadFileOperation
import com.amplifyframework.storage.options.StorageUploadFileOptions
import com.amplifyframework.storage.s3.AWSS3StoragePlugin
import com.amplifyframework.storage.s3.configuration.AWSS3StoragePluginConfiguration
import kotlinx.coroutines.delay
import ph.theorangeplatform.camtime.data.handlers.LogHandler
import ph.theorangeplatform.camtime.data.models.timelogs.TimeLogType
import java.io.File
import java.io.FileOutputStream
import java.net.URI
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


object UploadHandler {

    var uploadOperation: Cancelable? = null

    fun initAwsAmplify(context: Context) {
        try {
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            Amplify.addPlugin(AWSS3StoragePlugin(
                AWSS3StoragePluginConfiguration {
                    awsS3PluginPrefixResolver = S3StoragePrefixResolver()
                }
            ))
            Amplify.configure(context)
            LogHandler.d("UploadHandler: Initialized Amplify")
        } catch (error: AmplifyException) {
            LogHandler.e("UploadHandler: Could not initialize Amplify $error")
        }
    }

    suspend fun uploadAwait(
        context: Context, type: Int, uid: String, bitmap: Bitmap
    ): String = suspendCoroutineWithTimeout(300000) { continuation ->

        val prefix = when(type){
            TimeLogType.TIME_IN -> "TimeIn"
            TimeLogType.TIME_OUT -> "TimeOut"
            TimeLogType.OT_TIME_IN -> "OtTimeIn"
            TimeLogType.OT_TIME_OUT -> "OtTimeOut"
            else -> "TimeIn"
        }
        val key =  "topcamtime/${prefix}/${uid}_${System.currentTimeMillis()}.jpg"

        val file = createImageFile(context, bitmap)
        val options = StorageUploadFileOptions.defaultInstance()
        uploadOperation = Amplify.Storage.uploadFile(key, file, options,
            { progress ->
                val percentage = (progress.currentBytes / progress.totalBytes * 100).toInt()
                LogHandler.d("UploadHandler: $percentage")
            }, { result ->
                LogHandler.d("UploadHandler: ${result.key}")
                continuation.resume(result.key)
            }, { exception ->
                LogHandler.e("UploadHandler: $exception")
                continuation.resumeWithException(Throwable("Failed to upload your image"))
            }
        )


    }

    suspend fun getUrlAwait(key: String): String = suspendCoroutineWithTimeout(300000) { continuation ->
        Amplify.Storage.getUrl(
            key,
            { urlResult ->
                val uri = URI(urlResult.url.toString())
                val url = URI(uri.scheme, uri.authority, uri.path, null, uri.fragment).toString()
                LogHandler.d("UploadHandler: $url")
                continuation.resume(url)
            },
            { error ->
                LogHandler.e("UploadHandler: $error")
                continuation.resumeWithException(Throwable("Failed to upload your image"))
            }
        )
    }


    private fun createImageFile(context: Context, bitmap: Bitmap): File {
        val timeStamp: String =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(Date())
        val storageDir: File =
            context.cacheDir
        val file = File.createTempFile(
            "camtime${timeStamp}_",
            ".jpg",
            storageDir
        )
        FileOutputStream(file).use { fileOutputStream ->
            bitmap.compress(
                Bitmap.CompressFormat.JPEG,
                40,
                fileOutputStream
            )
        }
        return file
    }

    suspend fun uploadLeaveAttachment(
        context: Context,
        keyAttachmentName: String,
        file: File?,
        cameraPhotoBitmap: Bitmap?,
    ): String = suspendCoroutineWithTimeout(300000) { continuation ->
        val options = when {
            keyAttachmentName.contains("jpg", ignoreCase = true) || keyAttachmentName.contains("jpeg", ignoreCase = true) -> {
                StorageUploadFileOptions.builder()
                    .contentType("image/jpeg")
                    .build()
            }
            keyAttachmentName.contains("png", ignoreCase = true) -> {
                StorageUploadFileOptions.builder()
                    .contentType("image/png")
                    .build()
            }
            keyAttachmentName.contains("pdf", ignoreCase = true) -> {
                StorageUploadFileOptions.builder()
                    .contentType("application/pdf")
                    .build()
            }
            else -> {
                StorageUploadFileOptions.defaultInstance()
            }
        }

        val fileUpload = file ?: if(cameraPhotoBitmap != null) createImageFile(context, cameraPhotoBitmap) else null
        if (fileUpload != null) {
            Amplify.Storage.uploadFile(keyAttachmentName, fileUpload, options,
                { progress ->
                    val percentage = (progress.currentBytes / progress.totalBytes * 100).toInt()
                    LogHandler.d("UploadHandler: $percentage")
                }, { result ->
                    LogHandler.d("UploadHandler: ${result.key}")
                    continuation.resume(result.key)
                }, { exception ->
                    LogHandler.e("UploadHandler: $exception")
                    continuation.resumeWithException(Throwable("Failed to upload your attachment"))
                }
            )
        } else {
            LogHandler.e("UploadHandler: no file data")
            continuation.resumeWithException(Throwable("Failed to upload your attachment"))
        }
    }

    fun cancelUpload(){
        uploadOperation?.cancel()
    }
}*/
