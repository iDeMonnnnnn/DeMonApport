package com.demon.apport.util

import android.content.res.Resources
import android.util.Log
import android.util.TypedValue
import android.widget.Toast
import com.demon.apport.App
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

/**
 * @author DeMonnnnnn
 * @date 2022/6/20
 * @email liu_demon@qq.com
 * @desc
 */
inline val <T : Any> T.Tag: String
    get() = this.javaClass.simpleName

val scopeMain = CoroutineScope((SupervisorJob() + Dispatchers.Main))

val scopeIO = CoroutineScope((SupervisorJob() + Dispatchers.IO))

val Any.dp2px
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, "$this".parseFloat(), Resources.getSystem().displayMetrics).toInt()

/**
 * 12.sp2px = 12sp
 */
val Any.sp2px
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, "$this".parseFloat(), Resources.getSystem().displayMetrics).toInt()


/**
 * String转成Float
 */
fun String?.parseFloat(defaultValue: Float = 0f): Float {
    if (this.isNullOrEmpty()) return defaultValue
    return tryCatch(defaultValue) {
        this.toFloat()
    }
}


/**
 * 当需要返回值try的时候使用
 */
inline fun <T> tryCatch(default: T, block: () -> T): T {
    return try {
        block()
    } catch (e: Exception) {
        Log.e("TryExt", "tryCatch: ", e)
        default
    }
}

fun String?.toast() {
    this ?: return
    Toast.makeText(App.appContext, this, Toast.LENGTH_SHORT).show()
}