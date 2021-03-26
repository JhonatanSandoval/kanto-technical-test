package pro.jsandoval.kantotest.util.ext

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup

fun ViewGroup.inflater(): LayoutInflater = LayoutInflater.from(context)
fun Context.inflater(): LayoutInflater = LayoutInflater.from(this)