package com.example.accountantmini

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.example.accountantmini.data.AccountantDatabse
import com.example.accountantmini.data.entities.Account
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve NavController from the NavHostFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        // Set up the action bar for use with the NavController
        setupActionBarWithNavController(this, navController)

        //Test Code
//        val dao = AccountantDatabse.getDatabase(this).accountantDao()
//
//        val accounts = listOf(
//            Account(
//                accountName = "cash",
//                description = "cashAcc",
//                balance = 200.0,
//                accountType = "real"
//            ),
//            Account(
//                accountName = "bank",
//                description = "boi",
//                balance = 200.0,
//                accountType = "real"
//            )
//        )
//
//        lifecycleScope.launch {
//            accounts.forEach { dao.insertAccount(it) }
//        }
    }

    /**
     * Handle navigation when the user chooses Up from the action bar.
     */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}