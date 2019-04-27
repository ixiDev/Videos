/*
 * Created on 3/18/19 8:45 PM.
 * Copyright © 2019 刘振林. All rights reserved.
 */
@file:JvmName("Consts")

package com.liuzhenlin.videoplayer

import androidx.core.content.ContextCompat

/**
 * @author 刘振林
 */

const val NO_ID = -1L

internal const val KEY_DIRECTORY_PATH = "path_directory"
internal const val KEY_VIDEODIR = "videodir"
internal const val KEY_VIDEO = "video"
internal const val KEY_VIDEOS = "videos"
internal const val KEY_SELECTION = "index"

internal const val REQUEST_CODE_PLAY_VIDEO = 3
internal const val RESULT_CODE_PLAY_VIDEO = 3

internal const val REQUEST_CODE_PLAY_VIDEOS = 4
internal const val RESULT_CODE_PLAY_VIDEOS = 4

internal const val REQUEST_CODE_SEARCHED_VIDEOS_FRAGMENT = 5
internal const val RESULT_CODE_SEARCHED_VIDEOS_FRAGMENT = 5

internal const val REQUEST_CODE_FOLDED_VIDEOS_FRAGMENT = 6
internal const val RESULT_CODE_FOLDED_VIDEOS_FRAGMENT = 6

internal const val REQUEST_CODE_ADD_PICTURE = 7
internal const val RESULT_CODE_ADD_PICTURE = 7

const val EMPTY_STRING = ""
@JvmField
val EMPTY_STRING_ARRAY = arrayOf<String>()

private val _COLOR_SELECTOR by lazy(LazyThreadSafetyMode.NONE) { ContextCompat.getColor(App.getInstance(), R.color.selectorColor) }
@JvmField
internal val COLOR_SELECTOR = _COLOR_SELECTOR

private val _COLOR_ACCENT by lazy(LazyThreadSafetyMode.NONE) { ContextCompat.getColor(App.getInstance(), R.color.colorAccent) }
@JvmField
internal val COLOR_ACCENT = _COLOR_ACCENT

private val _STRING_TOP by lazy(LazyThreadSafetyMode.NONE) { App.getInstance().getString(R.string.top) }
@JvmField
internal val STRING_TOP = _STRING_TOP

private val _STRING_CANCEL_TOP by lazy(LazyThreadSafetyMode.NONE) { App.getInstance().getString(R.string.cancelTop) }
@JvmField
internal val STRING_CANCEL_TOP = _STRING_CANCEL_TOP

private val _STRING_SELECT_ALL by lazy(LazyThreadSafetyMode.NONE) { App.getInstance().getString(R.string.selectAll) }
@JvmField
internal val STRING_SELECT_ALL = _STRING_SELECT_ALL

private val _STRING_SELECT_NONE by lazy(LazyThreadSafetyMode.NONE) { App.getInstance().getString(R.string.selectNone) }
@JvmField
internal val STRING_SELECT_NONE = _STRING_SELECT_NONE
