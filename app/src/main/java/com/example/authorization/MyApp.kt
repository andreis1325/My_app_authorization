package com.example.authorization

import android.app.Application
import android.content.SharedPreferences

import com.example.authorization.model.PreferencesUtils
import com.example.authorization.net.repo.ArticleRepo
import com.example.authorization.net.repo.BlogRepo
import com.example.authorization.net.repo.ReportRepo

import com.example.authorization.net.repo.UserRepo

import com.example.authorization.net.services.ApiRest
import com.example.authorization.net.services.ArticleService
import com.example.authorization.net.services.BlogService
import com.example.authorization.net.services.ReportService
import com.example.authorization.storage.UserStorage
import io.reactivex.disposables.CompositeDisposable
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.rx.RealmObservableFactory
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import retrofit2.Retrofit

private lateinit var kodeinStored: DI

class MyApp : Application() {


    private val settingModule = DI.Module("Settings module") {
        bind<UserRepo>() with singleton { UserRepo() }
        bind<SharedPreferences>() with singleton {
            PreferencesUtils.getSharedPreferences(applicationContext)
        }
        bind<UserStorage>() with singleton { UserStorage() }
        bind<Retrofit>()with singleton { ApiRest.getApi() }

        bind<CompositeDisposable>( )with singleton { CompositeDisposable() }
        bind<ArticleRepo>() with singleton{
            ArticleRepo(
                instance<Retrofit>().create(
                    ArticleService::class.java
                )
            )
        }

        bind<BlogRepo>() with singleton{
            BlogRepo(
                instance<Retrofit>().create(
                    BlogService::class.java
                )
            )
        }

        bind<ReportRepo>() with singleton{
            ReportRepo(
                instance<Retrofit>().create(
                    ReportService::class.java
                )
            )
        }

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