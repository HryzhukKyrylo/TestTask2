package com.natife.testtask2.utils

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 *@author admin
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)
