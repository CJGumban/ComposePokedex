package ph.theorangeplatform.camtime.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import com.example.composepokedex.data.handlers.LogHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeout
import java.net.UnknownHostException
import kotlin.coroutines.Continuation
import kotlin.coroutines.cancellation.CancellationException

suspend fun <T> Flow<T>.singleOrError(
    success: ((T) -> Unit),
    failed: ((error: Throwable) -> Unit)? = null
) {
    try {
        success.invoke(single())
    } catch (t: CancellationException) {
        LogHandler.e(t.toString())
    } catch (t: UnknownHostException) {
        val throwable = UnknownHostException("Please check your internet connection.")
        failed?.invoke(throwable)
    }
    catch (t: Throwable) {
        LogHandler.e(t.toString())
        failed?.invoke(t)
    }
}


suspend inline fun <T> suspendCoroutineWithTimeout(
    timeout: Long,
    crossinline block: (Continuation<T>) -> Unit
) = withTimeout(timeout) {
    suspendCancellableCoroutine(block = block)
}


fun NavHostController.canGoBack(onGoBack: () -> Unit = {}){
    if (currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
        onGoBack()
        popBackStack()
    } else {
        LogHandler.w("Can't perform popBackStack right now")
    }
}

fun NavHostController.canGoBack(
    route: String,
    inclusive: Boolean
){
    if (currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
        popBackStack(route = route, inclusive = inclusive)
    } else {
        LogHandler.w("Can't perform popBackStack right now")
    }
}

@Composable
fun <T : Any> NavHostController.getCurrentStateHandleCollect(name: String): T? {
    var result =  currentBackStackEntry?.savedStateHandle?.getStateFlow<T?>(name,null)?.collectAsState()?.value
    currentBackStackEntry?.savedStateHandle?.remove<T?>(name)
    return result
}

fun CoroutineScope.tryLaunchIO(
    block: suspend CoroutineScope.() -> Unit,
    failed: ((error: Throwable) -> Unit)? = null,
): Job {
    return launch(Dispatchers.IO){
        try {
            block()
        } catch (t: CancellationException) {
            LogHandler.e(t)
        } catch (t: Throwable) {
            LogHandler.e(t.toString())
            failed?.invoke(t)
        }
    }
}