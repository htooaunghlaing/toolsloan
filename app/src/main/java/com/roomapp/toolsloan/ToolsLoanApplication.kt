package com.roomapp.toolsloan

import android.app.Application
import com.roomapp.toolsloan.db.LoanAppDatabase
import com.roomapp.toolsloan.repository.*
import com.roomapp.toolsloan.ui.friend_details.FriendDetailsViewModelFactory
import com.roomapp.toolsloan.ui.friends.FriendViewModelFactory
import com.roomapp.toolsloan.ui.tools.ToolsViewModelFactory
import com.roomapp.toolsloan.utality.CrashReportingTree
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import timber.log.Timber

class ToolsLoanApplication : Application(),KodeinAware{
    override val kodein =  Kodein.lazy {
        import(androidXModule(this@ToolsLoanApplication))

        bind() from singleton { LoanAppDatabase(instance()) }
        bind() from singleton { instance<LoanAppDatabase>().toolDao() }
        bind() from singleton { instance<LoanAppDatabase>().friendDao() }
        bind() from singleton { instance<LoanAppDatabase>().loanDao() }
        bind<ToolsRepository>() with singleton { ToolsRepositoryImpl(instance()) }
        bind() from provider { ToolsViewModelFactory(instance(),instance()) }
        bind<FriendRepository>() with singleton { FriendRepositoryImpl(instance()) }
        bind() from provider { FriendViewModelFactory(instance()) }
        bind<LoanRepository>() with singleton { LoanRepositoryImpl(instance()) }
        bind() from provider { FriendDetailsViewModelFactory(instance(),instance(),instance()) }
        bind<FriendDetailsRepository>() with singleton { FriendDetailsRepositoryImpl(instance()) }
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }
}