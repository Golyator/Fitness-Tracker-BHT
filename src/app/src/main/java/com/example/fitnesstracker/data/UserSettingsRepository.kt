package com.example.fitnesstracker.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_settings")

class UserSettingsRepository(context: Context) {

    private val dataStore = context.dataStore

    private object Keys {
        val AGE = intPreferencesKey("age")
        val HEIGHT = intPreferencesKey("height")
        val WEIGHT = doublePreferencesKey("weight")
        val GENDER = stringPreferencesKey("gender")
    }

    val userSettings: Flow<UserSettings> = dataStore.data.map { preferences ->
        UserSettings(
            age = preferences[Keys.AGE] ?: 30,
            height = preferences[Keys.HEIGHT] ?: 180,
            weight = preferences[Keys.WEIGHT] ?: 75.0,
            gender = Gender.valueOf(preferences[Keys.GENDER] ?: Gender.MALE.name)
        )
    }

    suspend fun saveUserSettings(userSettings: UserSettings) {
        dataStore.edit { preferences ->
            preferences[Keys.AGE] = userSettings.age
            preferences[Keys.HEIGHT] = userSettings.height
            preferences[Keys.WEIGHT] = userSettings.weight
            preferences[Keys.GENDER] = userSettings.gender.name
        }
    }
}
