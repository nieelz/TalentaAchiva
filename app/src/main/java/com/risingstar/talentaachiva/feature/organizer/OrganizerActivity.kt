package com.risingstar.talentaachiva.feature.organizer

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.risingstar.talentaachiva.R
import com.risingstar.talentaachiva.databinding.ActivityOrganizerBinding

class OrganizerActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityOrganizerBinding
    private lateinit var viewmodel: OrganizerVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val username = intent.getStringExtra(CURRENT_USER_ID)
        if(username!=null)
            viewmodel = ViewModelProvider(
                this,OrganizerFactory(username)
            )[OrganizerVM::class.java]

        binding = ActivityOrganizerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Toast.makeText(this,"Welcome to $this, $username", Toast.LENGTH_SHORT).show()

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_organizer)


        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_organizer)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    companion object {
        const val CURRENT_USER_ID = "Fish"
    }
}