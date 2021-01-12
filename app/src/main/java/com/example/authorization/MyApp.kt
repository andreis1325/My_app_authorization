package com.example.authorization

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.authorization.model.PreferencesUtils
import com.example.authorization.repo.UserRepo
import com.example.authorization.storage.UserStorage
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.rx.RealmObservableFactory
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import java.util.function.LongFunction

private lateinit var kodeinStored: DI

class MyApp : Application() {


    private val settingModule = DI.Module("Settings module") {
        bind<UserRepo>() with singleton { UserRepo() }
        bind<SharedPreferences>() with singleton {
            PreferencesUtils.getSharedPreferences(applicationContext)
        }
        bind<UserStorage>() with singleton { UserStorage() }
    }


    companion object {
        var kodein: DI
            get() = kodeinStored
            set(_) {}
    }

    override fun onCreate() {
        super.onCreate()

        if (::kodeinStored.isInitialized.not())
            kodeinStored = DI {
                import(settingModule)
            }

        initRealm()
    }

    private fun initRealm() {
        Realm.init(this@MyApp)

        val realmConfiguration = RealmConfiguration.Builder()
            .rxFactory(RealmObservableFactory(false))
            .deleteRealmIfMigrationNeeded()
            .build()

        Realm.setDefaultConfiguration(realmConfiguration)

    }
}